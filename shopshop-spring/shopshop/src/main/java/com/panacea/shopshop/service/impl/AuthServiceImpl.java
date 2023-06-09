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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null) {
            throw new UsernameNotFoundException("username cannot be empty!!!");
        }
        ShopUser shopUser = shopUserMapper.searchByNameOrEmail(username);
        if (shopUser == null) {
            throw new UsernameNotFoundException("username or password verify failed!!!");
        }
        return User
                .withUsername(shopUser.getUsername())
                .password(shopUser.getPassword())
                .roles("user")
                .build();
    }

    @Override//邮箱发送验证码
    public boolean sendValidateEmail(String email, String sessionId) {
        //判断是否存在该key 以及 3 分钟过期时间限制
        String key = "email: " + sessionId + ":" + email;
        if(Boolean.TRUE.equals(template.hasKey(key))){
            Long expire = Optional.ofNullable(template.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
            if(expire > 120) return false;
        }

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
            template.opsForValue().set(key, String.valueOf(code),3 , TimeUnit.MINUTES);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }
    }
}
