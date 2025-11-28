package com.cms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置类
 * 用于配置JWT认证和权限控制
 * @author 许祖嘉
 * @date 2025-11-28
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 禁用CSRF保护，因为使用JWT认证
            .csrf(csrf -> csrf.disable())
            
            // 设置会话管理为无状态，因为使用JWT认证
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // 配置请求授权规则
            .authorizeHttpRequests(authorize -> authorize
                // 允许所有用户访问登录接口
                .requestMatchers("/api/auth/**").permitAll()
                
                // 允许所有用户访问公共接口
                .requestMatchers("/api/public/**").permitAll()
                
                // 允许学生角色访问学生相关接口
                .requestMatchers("/api/student/**").hasRole("学生")
                
                // 允许教师角色访问教师相关接口
                .requestMatchers("/api/teacher/**").hasRole("教师")
                
                // 允许管理员角色访问管理员相关接口
                .requestMatchers("/api/admin/**").hasRole("管理员")
                
                // 成绩相关接口权限配置
                .requestMatchers("/api/scores/student/**").hasRole("学生")
                .requestMatchers("/api/scores/teacher/**").hasRole("教师")
                .requestMatchers("/api/scores/course/**").hasRole("教师")
                .requestMatchers("/api/scores/batch").hasRole("教师")
                .requestMatchers("/api/scores/**").hasAnyRole("教师", "管理员")
                
                // 成绩异议相关接口权限配置
                .requestMatchers("/api/score-disputes/student/**").hasRole("学生")
                .requestMatchers("/api/score-disputes/course/**").hasRole("教师")
                .requestMatchers("/api/score-disputes/**").hasAnyRole("教师", "管理员")
                
                // 其他所有请求都需要认证
                .anyRequest().authenticated()
            )
            
            // 在UsernamePasswordAuthenticationFilter之前添加JWT认证过滤器
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    /**
     * 配置AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }
}