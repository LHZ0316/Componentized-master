package com.lhz.android.libBaseCommon.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lhz.android.libBaseCommon.https.widget.LoadingDialog;
import com.lhz.android.libBaseCommon.mvp_senior.factroy.IMvpPresenterFactory;
import com.lhz.android.libBaseCommon.mvp_senior.factroy.IPresenterProxyFactory;
import com.lhz.android.libBaseCommon.mvp_senior.factroy.MvpPresenterFactoryImpl;
import com.lhz.android.libBaseCommon.mvp_senior.factroy.PresenterProxyFactoryImpl;

import io.reactivex.ObservableTransformer;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 想要使用mvp，需要继承此BaseMvpActivity
 * <p>
 * TODO:注意  需要创建 presenter，必需要添加注解 @CreatePresenterAnnotation(xxx.class)
 */

public abstract class BaseMvpActivity<V extends IBaseView, P extends BasePresenter<V>> extends BaseActivity
        implements IPresenterProxyFactory<V, P>, IBaseView {
    private static final String PRESENTER_SAVE_KEY = "presenter_save_key";


    /**
     * 绑定生命周期
     */
    @Override
    public <T> ObservableTransformer<T, T> bindLifeycle() {
        return this.bindToLifecycle();
    }


    /**
     * 创建被代理对象,传入默认Presenter的工厂
     */
    private PresenterProxyFactoryImpl<V, P> mProxy = new PresenterProxyFactoryImpl<>(MvpPresenterFactoryImpl.<V, P>creater(getClass()));
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mProxy.onCreate((V) this);
        if (savedInstanceState != null) {
            mProxy.onRestoreInstanceState(savedInstanceState.getBundle(PRESENTER_SAVE_KEY));
        }
        mLoadingDialog = new LoadingDialog(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("perfect-mvp", "V onResume");
//        mProxy.onResume((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("perfect-mvp", "V onDestroy = ");
        mProxy.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
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

}
