package com.z2devil.blog_api.utils;

/**
 * @program: blog_api
 * @description: 验证码相关工具类
 * @author: z2devil
 * @create: 2021-05-22
 **/
public class CaptchaUtils {

    /**
     * 获取随机字符串
     * @param length
     * @return
     */
    public static String randomStr(int length) {
//        String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String str1 = "1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < length; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }

    /**
     * 获取随机数字
     * @param length
     * @return
     */
    public static String randomNum(int length) {
        int max = 9 * (int) Math.pow(10, length-1) + 9, min = (int) Math.pow(10, length-1);
        int res = (int) (Math.random()*(max-min)+min);
        return Integer.toString(res);
    }

}
