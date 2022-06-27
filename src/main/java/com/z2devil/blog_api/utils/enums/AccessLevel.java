package com.z2devil.blog_api.utils.enums;

/**
 * @program: blog
 * @description: 权限枚举类
 * @author: z2devil
 * @create: 2020-08-25
 **/
public enum AccessLevel {

    /**
     * 普通用户
     */
    LOGIN(0, "login"),
    /**
     * 管理员
     */
    ADMIN(1, "admin"),
    /**
     * 博主
     */
    MASTER(2, "master");

    int lv;
    String msg;

    AccessLevel(int lv, String msg) {
        this.lv = lv;
        this.msg = msg;
    }

    public int getLv() {
        return lv;
    }

    public String getMsg() {
        return msg;
    }
}
