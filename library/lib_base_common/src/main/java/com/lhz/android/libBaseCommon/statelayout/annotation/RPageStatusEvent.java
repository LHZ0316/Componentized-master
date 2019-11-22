package com.lhz.android.libBaseCommon.statelayout.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ======================================================================
 * <p>
 * 描述：状态页面点击事件记录
 * <p>
 * ======================================================================
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        RPageStatusEvent.NO_CLICK,
        RPageStatusEvent.SINGLE_VIEW_CLICK,
        RPageStatusEvent.MORE_VIEW_CLICK,
})
public @interface RPageStatusEvent {
    /**
     * 没有点击事件
     */
    int NO_CLICK = 0;
    /**
     * 一个View有点击事件
     */
    int SINGLE_VIEW_CLICK = 1;
    /**
     * 多个View有点击事件
     */
    int MORE_VIEW_CLICK = 2;
}
