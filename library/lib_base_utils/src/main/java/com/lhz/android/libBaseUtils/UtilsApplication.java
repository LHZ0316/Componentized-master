package com.lhz.android.libBaseUtils;

import android.app.Application;

import com.lhz.android.libBaseUtils.utils.ContextUtils;

/**
 * lhz  on 2019/8/21.
 */

public class UtilsApplication extends Application {
    private static UtilsApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        ContextUtils.init(this);
    }

    /**
     * 利用单例模式获取Application实例
     *
     * @return
     */
    public static UtilsApplication getInstance() {
        if (null == mApplication) {
            mApplication = new UtilsApplication();
        }
        return mApplication;
    }

}
