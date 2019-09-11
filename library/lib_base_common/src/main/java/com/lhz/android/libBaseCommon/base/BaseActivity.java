package com.lhz.android.libBaseCommon.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lhz.android.libBaseCommon.R;
import com.lhz.android.libBaseCommon.eventbus.EventType;
import com.lhz.android.libBaseCommon.utils.StatusBarUtil;
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
    /**
     * 是否禁止旋转屏幕
     **/
    private boolean isAllowScreenRotate = true;

    private boolean isHaveTitle = true;
    /**
     * 标题栏
     */
    protected TextView mTitleBarName, mTitleBarLeft, mTitleBarRight, mTitleBarMore;
    protected RelativeLayout mBaseTitle;
    protected int mResultCode = RESULT_CANCELED;
    private View mView;
    private View mContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置标题
        if (isHaveTitle) {
            mView = initContentView();
            setContentView(mView); // 设置布局。
        } else {
            setContentView(setLayoutId());// 设置布局。
        }
        ButterKnife.bind(this);
        // 禁止所有的activity横屏
        if (isAllowScreenRotate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        ViewManager.getInstance().addActivity(this);
        // 设置状态栏颜色
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
        initView(savedInstanceState);
        initData();
        EventBus.getDefault().register(this);
    }

    /**
     * 初始化标题栏的view
     */
    protected View initContentView() {
        if (null == mView) {
            mContentView = LayoutInflater.from(this).inflate(R.layout.activity_base, null);
            FrameLayout mBaseContent = (FrameLayout) mContentView.findViewById(R.id.base_content);
            mBaseContent.addView(View.inflate(this, setLayoutId(), null));
            mBaseTitle = (RelativeLayout) mContentView.findViewById(R.id.base_title);
            mTitleBarName = (TextView) mContentView.findViewById(R.id.title_bar_name);
            mTitleBarLeft = (TextView) mContentView.findViewById(R.id.title_bar_left);
            mTitleBarRight = (TextView) mContentView.findViewById(R.id.title_bar_right);
            mTitleBarMore = (TextView) mContentView.findViewById(R.id.title_bar_more);

            mTitleBarLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onTitleBarClickLeft(view);
                }
            });
            mTitleBarRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onTitleBarClickRight(view);
                }
            });
            mTitleBarMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onTitleBarClickMore(view);
                }
            });
        }
        return mContentView;
    }

    /**
     * 是否展示标题栏
     */
    public void setHaveTitle(boolean haveTitle) {
        isHaveTitle = haveTitle;
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
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 移除栈
        ViewManager.getInstance().finishActivity(this);
        // 注销eventBus
        EventBus.getDefault().unregister(this);
    }


    /**
     * 设置TextView的left图片（不添加文字）
     *
     * @param textView 控件
     * @param resIds   资源ID
     */
    public void setDrawableLeft(TextView textView, int resIds) {
        textView.setVisibility(TextView.VISIBLE);
        textView.setText("");
        Drawable drawable = getResources().getDrawable(resIds);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        textView.setCompoundDrawables(drawable, null, null, null);
    }

    /**
     * 设置TextView的right图片（不添加文字）
     *
     * @param textView 控件
     * @param resIds   资源ID
     */
    public void setDrawableRight(TextView textView, int resIds) {
        textView.setVisibility(TextView.VISIBLE);
        textView.setText("");
        Drawable drawable = getResources().getDrawable(resIds);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        textView.setCompoundDrawables(null, null, drawable, null);
    }

    /**
     * 设置TextView颜色
     *
     * @param textView 控件
     * @param resId    资源ID
     */
    public void setTextColor(TextView textView, int resId) {
        textView.setTextColor(getResources().getColor(resId));
    }

    /**
     * 设置TextView文字
     *
     * @param textView 控件
     * @param resId    资源ID
     */
    public void setTextTitle(TextView textView, int resId) {
        textView.setText(getResources().getString(resId));
    }

    /**
     * 设置TextView背景
     *
     * @param view  控件
     * @param resId 资源ID
     */
    public void setTitleBackground(View view, int resId) {
        view.setBackgroundColor(getResources().getColor(resId));
    }

    /**
     * 设置屏幕是否可以旋转
     */
    public void setScreenRoate(boolean isAllowScreenRotate) {
        this.isAllowScreenRotate = isAllowScreenRotate;
    }


    /**
     * TitleBar左边按钮点击事件
     *
     * @param view 控件
     */
    protected void onTitleBarClickLeft(View view) {
        setResult(mResultCode);
        finish();
    }

    /**
     * TitleBar右边按钮点击事件
     *
     * @param view 控件
     */
    protected void onTitleBarClickRight(View view) {

    }

    /**
     * TitleBar右边（更多）按钮点击事件
     *
     * @param view 控件
     */
    protected void onTitleBarClickMore(View view) {

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

    /**
     * 通过 Class 跳转界面，不含参数
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有 Bundle 通过 Class 跳转界面
     * 带有跳转动画
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    /**
     * 通过 Class 跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 显示提示语
     *
     * @param toastResId 资源ID
     */
    public void showToast(int toastResId) {
        Toast.makeText(this, toastResId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示提示语
     *
     * @param toastText 文本
     */
    public void showToast(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }

    /**
     * 隐藏键盘
     */
    public void hideKeyBoard() {
        try {
            View rootView = this.getWindow().getDecorView();
            View focusView = rootView.findFocus();
            if (focusView != null) {
                int viewId = focusView.getId();
                View view = findViewById(viewId);
                if (view instanceof EditText) {
                    InputMethodManager manager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (manager != null) {
                        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Home键监听回调方法
     *
     * @param context 上下文环境
     * @param intent  意图
     */
    public void onHomeWatcherReceiver(Context context, Intent intent) {

    }

    /**
     * 监听返回键
     *
     * @param keyCode 键值
     * @param event   事件
     * @return true-消耗事件，false-不消耗事件
     */
    protected boolean executeKeyDownBack(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return executeKeyDownBack(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
