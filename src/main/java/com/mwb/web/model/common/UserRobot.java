package com.mwb.web.model.common;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/21
 */
public class UserRobot {
    public static final String DEFAULT_AVATAR = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2078562713,2600960194&fm=26&gp=0.jpg";
    public static final String DEFAULT_NAME = "萌宠游客";
    private static final Map<Integer, String> NAME_MAP = new HashMap<>(3);
    private static final Map<Integer, String> IMG_MAP = new HashMap<>(3);

    static {
        NAME_MAP.put(1, "只宠你");
        NAME_MAP.put(2, "宠爱一员");
        NAME_MAP.put(3, "着耳朵");
        IMG_MAP.put(1, "https://imgs-xwz.coohua.com/20200819/1d38b3875aeeeb1a06ad9a1f8e2a522f.jpg");
        IMG_MAP.put(2, "https://imgs-xwz.coohua.com/20200819/1d38b3875aeeeb1a06ad9a1f8e2a522f.jpg");
        IMG_MAP.put(3, "https://imgs-xwz.coohua.com/20200819/1d38b3875aeeeb1a06ad9a1f8e2a522f.jpg");
    }

    public static String getImg(int type){
        return StringUtils.defaultString(IMG_MAP.get(type), IMG_MAP.get(0));
    }

    public static String getName(int type){
        return StringUtils.defaultString(NAME_MAP.get(type), NAME_MAP.get(0));
    }
}
