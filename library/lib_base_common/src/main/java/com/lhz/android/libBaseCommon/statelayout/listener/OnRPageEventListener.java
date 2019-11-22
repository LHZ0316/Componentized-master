package com.lhz.android.libBaseCommon.statelayout.listener;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.View;

import com.lhz.android.libBaseCommon.statelayout.IRPageStatusController;
import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatus;

/**
 * ======================================================================
 * <p>
 * 描述：事件监听回调接口
 * <p>
 * ======================================================================
 */
public interface OnRPageEventListener {
    void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @RPageStatus int pageStatus,
                     @NonNull Object object, @NonNull View view, @IdRes int viewId);
}
