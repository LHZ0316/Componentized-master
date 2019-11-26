package com.xiaodou.module_task.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lhz.android.libBaseCommon.base.BaseMvpActivity;
import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;
import com.lhz.android.baseUtils.utils.StatusBar;


/**
 * lhz  on 2019/8/21.
 * <p>
 * 产品基类
 */

public abstract class BaseProductActivity<V extends IBaseView, P extends BasePresenter<V>> extends BaseMvpActivity<V, P> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBar.setTransparent(this);//沉趁式  透明状态栏

    }
}
