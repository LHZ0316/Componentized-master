package com.xiaodou.module_my.base;


import com.lhz.android.libBaseCommon.base.BaseMvpFragment;
import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;

/**
 * @author lhz
 * @version 1.0.0
 * @date 2018/5/7
 * @description
 */

public abstract class BaseMyAssetsFragment<V extends IBaseView, P extends BasePresenter<V>> extends BaseMvpFragment<V, P> {
}
