package com.z2devil.blog_api.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @program: blog
 * @description: IP地址工具类
 * @auther: z2devil
 * @create: 2020-09-24
 **/
public class IPUtil {

    private static final String UNKNOWN = "unknown";

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        try {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            String comma = ",";
            String localhost = "127.0.0.1";
            if (ip.contains(comma)) {
                ip = ip.split(",")[0];
            }
            if (localhost.equals(ip)) {
                // 获取本机真正的ip地址
                try {
                    ip = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
            return ip;
        } catch (Exception e) {
            e.printStackTrace();
            return "ip暂时不能获取";
        }
    }

}
