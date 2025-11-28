package com.cms.controller;

import com.cms.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 认证控制器
 * 处理用户登录和JWT令牌生成
 * @author 许祖嘉
 * @date 2025-11-28
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 登录请求DTO
     */
    public static class LoginRequest {
        private String username;
        private String password;
        private String role;

        // getter and setter
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    /**
     * 登录响应DTO
     */
    public static class LoginResponse {
        private String token;
        private String userId;
        private String username;
        private String role;

        public LoginResponse(String token, String userId, String username, String role) {
            this.token = token;
            this.userId = userId;
            this.username = username;
            this.role = role;
        }

        // getter and setter
        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    /**
     * 用户登录
     * @param loginRequest 登录请求
     * @return 登录响应
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // 进行身份认证
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            // 生成JWT令牌
            String token = jwtUtils.generateToken(
                    loginRequest.getUsername(), // 使用用户名作为用户ID
                    loginRequest.getUsername(),
                    loginRequest.getRole());

            // 返回登录响应
            return ResponseEntity.ok(new LoginResponse(
                    token,
                    loginRequest.getUsername(),
                    loginRequest.getUsername(),
                    loginRequest.getRole()));
        } catch (AuthenticationException e) {
            // 认证失败，返回错误信息
            Map<String, String> error = new HashMap<>();
            error.put("message", "用户名或密码错误");
            return ResponseEntity.badRequest().body(error);
        }
    }
}