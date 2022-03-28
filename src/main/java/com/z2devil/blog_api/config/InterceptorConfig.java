package com.z2devil.blog_api.config;

import com.z2devil.blog_api.security.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author z2devil
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加权限拦截器
        registry.addInterceptor(new AuthInterceptor())
                // 拦截的路径
                .addPathPatterns("/**")
                // 添加不拦截路径
                .excludePathPatterns(
                        // html静态资源
                        "/**/*.html",
                        // js静态资源
                        "/**/*.js",
                        // css静态资源
                        "/**/*.css",
                        // Web开放字体格式
                        "/**/*.woff",
                        // 字体
                        "/**/*.ttf"
                );
    }

}