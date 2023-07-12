package com.panacea.shopshop.service.impl;

import com.panacea.shopshop.mapper.ShopUserMapper;
import com.panacea.shopshop.model.ShopUser;
import com.panacea.shopshop.service.AuthService;
import jakarta.annotation.Resource;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${spring.mail.username}")
    String from;

    @Resource
    ShopUserMapper shopUserMapper;

    @Resource
    MailSender mailSender;
    @Resource
    StringRedisTemplate template;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("username cannot be empty!!!");
        }
        ShopUser shopUser = shopUserMapper.searchByNameOrEmail(username);
        if (shopUser == null) {
            throw new UsernameNotFoundException("username or password verification failed!!!");
        }
        return User
                .withUsername(shopUser.getUsername())
                .password(shopUser.getPassword())
                .roles("user")
                .build();
    }

    @Override//邮箱发送验证码
    public String sendValidateEmail(String email, String sessionId, boolean hasAccount) {
        //判断是否存在该key 以及 3 分钟过期时间限制
        String key = "email:" + email + ":" + sessionId + ":" + hasAccount;
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            Long expire = Optional.ofNullable(template.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
            if (expire > 120) return "Request too frequently, please try later";
        }

        ShopUser shopUser = shopUserMapper.searchByNameOrEmail(email);
        if (hasAccount && (shopUser == null)) return "Cannot find this Email address";
        if (!hasAccount && (shopUser != null)) return "This Email address has been used";


        //生成随机验证码
        Random random = new Random();
        int code = random.nextInt(899999) + 100000;

        //创建邮件内容
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("Conformation Mail from Panacea shopshop");
        message.setText("\nYour code is "
                + code
                + ".\n\nThis code is valid for 3 minutes.\nPlease DO NOT send to anyone.\nThis mail is send to "
                + email);

        //发送操作并设定 3 分钟期限
        try {
            mailSender.send(message);
            template.opsForValue().set(key, String.valueOf(code), 3, TimeUnit.MINUTES);
            return "Mail sent!";
        } catch (MailException e) {
            e.printStackTrace();
            return "Send fail, please check the mail address";
        }
    }

    @Override
    public String validateAndRegister(String username, String password, String email, String code, String sessionId) {

        String key = "email:" + email + ":" + sessionId + ":" + false;

        if (Boolean.TRUE.equals(template.hasKey(key))) {
            String keyCheck = template.opsForValue().get(key);
            if (keyCheck == null) return "Verification code was expired, please request again";
            if (keyCheck.equals(code)) {
                ShopUser shopUser = shopUserMapper.searchByNameOrEmail(username);
                template.delete(key);
                password = encoder.encode(password);
                return (shopUserMapper.createAccount(username, password, email) > 0) ?
                        null : "Something wired, please contact us";
            } else {
                return "Verification code error, please check your code";
            }
        } else {
            return "Get verification code first please";
        }
    }

    @Override
    public String validateOnly(String email, String code, String sessionId) {

        String key = "email:" + email + ":" + sessionId + ":" + true;
        System.out.println(key);
        if (Boolean.TRUE.equals(template.hasKey(key))) {
            String keyCheck = template.opsForValue().get(key);
            if (keyCheck == null) return "Verification code was expired, please request again";
            if (keyCheck.equals(code)) {
                template.delete(key);
                return null;
            } else {
                return "Verification code error, please check your code";
            }
        } else {
            return "Get verification code first please";
        }
    }

    @Override
    public boolean resetPassword(String password, String email) {

        password = encoder.encode(password);
        return shopUserMapper.updatePasswordByEmail(password, email) > 0;
    }

}
