package com.lhz.android.libBaseCommon.statelayout.annotation;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * ======================================================================
 * <p>
 * 描述：页面状态注解
 * <p>
 * ======================================================================
 */
@Retention(RetentionPolicy.SOURCE)
@IntDef({
        RPageStatus.LOADING,
        RPageStatus.EMPTY,
        RPageStatus.CONTENT,
        RPageStatus.NET_WORK,
        RPageStatus.ERROR,
        RPageStatus.NOT_FOUND,
})
public @interface RPageStatus {
    /**
     * 加载中状态
     */
    int LOADING = 100;
    /**
     * 空状态
     */
    int EMPTY = 101;
    /**
     * 内容状态
     */
    int CONTENT = 102;
    /**
     * 网络错误状态
     */
    int NET_WORK = 103;
    /**
     * 加载错误状态
     */
    int ERROR = 104;
    /**
     * 没找到内容页面状态
     */
    int NOT_FOUND = 105;
}
