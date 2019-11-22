package com.lhz.android.libBaseCommon.seniorMvp.factroy;


import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;
import com.lhz.android.libBaseCommon.seniorMvp.annotations.CreatePresenterAnnotation;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 创建presenter
 * 动态 工厂的实现类
 */

public class MvpPresenterFactoryImpl<V extends IBaseView, P extends BasePresenter<V>> implements IMvpPresenterFactory<V, P> {


    private final Class<P> mPresenterClass;

    private MvpPresenterFactoryImpl(Class<P> presenterClass) {
        this.mPresenterClass = presenterClass;
    }


    /**
     * 根据注解创建Presenter的工厂实现类
     *
     * @param aClass 需要创建Presenter的V层实现类
     * @param <V>    当前View实现的接口类型
     * @param <P>    当前要创建的Presenter类型
     * @return 工厂类
     */
    public static <V extends IBaseView, P extends BasePresenter<V>> MvpPresenterFactoryImpl creater(Class<?> aClass) {
        //拿到创建presenter的注解
        CreatePresenterAnnotation annotation = aClass.getAnnotation(CreatePresenterAnnotation.class);
        Class<P> currentPresenter = null;
        if (annotation != null) {
            //获取到具体的presenter
            currentPresenter = (Class<P>) annotation.value();

        }
        //传给有参构造
        return new MvpPresenterFactoryImpl(currentPresenter);
    }


    @Override
    public P createMvpPresenter() {
        try {
            return mPresenterClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("注解为空,请检查类上是否声明了@CreatePresenterAnnotation(xxx,class)注解");
        }
    }
}
