package com.mwb.web.utils;

import org.springframework.util.DigestUtils;

public class MD5Util {
    private static final String SAFE = "mwb_md5_";

    public static String md5(String str) {
        return DigestUtils.md5DigestAsHex((SAFE + str).getBytes());
    }

}
