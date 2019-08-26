package com.lhz.android.libBaseCommon.mvp_senior.factroy;

import android.os.Bundle;
import android.util.Log;

import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;


/**
 * lhz  on 2019/8/21.
 */

public class PresenterProxyFactoryImpl<V extends IBaseView, P extends BasePresenter<V>> implements IPresenterProxyFactory<V, P> {
    /**
     * 获取onSaveInstanceState中bundle的key
     */
    private static final String PRESENTER_KEY = "presenter_key";
    /**
     * Presenter工厂类
     */
    private IMvpPresenterFactory<V, P> mFactory;
    private P mPresenter;
    private Bundle mBundle;
    private boolean mIsAttchView;

    public PresenterProxyFactoryImpl(IMvpPresenterFactory<V, P> presenterMvpFactory) {
        this.mFactory = presenterMvpFactory;
    }


    @Override
    public void setPresenterFactory(IMvpPresenterFactory<V, P> presenterFactory) {
        if (mPresenter != null) {
            throw new IllegalArgumentException("这个方法只能在getMvpPresenter()之前调用，如果Presenter已经创建则不能再修改");
        }
        this.mFactory = presenterFactory;
    }

    @Override
    public IMvpPresenterFactory<V, P> getPresenterFactory() {
        return mFactory;
    }

    @Override
    public P getMvpPresenter() {
        if (mFactory != null) {
            if (mPresenter == null) {
                mPresenter = mFactory.createMvpPresenter();
            }
        }
        Log.e("perfect-mvp", "Proxy getMvpPresenter = " + mPresenter);
        return mPresenter;
    }


    /**
     * 绑定Presenter和view
     */
    public void onCreate(V mvpView) {
        getMvpPresenter();
        Log.e("perfect-mvp","Proxy onCreate");
        if (mPresenter != null && !mIsAttchView) {
            mPresenter.attatchView(mvpView);
            mIsAttchView = true;
        }
    }

    /**
     * 销毁Presenter持有的View
     */
    private void onDetachMvpView() {
        Log.e("perfect-mvp","Proxy onDetachMvpView = ");
        if (mPresenter != null && mIsAttchView) {
            mPresenter.detachView();
            mIsAttchView = false;
        }
    }

    /**
     * 销毁Presenter
     */
    public void onDestroy() {
        Log.e("perfect-mvp","Proxy onDestroy = ");
        if (mPresenter != null ) {
            onDetachMvpView();
            mPresenter.onDestroyPersenter();
            mPresenter = null;
        }
    }

    /**
     * 意外销毁的时候调用
     * @return Bundle，存入回调给Presenter的Bundle和当前Presenter的id
     */
    public Bundle onSaveInstanceState() {
        Log.e("perfect-mvp","Proxy onSaveInstanceState = ");
        Bundle bundle = new Bundle();
        getMvpPresenter();
        if(mPresenter != null){
            Bundle presenterBundle = new Bundle();
            //回调Presenter
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putBundle(PRESENTER_KEY,presenterBundle);
        }
        return bundle;
    }

    /**
     * 意外关闭恢复Presenter
     * @param savedInstanceState 意外关闭时存储的Bundler
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("perfect-mvp","Proxy onRestoreInstanceState = ");
        Log.e("perfect-mvp","Proxy onRestoreInstanceState Presenter = " + mPresenter);
        mBundle = savedInstanceState;
    }
}
