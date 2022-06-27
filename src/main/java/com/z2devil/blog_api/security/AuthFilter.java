package com.z2devil.blog_api.security;

import cn.hutool.core.util.StrUtil;
import com.z2devil.blog_api.config.bean.AuthProperties;
import com.z2devil.blog_api.utils.JwtUtil;
import com.z2devil.blog_api.utils.RedisUtils;
import com.z2devil.blog_api.utils.SignUserIdUtils;
import com.z2devil.blog_api.utils.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @program: blog_api
 * @description: 权限过滤器
 * @author: z2devil
 * @create: 2021-08-04
 **/
@Slf4j
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // 获取authProperties配置类
        AuthProperties authProperties = SpringContextHolder.getBean(AuthProperties.class);
        // 获取redis工具类
        RedisUtils redisUtils = SpringContextHolder.getBean(RedisUtils.class);
        // 获取请求头中的token
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(authProperties.getHeader());
        // 校验token
        if (StrUtil.isNotBlank(token) && JwtUtil.isExpiration(token)) {
            // 从token中获取用户id
            Integer getUserId = JwtUtil.getUid(token);
            // 在Redis中查询已登录用户的token
            String loginTokenKey = authProperties.getTokenPrefix()+ getUserId;
            Object getToken = redisUtils.get(loginTokenKey);
            // 向线程存储中存入登录用户id
            if (getToken != null && getToken.equals(token)) {
                SignUserIdUtils.set(getUserId);
                // 续期操作
                Integer expire = (int) redisUtils.getExpire(authProperties.getTokenPrefix()+getUserId);
                if (authProperties.getTokenDetectScope() > expire) {
                    redisUtils.expire(authProperties.getTokenPrefix()+getUserId,
                            authProperties.getTokenExpireTime());
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
