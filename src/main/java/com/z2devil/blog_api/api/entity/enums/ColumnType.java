package com.z2devil.blog_api.api.entity.enums;

import com.z2devil.blog_api.utils.SignUserIdUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: blog_api
 * @description: 列支持的类型
 * @author: z2devil
 * @create: 2021-10-19
 **/
@Getter
@AllArgsConstructor
public enum ColumnType {

    /**
     * 布尔型
     */
    BOOL(Boolean.class, "valueOf"),

    /**
     * 整型
     */
    INT(Integer.class, "valueOf"),

    /**
     * 字符串型
     */
    STR(String.class, ""),

    /**
     * 登录用户id
     */
    LOGGED_USER(SignUserIdUtils.class, "get")

    ;

    /**
     * 类型
     */
    private Class<?> clazz;

    /**
     * 格式化方式
     */
    private String methodStr;
}
