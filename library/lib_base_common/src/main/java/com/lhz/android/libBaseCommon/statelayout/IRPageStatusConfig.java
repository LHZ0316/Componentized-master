package com.lhz.android.libBaseCommon.statelayout;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatus;
import com.lhz.android.libBaseCommon.statelayout.listener.OnRPageEventListener;

/**
 * ======================================================================
 * <p>
 * 描述：增加配置信息接口
 * <p>
 * ======================================================================
 */
public interface IRPageStatusConfig<T extends IRPageStatusConfig> {
    /**
     * 增加状态页面布局
     *
     * @param pageStatus 页面状态 {@link RPageStatus}
     * @param layoutId   布局资源id
     * @return
     */
    T addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId);

    /**
     * 增加状态页面布局，点击时会将页面修改为 {@link RPageStatus#LOADING} 状态，
     * 如果不需要修改，请调用 {@link #addPageStatusView(int, int, int, boolean, OnRPageEventListener)} 方法修改
     *
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param layoutId             布局资源id
     * @param viewId               布局文件中有点击事件的View的id
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    T addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int viewId, @Nullable OnRPageEventListener onRPageEventListener);

    /**
     * 增加状态页面布局
     *
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param layoutId             布局资源id
     * @param showLoading          点击时是否自动显示成 {@link RPageStatus#LOADING} 状态
     * @param viewId               布局文件中有点击事件的View的id
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    T addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int viewId, boolean showLoading, @Nullable OnRPageEventListener onRPageEventListener);


    /**
     * 增加状态页面布局，点击时会将页面修改为 {@link RPageStatus#LOADING} 状态，
     * 如果不需要修改，请调用 {@link #addPageStatusView(int, int, int[], boolean, OnRPageEventListener)} 方法修改
     *
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param layoutId             布局资源id
     * @param viewIds              布局文件中有点击事件的View的id集合
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    T addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int[] viewIds, @Nullable OnRPageEventListener onRPageEventListener);

    /**
     * 增加状态页面布局
     *
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param layoutId             布局资源id
     * @param viewIds              布局文件中有点击事件的View的id集合
     * @param showLoading          点击时是否自动显示成 {@link RPageStatus#LOADING} 状态
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    T addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int[] viewIds, boolean showLoading, @Nullable OnRPageEventListener onRPageEventListener);
}
