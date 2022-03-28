package com.z2devil.blog_api.annotation;

import java.lang.annotation.*;

/**
 * @program: blog_api
 * @description: 关联查询集合
 * @author: z2devil
 * @create: 2021-10-13
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Queries {

    Query[] value() default {};
}
