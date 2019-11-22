package com.lhz.android.libBaseCommon.seniorMvp.factroy;

import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 提供get,set工厂方法
 * <p>
 * 目的为了扩展，可以自定义工厂，创建不同的presenter
 */

public interface IPresenterProxyFactory<V extends IBaseView, P extends BasePresenter<V>> {

    /**
     * 设置创建Presenter的工厂
     *
     * @param presenterFactory PresenterFactory类型
     */
    void setPresenterFactory(IMvpPresenterFactory<V, P> presenterFactory);

    /**
     * 获取Presenter的工厂类
     *
     * @return 返回PresenterMvpFactory类型
     */
    IMvpPresenterFactory<V, P> getPresenterFactory();

    /**
     * 获取创建的Presenter
     *
     * @return 指定类型的Presenter
     */
    P getMvpPresenter();
}
