package com.lhz.android.libBaseCommon.mvp_senior.factroy;

import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 创建Presenter工厂接口
 */

public interface IMvpPresenterFactory<V extends IBaseView, P extends BasePresenter<V>> {
    /**
     * 创建Presenter的接口方法
     *
     * @return 需要创建的Presenter
     */
    P createMvpPresenter();
}
