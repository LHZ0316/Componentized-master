package com.lhz.android.libBaseCommon.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhz.android.libBaseCommon.https.widget.LoadingDialog;
import com.lhz.android.libBaseCommon.mvp_senior.factroy.IMvpPresenterFactory;
import com.lhz.android.libBaseCommon.mvp_senior.factroy.IPresenterProxyFactory;
import com.lhz.android.libBaseCommon.mvp_senior.factroy.MvpPresenterFactoryImpl;
import com.lhz.android.libBaseCommon.mvp_senior.factroy.PresenterProxyFactoryImpl;

import io.reactivex.ObservableTransformer;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 想要使用mvp，需要继承此BaseMvpFragment
 * TODO:注意  需要创建 presenter，必需要添加注解 @CreatePresenterAnnotation(xxx.class)
 */

public abstract class BaseMvpFragment<V extends IBaseView, P extends BasePresenter<V>> extends BaseFragment
        implements IPresenterProxyFactory<V, P>, IBaseView {
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";
    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private PresenterProxyFactoryImpl<V, P> mProxy = new PresenterProxyFactoryImpl<>(MvpPresenterFactoryImpl.<V, P>creater(getClass()));

    private LoadingDialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
        mLoadingDialog = new LoadingDialog(getContext());
        mProxy.onCreate((V) this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("perfect-mvp", "V onResume");
//        mProxy.onResume((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("perfect-mvp", "V onDestroy = ");
        mProxy.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp", "V onSaveInstanceState");
        outState.putBundle(PRESENTER_SAVE_KEY, mProxy.onSaveInstanceState());
    }

    @Override
    public void setPresenterFactory(IMvpPresenterFactory<V, P> presenterFactory) {
        Log.e("perfect-mvp", "V setPresenterFactory");
        mProxy.setPresenterFactory(presenterFactory);
    }

    @Override
    public IMvpPresenterFactory<V, P> getPresenterFactory() {
        Log.e("perfect-mvp", "V getPresenterFactory");
        return mProxy.getPresenterFactory();
    }

    @Override
    public P getMvpPresenter() {
        Log.e("perfect-mvp", "V getMvpPresenter");
        return mProxy.getMvpPresenter();
    }

    @Override
    public void showProgress() {
        if (mLoadingDialog != null) {
            mLoadingDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifeycle() {
        return this.bindToLifecycle();
    }
}
