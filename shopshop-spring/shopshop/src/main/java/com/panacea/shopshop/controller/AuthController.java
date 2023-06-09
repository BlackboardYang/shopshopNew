package com.panacea.shopshop.controller;

import com.panacea.shopshop.model.RestBean;
import com.panacea.shopshop.service.AuthService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.annotations.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    AuthService service;

    private final String EMAIL_REGEX = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";


    @PostMapping("valid-email")
    public RestBean<String> validEmail(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email
                                        , HttpSession session) {
        if (service.sendValidateEmail(email, session.getId())) {
            return RestBean.success("Email sent, check mail box please");
        } else {
            return RestBean.failure(400, "Email sent fail, contact us please");
        }

    }
}
