package com.mwb.web.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     *
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

    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符
    private static final String regEx_w = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";//定义所有w标签

    /**
     * 删除html标签
     *
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr) {
        if (StringUtils.isBlank(htmlStr)) {
            return null;
        }
        Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
        Matcher m_w = p_w.matcher(htmlStr);
        htmlStr = m_w.replaceAll(""); // 过滤script标签
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll(""); // 过滤script标签
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        htmlStr = htmlStr.replaceAll(" ", ""); //过滤
        return htmlStr;
    }
}
