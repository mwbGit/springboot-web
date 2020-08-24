package com.mwb.web.utils;

import org.springframework.util.DigestUtils;

public class MD5Util {
    private static final String SAFE_PWD = "mwb_pwd_md5_";
    private static final String SAFE = "mwb_md5_";

    public static String md5Pwd(String str) {
        return DigestUtils.md5DigestAsHex((SAFE_PWD + str).getBytes());
    }

    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex((SAFE + str).getBytes());
    }

    public static void main(String[] args) {
        System.out.println(md5Pwd("admin"));
    }

}
