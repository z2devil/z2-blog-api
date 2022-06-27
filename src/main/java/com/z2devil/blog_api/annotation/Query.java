package com.z2devil.blog_api.annotation;

import java.lang.annotation.*;

/**
 * @program: blog_api
 * @description: 关联查询
 * @author: z2devil
 * @create: 2021-10-13
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Queries.class)
public @interface Query {

    // 列名，作为关联查询的参数
    String column() default "";

    // 列名，作为关联查询的参数(可以是多个，支持给定值)
    Column[] columns() default {};

    // 封装目标的属性名，作为关联查询的结果
    String property() default "";

    // 执行的关联查询
    String select() default "";
}
