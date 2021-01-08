package com.mwb.web.utils;

import org.springframework.util.DigestUtils;

public class MD5Util {
    private static final String SAFE_PWD = "mwb_pwd_md5_";
    private static final String SAFE = "";

    public static String md5Pwd(String str) {
        return DigestUtils.md5DigestAsHex((SAFE_PWD + str).getBytes());
    }

    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex((SAFE + str).getBytes());
    }

    public static void main(String[] args) {
        //1f18d6d3718c5ed821986fa1dbd50f21
        System.out.println(501566316 % 10 >= 6 && 501566316 % 10 <= 7);
    }

}
