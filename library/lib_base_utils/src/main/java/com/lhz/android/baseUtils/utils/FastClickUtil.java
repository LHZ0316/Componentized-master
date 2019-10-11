package com.lhz.android.baseUtils.utils;

/**
 * lhz  on 2019/8/21.
 * 让按钮一秒内，只执行一次点击事件
 */

public class FastClickUtil {

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}
