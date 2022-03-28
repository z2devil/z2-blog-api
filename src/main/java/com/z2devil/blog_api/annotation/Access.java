package com.z2devil.blog_api.annotation;

import com.z2devil.blog_api.utils.enums.AccessLevel;

import java.lang.annotation.*;

/**
 * @program: blog
 * @description 权限注解
 * @author z2devil
 * @create 2020-08-25
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface Access {
    AccessLevel value() default AccessLevel.LOGIN;
}
