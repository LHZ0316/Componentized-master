package com.xiaodou.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.view.View;

import com.lhz.android.baseUtils.utils.ContextUtils;
import com.lhz.android.libBaseCommon.BuildConfig;
import com.lhz.android.libBaseCommon.base.BaseApi;
import com.lhz.android.libBaseCommon.base.BaseApplication;
import com.lhz.android.libBaseCommon.statelayout.IRPageStatusController;
import com.lhz.android.libBaseCommon.statelayout.RPageStatusManager;
import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatus;
import com.lhz.android.libBaseCommon.statelayout.listener.OnRPageEventListener;
import com.mob.MobSDK;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.activity.DefaultErrorActivity;
import cat.ereza.customactivityoncrash.config.CaocConfig;

/**
 * lhz  on 2019/8/21.
 */

public class MainApplication extends BaseApplication {
    private static MainApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        //初始化api环境
        BaseApi.host(BaseApi.HostType.TEST_1, true);

        // 初始化shareSDK分享
        MobSDK.init(this, "2698513ea990", "33e2857eb692b77079ccd5cc28799eea");

        // SDK初始化热更新，调试时，替换自己的appId，将第三个参数改为true
        Bugly.init(this, "fce6ddb348", BuildConfig.LOG_DEBUG);

        // 日志打印
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.LOG_DEBUG;
            }
        });

        // 初始化工具类
        ContextUtils.init(this);

        // 布局管理
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

        //收集bug崩溃日志
        CaocConfig.Builder.create()
                //程序在后台时，发生崩溃的三种处理方式
                //BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM: //当应用程序处于后台时崩溃，也会启动错误页面，
                //BackgroundMode.BACKGROUND_MODE_CRASH:      //当应用程序处于后台崩溃时显示默认系统错误（一个系统提示的错误对话框），
                //BackgroundMode.BACKGROUND_MODE_SILENT:     //当应用程序处于后台时崩溃，默默地关闭程序！这种模式我感觉最好
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT)
                .enabled(true)     //这阻止了对崩溃的拦截,false表示阻止。用它来禁用customactivityoncrash框架
                .showErrorDetails(true) //这将隐藏错误活动中的“错误详细信息”按钮，从而隐藏堆栈跟踪。
                .showRestartButton(true)    //是否可以重启页面
                .trackActivities(true)     //错误页面中显示错误详细信息
                .minTimeBetweenCrashesMs(2000)      //定义应用程序崩溃之间的最短时间，以确定我们不在崩溃循环中。比如：在规定的时间内再次崩溃，框架将不处理，让系统处理！
                .errorDrawable(R.drawable.ic_logo_application)     //崩溃页面显示的图标
                .errorActivity(DefaultErrorActivity.class) //程序崩溃后显示的页面
                .eventListener(new CustomEventListener())//设置监听
                .apply();
        //如果没有任何配置，程序崩溃显示的是默认的设置
        CustomActivityOnCrash.install(this);
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


    /**
     * 监听程序崩溃/重启
     */
    private static class CustomEventListener implements CustomActivityOnCrash.EventListener {
        //程序崩溃回调
        @Override
        public void onLaunchErrorActivity() {
            Log.d("huangxiaoguo", "程序崩溃回调");
        }

        //重启程序时回调
        @Override
        public void onRestartAppFromErrorActivity() {
            Log.d("huangxiaoguo", "重启程序时回调");
        }

        //在崩溃提示页面关闭程序时回调
        @Override
        public void onCloseAppFromErrorActivity() {
            Log.d("huangxiaoguo", "在崩溃提示页面关闭程序时回调");
        }

    }
}
