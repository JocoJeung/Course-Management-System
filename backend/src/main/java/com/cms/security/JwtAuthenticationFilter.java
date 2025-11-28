package com.cms.security;

import com.cms.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * JWT认证过滤器
 * 用于拦截请求并验证JWT令牌
 * @author 许祖嘉
 * @date 2025-11-28
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        // 从请求头中获取Authorization字段
        String authorizationHeader = request.getHeader("Authorization");

        String token = null;
        String userId = null;
        String role = null;

        // 检查Authorization头是否包含Bearer前缀
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            try {
                // 从JWT令牌中获取用户ID和角色
                userId = jwtUtils.getUserIdFromToken(token);
                role = jwtUtils.getRoleFromToken(token);
            } catch (Exception e) {
                logger.error("JWT令牌解析失败", e);
            }
        }

        // 如果令牌有效且SecurityContext中没有认证信息，则进行认证
        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtils.validateToken(token)) {
                // 创建认证令牌
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userId, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role)));
                
                // 设置认证详情
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // 将认证令牌存储到SecurityContext中
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // 继续处理请求
        filterChain.doFilter(request, response);
    }
}