package com.z2devil.blog_api.exception.handler;

import com.z2devil.blog_api.exception.AccessException;
import com.z2devil.blog_api.exception.BaseException;
import com.z2devil.blog_api.response.Result;
import com.z2devil.blog_api.response.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: blog_api
 * @description: 统一异常处理
 * @author: z2devil
 * @create: 2021-07-26
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus()
    public Result<String> handleException(Throwable e) {
        // 打印堆栈信息
        log.error(e.getMessage(), e);
        return Result.res(ResponseEnum.INTERNAL_SERVER_ERROR, "未知错误");
    }

    /**
     * 异常统一返回结构处理
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus()
    public Result<String> baseExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return Result.res(ResponseEnum.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    /**
     * 异常统一返回结构处理
     * @params [e]
     * @return Result<String>
     * @author z2devil
     * @date 2021/11/8
     */
    @ExceptionHandler(AccessException.class)
    @ResponseStatus()
    public Result<String> AccessExceptionHandler(AccessException e) {
        log.error(e.getMessage(), e);
        return Result.res(e.getResponseEnum(), e.getMessage());
    }

}
