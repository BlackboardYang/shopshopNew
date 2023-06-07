package com.panacea.shopshop.service.impl;

import com.panacea.shopshop.mapper.ShopUserMapper;
import com.panacea.shopshop.model.ShopUser;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements UserDetailsService {

    @Resource
    ShopUserMapper shopUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null){
            throw new UsernameNotFoundException("username cannot be empty!!!");
        }
       ShopUser shopUser = shopUserMapper.searchByNameOrEmail(username);
        if (shopUser == null){
            throw new UsernameNotFoundException("username or password verify failed!!!");
        }
        return User
                .withUsername(shopUser.getUsername())
                .password(shopUser.getPassword())
                .roles("user")
                .build();
    }
}
