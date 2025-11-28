package com.cms.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 * 用于生成和验证JWT令牌
 * @author 许祖嘉
 * @date 2025-11-28
 */
@Component
public class JwtUtils {

    /**
     * JWT过期时间（毫秒）
     */
    @Value("${jwt.expiration:3600000}")
    private long expiration;

    /**
     * JWT密钥
     */
    @Value("${jwt.secret:cms_secret_key_2025}")
    private String secret;

    /**
     * 生成JWT令牌
     * @param userId 用户ID
     * @param username 用户名
     * @param role 用户角色
     * @return JWT令牌
     */
    public String generateToken(String userId, String username, String role) {
        // 设置JWT声明
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        claims.put("role", role);
        claims.put("issuedAt", new Date());

        // 生成JWT令牌
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 从JWT令牌中获取声明
     * @param token JWT令牌
     * @return 声明
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 验证JWT令牌
     * @param token JWT令牌
     * @return 是否有效
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从JWT令牌中获取用户ID
     * @param token JWT令牌
     * @return 用户ID
     */
    public String getUserIdFromToken(String token) {
        return getClaimsFromToken(token).get("userId", String.class);
    }

    /**
     * 从JWT令牌中获取用户名
     * @param token JWT令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).get("username", String.class);
    }

    /**
     * 从JWT令牌中获取用户角色
     * @param token JWT令牌
     * @return 用户角色
     */
    public String getRoleFromToken(String token) {
        return getClaimsFromToken(token).get("role", String.class);
    }

    /**
     * 获取签名密钥
     * @return 签名密钥
     */
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}