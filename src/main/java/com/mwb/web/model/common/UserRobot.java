package com.mwb.web.model.common;

import com.mwb.web.model.UserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author mengweibo@kanzhun.com
 * @create 2020/8/21
 */
public class UserRobot {
    public static final UserInfo DEFAULT = new UserInfo(2, "游客猫", "http://img.boqiicdn.com/Data/BK/P/img79491416973896.jpg");
    private static final Map<Long, UserInfo> ROBOT_MAP = new HashMap<>(6);

    static {
        ROBOT_MAP.put(11L, new UserInfo(1, "猫范儿", "http://img.boqiicdn.com/Data/BK/P/imagick60751542248890.jpg"));
        ROBOT_MAP.put(12L, new UserInfo(2, "猫侠归来", "http://img.boqiicdn.com/Data/BK/P/imagick53981542249264.jpg"));
        ROBOT_MAP.put(13L, new UserInfo(3, "喵小猫", "http://img.boqiicdn.com/Data/BK/P/img49891406109364.jpg"));
        ROBOT_MAP.put(14L, new UserInfo(4, "魅力小猫", "http://img.boqiicdn.com/Data/BK/P/imagick5801542249763.jpg"));
        ROBOT_MAP.put(15L, new UserInfo(5, "一根藤上七只猫", "http://img.boqiicdn.com/Data/BK/P/imagick12791473240420.jpg"));
    }

    public static String getImg(long userId){
        if (ROBOT_MAP.containsKey(userId)) {
            return ROBOT_MAP.get(userId).getHeadImg();
        }
        return ROBOT_MAP.get(10L).getHeadImg();
    }

    public static String getName(long userId){
        if (ROBOT_MAP.containsKey(userId)) {
            return ROBOT_MAP.get(userId).getName();
        }
        return ROBOT_MAP.get(10L).getName();
    }
}
