package com.lhz.android.libBaseCommon.statelayout.help;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.lhz.android.libBaseCommon.statelayout.IRPageStatusController;
import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatus;


/**
 * ======================================================================
 * <p>
 * 描述：
 * <p>
 * ======================================================================
 */
public class RPageStatusHelp {
    // 绑定信息
    private RPageStatusBindInfo mRPageStatusBindInfo;
    private RPageStatusLayout mRPageStatusLayout;

    public RPageStatusHelp(@NonNull Context context, @NonNull IRPageStatusController irPageStatusController, @NonNull Object object, @Nullable ViewGroup parentView, @NonNull View targetView) {
        mRPageStatusBindInfo = new RPageStatusBindInfo(object, parentView, targetView);
        mRPageStatusLayout = new RPageStatusLayout(context,irPageStatusController);
    }

    public void bindActivity() {
        mRPageStatusLayout.bindActivity(mRPageStatusBindInfo);
    }

    public View bindFragment() {
        return mRPageStatusLayout.bindFragment(mRPageStatusBindInfo);
    }

    public View bindFragmentSupport() {
        return mRPageStatusLayout.bindFragmentSupport(mRPageStatusBindInfo);
    }

    public void bindView() {
        mRPageStatusLayout.bindView(mRPageStatusBindInfo);
    }

    public void changePageStatus(@RPageStatus int pageStatus, @NonNull SparseArray<RPageStatusLayoutInfo> rPageStatusLayoutInfoSparseArray) {
        mRPageStatusLayout.changePageStatus(pageStatus, rPageStatusLayoutInfoSparseArray);
    }

    public int getCurrentPageStatus() {
        return mRPageStatusLayout.getCurrentPageStatus();
    }
}
