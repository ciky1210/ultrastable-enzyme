package com.hnlg.enzyme.constant;

/**
 * redis常量
 */
public class RedisConstant {

    /**
     * 分隔符
     */
    private final static String splitChar = ":";

    /**
     * 获取用户token key
     *
     * @param uid 用户id
     * @return
     */
    public static String getStaffKey(Long uid) {
        return "user" + splitChar + uid;
    }
}
