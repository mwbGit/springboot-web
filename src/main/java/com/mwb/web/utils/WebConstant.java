package com.mwb.web.utils;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/4
 */
public class WebConstant {

    public static final String WAP_COOKIE_NAME = "pet";
    public static final String USER_BASE = "user";
    public static final int TC_EXPIRE_TIME = 60 * 60 * 24 * 7;

    public static final int ACCESS_TOTAL = 800;

    public static void main(String[] args) {
        String title = "http://www.xingquanpet.com/布偶猫";
        System.out.println(title.substring(title.lastIndexOf("/") + 1));
    }
}
