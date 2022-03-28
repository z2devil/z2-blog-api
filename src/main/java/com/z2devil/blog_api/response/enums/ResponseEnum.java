package com.z2devil.blog_api.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: blog
 * @description: 返回枚举
 * @author: z2devil
 * @create: 2020-09-22
 **/
@Getter
@AllArgsConstructor
public enum ResponseEnum {

    /**
     * 成功
     */
    OK(200, "请求成功"),

    /**
     * 客户端错误
     */
    BAD_REQUEST(400, "错误请求"),
    UNAUTHORIZED(401, "未登录或当前登录状态过期"),
    FORBIDDEN(403, "权限不足"),
    NOT_FOUND(404, "未找到"),
    METHOD_NOT_ALLOWED(405, "方法不允许"),
    DATA_EXISTS(406, "数据已存在"),

    /**
     * 服务器错误
     */
    INTERNAL_SERVER_ERROR(500, "服务器错误"),

    ;
    private int code;
    private String msg;

}
