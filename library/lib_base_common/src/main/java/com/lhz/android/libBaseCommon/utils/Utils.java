package com.lhz.android.libBaseCommon.utils;

/**
 * <p>Utils初始化相关 </p>
 */
public class Utils {


    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断对象是否为空
     */
    public static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }
}