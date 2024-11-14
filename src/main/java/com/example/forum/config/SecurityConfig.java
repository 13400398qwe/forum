package com.example.forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register").permitAll() // 允许匿名访问注册接口
                        .requestMatchers("/api/auth/login").permitAll()
                        .anyRequest().authenticated()                     // 其他接口需要认证
                )
                .formLogin(form -> form
                        .permitAll() // 允许所有用户访问登录页面
                )
                .csrf(csrf -> csrf.disable()); // 如果是无状态 REST API，可禁用 CSRF

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 使用 BCrypt 加密密码
    }
}
