package com.z2devil.blog_api.config;

import com.z2devil.blog_api.security.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: blog_api
 * @description: 过滤器配置
 * @author: z2devil
 * @create: 2021-08-04
 **/
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> registrationFilter() {
        FilterRegistrationBean<AuthFilter> registration = new FilterRegistrationBean<>(new AuthFilter());
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.addInitParameter("exclusions","/api/auth/sign,/api/auth/verify-code");
        return registration;
    }

}
