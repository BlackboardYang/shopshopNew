package com.panacea.shopshop.controller;

import com.panacea.shopshop.model.RestBean;
import com.panacea.shopshop.service.AuthService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
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
    private final String USERNAME_REGEX = "^[\\u4E00-\\u9FA5\\u3040-\\u309F\\u30A0-\\u30FFa-zA-Z0-9]+$";


    @PostMapping("/valid-register-email")
    public RestBean<String> validRegisterEmail(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email
                                        , HttpSession session) {

        String isMailValid = service.sendValidateEmail(email, session.getId(), false);

        if (isMailValid == null) {
            return RestBean.success("Email sent, check mail box please");
        } else {
            return RestBean.failure(400, isMailValid);
        }
    }

    @PostMapping("/valid-reset-email")
    public RestBean<String> validResetEmail(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email
            , HttpSession session) {

        String isMailValid = service.sendValidateEmail(email, session.getId(), true);

        if (isMailValid == null) {
            return RestBean.success("Email sent, check mail box please");
        } else {
            return RestBean.failure(400, isMailValid);
        }
    }

    @PostMapping("/register")
    public RestBean<String> registerUser(@Pattern(regexp = USERNAME_REGEX) @Length(min = 3, max = 16) @RequestParam("username") String username,
                                         @Length(min = 6, max = 16)@RequestParam("password") String password,
                                         @Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                         @Length(min = 6, max = 6) String code,
                                         HttpSession session){

        String isGoodToRegister = service.validateAndRegister(username, password, email, code, session.getId());
        if (isGoodToRegister == null){
            return RestBean.success("Register Success!");
        } else {
            return RestBean.failure(400, isGoodToRegister);
        }
    }

    @PostMapping("/start-reset")
    public RestBean<String> startReset(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                       @Length(min = 6, max = 6) String code,
                                       HttpSession session){

        String isMailValid = service.validateOnly(email, code, session.getId());

        if (isMailValid == null){
            session.setAttribute("reset-password", email);
            return RestBean.success();
        } else {
            return RestBean.failure(400, isMailValid);
        }
    }

    @PostMapping("/do-reset")
    public RestBean<String> resetPassword(@Length(min = 6, max = 16)@RequestParam("password") String password,
                                          HttpSession session){
        String email = (String) session.getAttribute("reset-password");

        if (email == null){
            return RestBean.failure(401, "Complete verification first");
        } else if(service.resetPassword(password, email)) {
            session.removeAttribute("reset-password");
            return RestBean.success("Password reset success");
        } else {
            return RestBean.failure(500, "Something went wrong, please contact us");
        }
    }




}
