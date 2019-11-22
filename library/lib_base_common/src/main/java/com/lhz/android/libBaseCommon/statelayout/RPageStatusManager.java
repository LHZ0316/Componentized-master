package com.lhz.android.libBaseCommon.statelayout;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatus;
import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatusEvent;
import com.lhz.android.libBaseCommon.statelayout.help.RPageStatusLayoutInfo;
import com.lhz.android.libBaseCommon.statelayout.listener.OnRPageEventListener;
import com.lhz.android.libBaseCommon.statelayout.utils.RPageStatusUtils;

/**
 * ======================================================================
 * <p>
 * 描述：全局管理类，配置全局状态页面
 * <p>
 * ======================================================================
 */
public class RPageStatusManager implements IRPageStatusConfig<RPageStatusManager> {
    private final static RPageStatusManager INSTANCE = new RPageStatusManager();
    final static SparseArray<RPageStatusLayoutInfo> mRPageStatusLayoutArray = new SparseArray<>();

    private RPageStatusManager() {
    }

    public static RPageStatusManager getInstance() {
        return INSTANCE;
    }

    /**
     * 增加状态页面布局
     *
     * @param pageStatus 页面状态 {@link RPageStatus}
     * @param layoutId   布局资源id
     * @return
     */
    @Override
    public RPageStatusManager addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.NO_CLICK);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return INSTANCE;
    }

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
    @Override
    public RPageStatusManager addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int viewId, @Nullable OnRPageEventListener onRPageEventListener) {
        return addPageStatusView(pageStatus, layoutId, viewId, true, onRPageEventListener);
    }

    /**
     * 增加状态页面布局
     *
     * @param pageStatus           页面状态 {@link RPageStatus}
     * @param layoutId             布局资源id
     * @param viewId               布局文件中有点击事件的View的id
     * @param showLoading          点击时是否自动显示成 {@link RPageStatus#LOADING} 状态
     * @param onRPageEventListener 点击事件回调监听
     * @return
     */
    @Override
    public RPageStatusManager addPageStatusView(int pageStatus, int layoutId, int viewId, boolean showLoading, @Nullable OnRPageEventListener onRPageEventListener) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.SINGLE_VIEW_CLICK, viewId, showLoading, onRPageEventListener);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return INSTANCE;
    }

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
    @Override
    public RPageStatusManager addPageStatusView(@RPageStatus int pageStatus, @LayoutRes int layoutId, @IdRes int[] viewIds, @Nullable OnRPageEventListener onRPageEventListener) {
        return addPageStatusView(pageStatus, layoutId, viewIds, true, onRPageEventListener);
    }

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
    @Override
    public RPageStatusManager addPageStatusView(int pageStatus, int layoutId, int[] viewIds, boolean showLoading, @Nullable OnRPageEventListener onRPageEventListener) {
        RPageStatusUtils.checkAddContentStatusPage(pageStatus);
        RPageStatusLayoutInfo rPageStatusLayoutInfo = new RPageStatusLayoutInfo(pageStatus, layoutId, RPageStatusEvent.MORE_VIEW_CLICK, viewIds, showLoading, onRPageEventListener);
        mRPageStatusLayoutArray.put(pageStatus, rPageStatusLayoutInfo);
        return INSTANCE;
    }
}
