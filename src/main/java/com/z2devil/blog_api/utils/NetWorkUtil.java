package com.z2devil.blog_api.utils;

import javax.servlet.http.HttpServletRequest;

public class NetWorkUtil {
    /**
     * Java Web获取访问者IP的方式
     * 获取一个请求的发起IP
     * @param request HttpServletRequest
     * @return String ip
     */
    public static String getIP(HttpServletRequest request) {
        String comma = ",";
        String localhostIP = "0:0:0:0:0:0:0:1";
        String commonIP = "0.0.0.0.0.0.0.1%0";
        String ip = request.getHeader("x-forwarded-for");

        if (isNull(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isNull(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isNull(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (isNull(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (isNull(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (isNull(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhostIP.equals(ip) || commonIP.equals(ip)) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    private static boolean isNull(String ip) {
        String unknown = "unknown";
        return null == ip || 0 == ip.length() || unknown.equalsIgnoreCase(ip);
    }
}
