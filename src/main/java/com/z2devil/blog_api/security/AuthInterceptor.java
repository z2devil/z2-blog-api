package com.z2devil.blog_api.security;

import com.z2devil.blog_api.annotation.Access;
import com.z2devil.blog_api.api.entity.User;
import com.z2devil.blog_api.api.service.IUserService;
import com.z2devil.blog_api.exception.AccessException;
import com.z2devil.blog_api.response.enums.ResponseEnum;
import com.z2devil.blog_api.utils.SignUserIdUtils;
import com.z2devil.blog_api.utils.SpringContextHolder;
import com.z2devil.blog_api.utils.enums.AccessLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @program: blog_api
 * @description: 权限拦截器
 * @author z2devil
 * @create: 2021-05-22
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) {
        if (!(o instanceof HandlerMethod)) {
            return true;
        }
        // 访问的接口
        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        // 获取访问接口的权限注解
        Access access = method.getAnnotation(Access.class);
        // 如果权限注解为null, 说明不需要拦截, 直接放过
        if (access == null) {
            return true;
        }
        // 获取访问接口的权限等级
        int authorityLevel = access.value().getLv();
        // 如果权限等级需要在登录之上
        if (authorityLevel >= AccessLevel.LOGIN.getLv()) {
            // 获取UserService
            IUserService userService = SpringContextHolder.getBean(IUserService.class);
            User signedUser = userService.getById(SignUserIdUtils.get());
            // 判断user权限是否足够
            if (signedUser == null) {
                throw new AccessException(ResponseEnum.UNAUTHORIZED,
                        "access " + method.getName() + " Not logged in or login information expired");
            }
            if (signedUser.getLv() < authorityLevel) {
                throw new AccessException(ResponseEnum.FORBIDDEN,
                        "access " + method.getName() + " No authority");
            }
        }
        return true;
    }

}
