package com.z2devil.blog_api.annotation;

import com.z2devil.blog_api.api.entity.enums.ColumnType;

import java.lang.annotation.*;

/**
 * @program: blog_api
 * @description: 列注解
 * @author: z2devil
 * @create: 2021-10-19
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    ColumnType type();

    String name() default "";

    String value() default "";

    boolean customMethod() default false;

}
