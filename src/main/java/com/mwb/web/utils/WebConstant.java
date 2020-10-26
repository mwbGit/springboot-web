package com.mwb.web.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/4
 */
public class WebConstant {

    public static final String WAP_COOKIE_NAME = "pet";
    public static final String USER_BASE = "user";
    public static final int TC_EXPIRE_TIME = 60 * 60 * 24 * 15;
    public static final int ACCESS_TOTAL = 800;

    private static final String WATERMARK = "?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,size_20,text_54yr5ZKq5LmL5a62,color_FFFFFF,shadow_50,t_100,g_se,x_10,y_10";

    /**
     * 水印图片
     *
     * @return
     * @paramimage
     */
    public static String getWaterImage(String image) {
        if (StringUtils.isNoneBlank(image) && !image.contains("?") && image.contains("xwz")) {
            return image + WATERMARK;
        }
        return image;
    }

    /**
     * sql注入检测
     * @param str
     * @return
     */
    public static boolean checkSql(String str) {
        String ruleStr = "'_and_exec_insert_select_delete_update_count_*_%_chr_mid_master_truncate_char_declare_;_or_-_+_,_union";
        String ruleStrs[] = ruleStr.split("_");
        for (int i = 0; i < ruleStrs.length; i++) {
            if (str.contains(ruleStrs[i])) {
                return false;
            }
        }
        return true;
    }
}
