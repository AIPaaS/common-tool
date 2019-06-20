package com.ai.paas.util;

import java.util.List;
import java.util.Map;

/**
 * 判断参数的工具类
 * 
 * @author douxiaofeng
 *
 */
public abstract class Assert {

    private static final String ARG_IS_NULL = "Argument(s) is null, Pls. check!";

    private static final String ARG_IS_EMPTY = "Argument(s) is empty, Pls. check!";

    protected Assert() {
    }

    /**
     * 判断参数是否为空
     * 
     * @param obj
     * @param message
     */
    public static void notNull(Object obj) {
        notNull(obj, ARG_IS_NULL);
    }

    public static void notNull(Object obj1, Object obj2) {
        notNull(obj1, ARG_IS_NULL);
        notNull(obj2, ARG_IS_NULL);
    }

    public static void notNull(Object obj1, Object obj2, Object obj3) {
        notNull(obj1, ARG_IS_NULL);
        notNull(obj2, ARG_IS_NULL);
        notNull(obj3, ARG_IS_NULL);
    }

    /**
     * 判断参数是否为空
     * 
     * @param obj
     * @param message
     */
    public static void notNull(Object obj, String message) {
        if (obj == null || (obj instanceof String && StringUtil.isBlank(obj.toString()))) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断列表是否为空
     * 
     * @param objs
     */
    public static void notNull(@SuppressWarnings("rawtypes") List collection) {
        notNull(collection, ARG_IS_NULL);
    }

    public static void notEmpty(@SuppressWarnings("rawtypes") List collection) {
        notNull(collection, ARG_IS_NULL);
        if (collection.isEmpty())
            throw new IllegalArgumentException(ARG_IS_EMPTY);
    }

    /**
     * 判断列表是否为空
     * 
     * @param objs
     * @param message
     */
    public static void notNull(@SuppressWarnings("rawtypes") List objs, String message) {
        if (objs == null || objs.isEmpty())
            throw new IllegalArgumentException(message);
    }

    /**
     * 判断map是否为空
     * 
     * @param objs
     */
    public static void notNull(@SuppressWarnings("rawtypes") Map objs) {
        notNull(objs, ARG_IS_NULL);
    }

    /**
     * 判断map是否为空
     * 
     * @param objs
     * @param message
     */
    public static void notNull(@SuppressWarnings("rawtypes") Map objs, String message) {
        if (objs == null || objs.size() <= 0)
            throw new IllegalArgumentException(message);
    }

    /**
     * 判断数组是否为空
     * 
     * @param objs
     */
    public static void notNull(Object[] objs) {
        notNull(objs, ARG_IS_NULL);
    }

    /**
     * 判断数组是否为空
     * 
     * @param objs
     * @param message
     */
    public static void notNull(Object[] objs, String message) {
        if (objs == null || objs.length <= 0)
            throw new IllegalArgumentException(message);
    }
}
