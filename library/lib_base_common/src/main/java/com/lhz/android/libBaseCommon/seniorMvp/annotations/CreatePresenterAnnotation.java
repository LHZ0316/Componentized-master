package com.lhz.android.libBaseCommon.seniorMvp.annotations;


import com.lhz.android.libBaseCommon.base.BasePresenter;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 创建presenter的注解
 * 这个注解表示  只能在类上使用并且是运行时
 *
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CreatePresenterAnnotation {

    Class<? extends BasePresenter> value();
}
