package com.xiaodou.core.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lhz.android.libBaseCommon.base.BaseMvpActivity;
import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;
import com.xiaodou.common.R;

/**
 * lhz  on 2019/8/21.
 */

public class SplashActivity<V extends IBaseView, P extends BasePresenter<V>> extends BaseMvpActivity<V, P> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }


}
