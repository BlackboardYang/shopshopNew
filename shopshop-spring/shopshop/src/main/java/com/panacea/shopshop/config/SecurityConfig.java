package com.panacea.shopshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panacea.shopshop.model.RestBean;
import com.panacea.shopshop.service.impl.AuthServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    ObjectMapper objectMapper;

    @Resource
    AuthServiceImpl authServiceImpl;

    @Resource
    DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(("/api/auth/**"), ("/home/category/head")).permitAll()
                .anyRequest().authenticated()
        ).formLogin(login -> login
                .loginProcessingUrl("/api/auth/login")
                .successHandler(this::onAuthenticationSuccess)
                .failureHandler(this::onAuthenticationFailure)
        ).logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .logoutSuccessHandler(this::onAuthenticationSuccess)
        )
                .rememberMe(rememberMe ->
                rememberMe.rememberMeParameter("rememberme")
                        .tokenRepository(this.tokenRepository())
                        .tokenValiditySeconds(3600 * 24 * 5)
        )
                .userDetailsService(authServiceImpl
        ).csrf(AbstractHttpConfigurer::disable
        ).exceptionHandling(failure -> failure.authenticationEntryPoint(this::onAuthenticationFailure)
        ).cors(cors -> cors.configurationSource(configurationSource()));
        return http.build();
    }

    private PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        //以下方法通过 bean 注入方式实现创建表，实现后消除
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    private CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOriginPattern("*");
        corsConfig.setAllowCredentials(true);
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");
        corsConfig.addExposedHeader("*");

        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", corsConfig);

        return configurationSource;
    }


//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity security) throws Exception {
//        return security.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(authServiceImpl)
//                .and().build();
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        if (request.getRequestURI().endsWith("/login")) {
            response.getWriter().write(objectMapper.writeValueAsString(RestBean.success("Login Success!ログイン成功！登录成功！")));
        } else if (request.getRequestURI().endsWith("/logout")) {
            response.getWriter().write(objectMapper.writeValueAsString(RestBean.success("You are logged out.")));
        }
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(RestBean.failure(401, exception.getMessage())));
    }
}
