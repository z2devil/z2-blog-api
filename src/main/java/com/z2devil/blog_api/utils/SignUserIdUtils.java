package com.z2devil.blog_api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @program: blog_api
 * @description: 用户信息上下文工具类
 * @author: z2devil
 * @create: 2021-05-23
 **/
@Slf4j
public class SignUserIdUtils {

    private static final String SECURITY_CONTEXT_ATTRIBUTES = "SECURITY_CONTEXT";

    public static void set(Integer id) {
        RequestContextHolder.currentRequestAttributes()
                .setAttribute(
                        SECURITY_CONTEXT_ATTRIBUTES,
                        id,
                        RequestAttributes.SCOPE_REQUEST
                );
    }

    public static Integer get() {
        return (Integer) RequestContextHolder
                .currentRequestAttributes()
                .getAttribute(
                        SECURITY_CONTEXT_ATTRIBUTES,
                        RequestAttributes.SCOPE_REQUEST
                );
    }

    public static Integer get(String s) {
        return get();
    }

}
