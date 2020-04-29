package com.quaint.shop.common.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * desc: 用户上下文环境
 * </p>
 *
 * @author quaint
 * @since 29 April 2020
 */
public class UserContext {

    private static ThreadLocal<Map<String,Object>> mapThreadLocal = new ThreadLocal<>();

    /**
     * 用户id
     */
    private static final String USER_ID = "user_id";


    public static Long getUserId() {
        return getValue(USER_ID);
    }

    public static void setUserId(Long userId) {
        setValue(USER_ID, userId);
    }



    private static Map<String, Object> getMap() {
        Map<String, Object> map = mapThreadLocal.get();
        if (map == null) {
            map = new HashMap<>(8);
            mapThreadLocal.set(map);
        }
        return map;
    }

    public static void setValue(String key, Object val) {
        getMap().put(key, val);
    }

    public static <T> T getValue(String key) {
        Object val = getMap().get(key);
        return val == null ? null : (T) val;
    }

    public static void clean() {
        mapThreadLocal.remove();
    }

}
