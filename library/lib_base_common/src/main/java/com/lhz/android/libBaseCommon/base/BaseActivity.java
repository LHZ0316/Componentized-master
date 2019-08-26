package com.lhz.android.libBaseCommon.base;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.lhz.android.libBaseCommon.eventbus.EventType;
import com.lhz.android.libBaseCommon.utils.Utils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;


/**
 * lhz  on 2019/8/21.
 * Activity 的基类
 * <p>
 * ButterKnife，activity的管理，禁止横屏
 */

@SuppressLint("Registered")
public abstract class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        // 禁止所有的activity横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ViewManager.getInstance().addActivity(this);
        initView(savedInstanceState);
        initData();
        EventBus.getDefault().register(this);
    }

    /**
     * 加载布局
     */
    public abstract int setLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewManager.getInstance().finishActivity(this);
        EventBus.getDefault().unregister(this);
    }

    /**
     * 添加fragment
     */
    protected void addFragment(Fragment fragment, @IdRes int frameId) {
        Utils.checkNotNull(fragment);
        getSupportFragmentManager()
                .beginTransaction()
                .add(frameId, fragment, fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
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

    @Subscribe
    public void onEventType(EventType eventType) {

    }

}
