package com.lhz.android.libBaseCommon.base;

import io.reactivex.ObservableTransformer;

/**
 * lhz  on 2019/8/21.
 * <p>
 * View接口的基类
 */

public interface IBaseView {
    /**
     * 用来 绑定view 生命周期，解决rxjava内存泄露
     *
     * @param
     * @return
     */
    <T> ObservableTransformer<T, T> bindLifeycle();

    /**
     * 显示正在加载
     */
    void showProgress();

    /**
     * 隐藏正在加载
     */
    void hideProgress();

}
