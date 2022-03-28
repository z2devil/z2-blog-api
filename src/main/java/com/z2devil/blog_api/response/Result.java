package com.z2devil.blog_api.response;

import com.z2devil.blog_api.response.enums.ResponseEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @program: blog
 * @description: 统一返回数据
 * @author: z2devil
 * @create: 2020-09-22
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class Result<T> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code;


    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 构造方法
     */
    public Result(ResponseEnum responseEnum) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
    }

    public Result(ResponseEnum responseEnum, String customMsg) {
        this.code = responseEnum.getCode();
        this.msg = customMsg;
    }

    public Result(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.msg = responseEnum.getMsg();
        this.data = data;
    }

    public Result(ResponseEnum responseEnum, String customMsg, T data) {
        this.code = responseEnum.getCode();
        this.msg = customMsg;
        this.data = data;
    }

    /**
     * 调用
     */
    public static <T> Result<T> res(ResponseEnum responseEnum) {
        return new Result<T>(responseEnum);
    }

    public static <T> Result<T> res(ResponseEnum responseEnum, String customMsg) {
        return new Result<T>(responseEnum, customMsg);
    }

    public static <T> Result<T> res(ResponseEnum responseEnum, T data) {
        return new Result<T>(responseEnum, data);
    }

    public static <T> Result<T> res(ResponseEnum responseEnum, String customMsg, T data) {
        return new Result<T>(responseEnum, customMsg, data);
    }
}
