package com.z2devil.blog_api.exception;

import com.z2devil.blog_api.response.enums.ResponseEnum;
import lombok.Getter;

/**
 * @program: blog_api
 * @description: 统一异常处理
 * @author: z2devil
 * @create: 2021-07-26
 **/
@Getter
public class BaseException extends RuntimeException {
    /**
     * 异常枚举
     */
    private ResponseEnum responseEnum;

    public BaseException(String message) {
        super(message);
    }
}
