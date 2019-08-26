package com.lhz.android.libBaseUtils.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lhz on 2015/11/17.
 * 数字转换辅助类
 */
public class NumberConvert {
    /**
     * @param value
     * @param defaultValue
     * @return integer
     * @throws
     * @Description: 对象转化为int类型
     */
    public final static int convertToInt(Object value, int defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Integer.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

    /**
     * @param value
     * @param defaultValue
     * @return float
     * @throws
     * @Description: 对象转化为float类型
     */
    public final static float convertToFloat(Object value, float defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Float.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }

    /**
     * @param value
     * @param defaultValue
     * @return float
     * @throws
     * @Description: 对象转化为float类型
     */
    public final static double convertToDouble(Object value, Double defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            return defaultValue;
        }
        try {
            return Double.valueOf(value.toString());
        } catch (Exception e) {
            try {
                return Double.valueOf(value.toString()).intValue();
            } catch (Exception e1) {
                return defaultValue;
            }
        }
    }


    /**
     * @param value
     * @param defaultValue
     * @return long
     * @throws
     * @Description: 对象转化为long类型
     */
    public final static long convertToLong(Object value, Long defaultValue) {
        if (value == null || "".equals(value.toString().trim())) {
            Logger log = Logger.getLogger("NumberConvert");
            log.setLevel(Level.INFO);
            log.info(String.valueOf(defaultValue));
            return defaultValue;
        }
        try {
            return Long.valueOf(value.toString());
        } catch (Exception e) {
            Logger log = Logger.getLogger("NumberConvert");
            log.setLevel(Level.INFO);
            log.info(e.toString());
            return defaultValue;
        }
    }
}
