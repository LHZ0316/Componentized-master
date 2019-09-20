package com.xiaodou.core.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lhz.android.libBaseCommon.base.RouterPath;
import com.lhz.android.libBaseCommon.mvp_senior.annotations.CreatePresenterAnnotation;
import com.lhz.android.baseUtils.utils.ScreenUtil;
import com.lhz.android.baseUtils.utils.StatusBar;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.xiaodou.core.base.BaseMainActivity;
import com.xiaodou.core.contract.IMainContract;
import com.xiaodou.core.presenter.LaunchPresenter;
import com.xiaodou.common.R;

import java.lang.ref.WeakReference;

/**
 * lhz  on 2019/8/21.
 */
@CreatePresenterAnnotation(LaunchPresenter.class)
@Route(path = RouterPath.MAIN_ACTIVITY)
public class LaunchActivity extends BaseMainActivity<IMainContract.LaunchView, LaunchPresenter>
        implements IMainContract.LaunchView {
    private int time = 1;
    private myHandler myHandler;

    /**
     * 匿名内部类
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            time--;
            //            txt.setText(time + "s");
            if (time == 0) {
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                finish();
                handler.removeMessages(0);
            }
            handler.sendEmptyMessageDelayed(0, 1000);
        }
    };

    /**
     * 静态内部类 + 弱引用
     */
    private static class myHandler extends Handler {
        private final WeakReference<LaunchActivity> mActivity;

        public myHandler(LaunchActivity activity) {
            mActivity = new WeakReference<LaunchActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LaunchActivity activity = mActivity.get();
            if (activity != null) {
                activity.time--;
//            txt.setText(time + "s");
                if (activity.time == 0) {
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    activity.finish();
                    activity.myHandler.removeMessages(0);
                }
                activity.myHandler.sendEmptyMessageDelayed(0, 500);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 适配刘海屏
        if (Build.VERSION.SDK_INT >= 28) {
            showFullScreenModel(this);
        } else {
            hideBottomNav(this);
        }
    }


    @Override
    public LaunchActivity getThis() {
        return this;
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        myHandler = new myHandler(this);
        myHandler.sendEmptyMessageDelayed(0, 500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myHandler != null) {
            myHandler.removeCallbacksAndMessages(null);
            myHandler = null;
        }
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}
