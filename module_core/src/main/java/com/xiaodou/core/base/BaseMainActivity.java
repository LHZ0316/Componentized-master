package com.xiaodou.core.base;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RemoteViews;

import com.lhz.android.libBaseCommon.base.BaseMvpActivity;
import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;
import com.lhz.android.libBaseCommon.utils.Utils;
import com.lhz.android.libBaseUtils.utils.DownLoadUtil;
import com.lhz.android.libBaseUtils.utils.StatusBar;
import com.lhz.android.libBaseUtils.utils.ToastUtils;
import com.xiaodou.common.R;

import java.io.File;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 适配MainActivity当中的Fragment沉趁式透明状态栏
 */

public abstract class BaseMainActivity<V extends IBaseView, P extends BasePresenter<V>> extends BaseMvpActivity<V, P> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 无标题
        setHaveTitle(false);
        super.onCreate(savedInstanceState);
        //设置沉浸式，透明状态栏,针对一个activity对多个fragment
        StatusBar.setTransparent(this);
    }

    /**
     * 添加fragment
     */
    protected void addFragment(Fragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .show(fragment)
                .commit();
    }

    /**
     * 替换fragment
     */
    protected void replaceFragment(Fragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(frameId, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    /**
     * 显示fragment
     */
    protected void showFragment(Fragment fragment) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .show(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 隐藏fragment
     */
    protected void hideFragment(Fragment fragment) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .hide(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 移出fragment
     */
    protected void removeFragment(Fragment fragment) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commitAllowingStateLoss();
    }

    /**
     * 弹出栈顶部的Fragment
     */
    protected void popFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    /**
     * 隐藏虚拟按键，并且全屏
     */
    public static void hideBottomNav(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(0);
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    /**
     * 重新显示导航栏和状态栏
     */
    public static void showBottomNav(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(0);
    }

    /**
     * 沉浸式全屏状态下，显示导航栏和状态栏
     */
    public static void showBottomNavImmerseBar(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(0);
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * 全屏，显示导航栏和状态栏
     */
    @RequiresApi(api = 28)
    public void showFullScreenModel(Activity activity) {
        activity.requestWindowFeature(activity.getWindow().FEATURE_NO_TITLE);
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        activity.getWindow().setAttributes(lp);
        View decorView = activity.getWindow().getDecorView();
        decorView.setSystemUiVisibility(0);
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

}
