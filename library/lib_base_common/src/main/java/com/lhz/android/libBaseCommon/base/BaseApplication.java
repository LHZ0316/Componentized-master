package com.lhz.android.libBaseCommon.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lhz.android.libBaseCommon.utils.Utils;

/**
 * lhz  on 2019/8/21.
 */

public class BaseApplication extends Application {
    private static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
        Utils.init(this);
    }

    /**
     * 利用单例模式获取Application实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        if (null == mApplication) {
            mApplication = new BaseApplication();
        }
        return mApplication;
    }

}
