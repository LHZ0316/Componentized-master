package com.lhz.android.libBaseCommon.statelayout.help;

import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.LayoutRes;

import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatus;
import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatusEvent;
import com.lhz.android.libBaseCommon.statelayout.listener.OnRPageEventListener;

/**
 * ======================================================================
 * <p>
 * 描述：状态页面信息封装
 * <p>
 * ======================================================================
 */
public class RPageStatusLayoutInfo {
    @RPageStatus
    @IntRange(from = 0, to = 5)
    public int pageStatus;
    @LayoutRes
    public int layoutId;
    @RPageStatusEvent
    public int rPageStatusEvent;
    @IdRes
    public int viewId;
    public int[] viewIds;
    public boolean showLoading;
    public OnRPageEventListener onRPageEventListener;

    public RPageStatusLayoutInfo(RPageStatusLayoutInfo rPageStatusLayoutInfo) {
        if (null != rPageStatusLayoutInfo) {
            this.pageStatus = rPageStatusLayoutInfo.pageStatus;
            this.layoutId = rPageStatusLayoutInfo.layoutId;
            this.rPageStatusEvent = rPageStatusLayoutInfo.rPageStatusEvent;
            this.viewId = rPageStatusLayoutInfo.viewId;
            this.viewIds = rPageStatusLayoutInfo.viewIds;
            this.showLoading = rPageStatusLayoutInfo.showLoading;
            this.onRPageEventListener = rPageStatusLayoutInfo.onRPageEventListener;
        }
    }

    public RPageStatusLayoutInfo(@RPageStatus int pageStatus, @LayoutRes int layoutId, @RPageStatusEvent int rPageStatusEvent) {
        this.pageStatus = pageStatus;
        this.layoutId = layoutId;
        this.rPageStatusEvent = rPageStatusEvent;
    }

    public RPageStatusLayoutInfo(@RPageStatus int pageStatus, @LayoutRes int layoutId, @RPageStatusEvent int rPageStatusEvent, @IdRes int viewId, boolean showLoading, OnRPageEventListener onRPageEventListener) {
        this.pageStatus = pageStatus;
        this.layoutId = layoutId;
        this.rPageStatusEvent = rPageStatusEvent;
        this.viewId = viewId;
        this.showLoading = showLoading;
        this.onRPageEventListener = onRPageEventListener;
    }

    public RPageStatusLayoutInfo(@RPageStatus int pageStatus, @LayoutRes int layoutId, @RPageStatusEvent int rPageStatusEvent, @IdRes int[] viewIds, boolean showLoading, OnRPageEventListener onRPageEventListener) {
        this.pageStatus = pageStatus;
        this.layoutId = layoutId;
        this.rPageStatusEvent = rPageStatusEvent;
        this.viewIds = viewIds;
        this.showLoading = showLoading;
        this.onRPageEventListener = onRPageEventListener;
    }
}
