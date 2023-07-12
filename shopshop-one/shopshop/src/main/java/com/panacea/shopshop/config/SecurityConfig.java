package com.panacea.shopshop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.panacea.shopshop.model.RestBean;
import com.panacea.shopshop.service.impl.AuthServiceImpl;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
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

import javax.crypto.SecretKey;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import static javax.crypto.Cipher.SECRET_KEY;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    ObjectMapper objectMapper;

    @Resource
    AuthServiceImpl authServiceImpl;

    @Resource
    DataSource dataSource;

    @Autowired
    StringRedisTemplate redisTemplate;

    //25Hrs
    private static final Long EXPIRATION_TIME = 1000L * 60L * 25L;

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(("/api/auth/**"), ("/home/category/**"), ("/product/**")).permitAll()
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

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (request.getRequestURI().endsWith("/login")) {
            String username = authentication.getName();
//            // 生成令牌

//            // 将令牌添加到响应中
//            response.addHeader("Authorization", "Bearer " + token);


            SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
            String token = Jwts.builder()
                    .setSubject(username)  // 设置用户名作为令牌主题
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // 设置令牌过期时间
                    .signWith(secretKey, SignatureAlgorithm.HS512)  // 使用密钥对令牌进行签名
                    .compact();
            //String token = "fakeToken";
            redisTemplate.opsForValue().set(username, token, 25, TimeUnit.HOURS);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("username", username);
            responseData.put("authenticated", authentication.isAuthenticated());
            responseData.put("token", token);

            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.addHeader("Authorization", "Bearer " + token);
            response.getWriter().write(objectMapper.writeValueAsString(RestBean.success(responseData)));
        } else if (request.getRequestURI().endsWith("/logout")) {
            response.getWriter().write(objectMapper.writeValueAsString(RestBean.success("You are logged out.")));
        }
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(RestBean.failure(401, exception.getMessage())));
    }
}
