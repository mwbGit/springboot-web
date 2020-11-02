package com.mwb.web.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class HttpUtil {
    private static final List<String> DOMAINS = Arrays.asList("mengweibo.com", "maomihome.com");

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }

    public static String getFullUrl(HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getQueryString())) {
            return request.getRequestURI();
        } else {
            return new StringBuilder(request.getRequestURI()).append("?").append(request.getQueryString()).toString();
        }
    }

    public static String getCookieValue(String key, HttpServletRequest req) {
        String cookieValue = null;
        Cookie[] cookies = req.getCookies();
        if (null == cookies) {
            return null;
        } else {
            for (int i = 0; i < cookies.length; ++i) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals(key)) {
                    cookieValue = cookie.getValue();
                }
            }

            return cookieValue;
        }
    }

    public static void saveCookie(HttpServletResponse response, String key, String value, int second, String path) {
        value = StringUtils.remove(value, '\n');
        value = StringUtils.remove(value, '\r');
        Cookie cookie = new Cookie(key, value);
        if (StringUtils.isNotBlank(path)) {
            cookie.setPath(path);
        }

        cookie.setMaxAge(second);
//        if (StringUtils.isNotBlank(domain)) {
//            cookie.setDomain(domain);
//        }

        response.addCookie(cookie);
    }

    public static boolean isOnlineDomain(HttpServletRequest request) {
        String domain = request.getServerName().replaceFirst("www", "");
        return DOMAINS.contains(domain);
    }

    public static String getMainDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getSession().getServletContext().getContextPath()).append("/").toString();
    }

    public static void clearCookie(HttpServletResponse response, String key, int second, String path) {
        Cookie cookie = new Cookie(key, null);
        cookie.setPath(path);
        cookie.setMaxAge(second);
//        if (StringUtils.isNotBlank(domain)) {
//            cookie.setDomain(domain);
//        }

        response.addCookie(cookie);
    }

}
