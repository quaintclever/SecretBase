package com.quaint.shop.member.helper;

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
public class MemberContext {

    private static ThreadLocal<Map<String,Object>> mapThreadLocal = new ThreadLocal<>();

    /**
     * 用户id
     */
    private static final String MEMBER_ID = "member_id";


    public static Long getMemberId() {
        return getValue(MEMBER_ID);
    }

    public static void setMemberId(Long memberId) {
        setValue(MEMBER_ID, memberId);
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
