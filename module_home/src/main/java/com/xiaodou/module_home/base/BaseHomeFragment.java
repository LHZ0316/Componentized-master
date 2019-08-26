package com.xiaodou.module_home.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhz.android.libBaseCommon.base.BaseMvpFragment;
import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;

/**
 * lhz  on 2019/8/21.
 * home模块fragment  基类
 * 问题：为什么要有这个呢
 * <p>
 * 答：主要是为了扩展，方便维护
 */

public abstract class BaseHomeFragment<V extends IBaseView, P extends BasePresenter<V>> extends BaseMvpFragment<V, P> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

    }
}
