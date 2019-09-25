package com.xiaodou.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.view.View;

import com.lhz.android.baseUtils.utils.ContextUtils;
import com.lhz.android.libBaseCommon.base.BaseApi;
import com.lhz.android.libBaseCommon.base.BaseApplication;
import com.lhz.android.libBaseCommon.statelayout.IRPageStatusController;
import com.lhz.android.libBaseCommon.statelayout.RPageStatusManager;
import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatus;
import com.lhz.android.libBaseCommon.statelayout.listener.OnRPageEventListener;
import com.lhz.android.libBaseCommon.utils.Utils;
import com.mob.MobSDK;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.xiaodou.common.widget.lock.LockPatternUtils;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * lhz  on 2019/8/21.
 */

public class MainApplication extends BaseApplication {
    private static MainApplication mApplication;
    private LockPatternUtils mLockPatternUtils;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        //初始化api环境
        BaseApi.host(BaseApi.HostType.TEST_1, true);

        // 初始化shareSDK分享
        MobSDK.init(this, "2698513ea990", "33e2857eb692b77079ccd5cc28799eea");

        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(this, "fce6ddb348", true);

        // 打印日志
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        // 初始化工具类
        ContextUtils.init(this);

        RPageStatusManager.getInstance()
                .addPageStatusView(RPageStatus.LOADING, R.layout.status_view_loading)
                .addPageStatusView(RPageStatus.EMPTY, R.layout.status_view_empty)
                .addPageStatusView(RPageStatus.NET_WORK, R.layout.status_view_network, R.id.ll_net_work, null)
                .addPageStatusView(RPageStatus.ERROR, R.layout.status_view_error, R.id.ll_error, new OnRPageEventListener() {
                    @Override
                    public void onViewClick(@NonNull IRPageStatusController iRPageStatusController, @RPageStatus int pageStatus, @NonNull Object object, @NonNull View view, int viewId) {
                        Log.i("MyApplication", "全局配置加载错误监听: iRPageStatusController = [" + iRPageStatusController + "]," +
                                " pageStatus = [" + pageStatus + "], object = [" + object + "], view = [" + view + "], viewId = [" + viewId + "]");
                    }
                });
    }

    /**
     * bugly热修复
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker();
    }


    /**
     * 利用单例模式获取Application实例
     *
     * @return
     */
    public static MainApplication getInstance() {
        if (null == mApplication) {
            mApplication = new MainApplication();
        }
        return mApplication;
    }

    public LockPatternUtils getLockPatternUtils() {
        if (mLockPatternUtils == null) {
            mLockPatternUtils = new LockPatternUtils(mApplication);
        }
        return mLockPatternUtils;
    }
}
