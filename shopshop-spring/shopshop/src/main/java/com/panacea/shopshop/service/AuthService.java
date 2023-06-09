package com.panacea.shopshop.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    boolean sendValidateEmail(String email, String sessionId);
}
