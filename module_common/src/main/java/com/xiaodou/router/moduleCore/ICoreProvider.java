package com.xiaodou.router.moduleCore;

import android.app.Activity;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * 项目名称：MvpFrame
 * 创建人：LHZ
 * 创建时间：2019/11/26 17:53
 * 类描述：核心组件对其它组件提供的方法（组件间的通信方式）
 */
public interface ICoreProvider extends IProvider {
    void goToLogin(Activity activity);
}
