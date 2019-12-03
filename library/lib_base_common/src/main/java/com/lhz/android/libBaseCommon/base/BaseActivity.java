package com.lhz.android.libBaseCommon.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lhz.android.libBaseCommon.R;
import com.lhz.android.libBaseCommon.eventbus.EventBusUtil;
import com.lhz.android.libBaseCommon.eventbus.MessageEvent;
import com.lhz.android.libBaseCommon.statelayout.RPageStatusController;
import com.lhz.android.libBaseCommon.utils.AppManager;
import com.lhz.android.libBaseCommon.utils.StatusBarUtil;
import com.lhz.android.libBaseCommon.utils.Utils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Random;

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
     * 标题栏
     */
    private boolean isHaveTitle = true;
    private FrameLayout mBaseContent;
    protected TextView mTitleBarName, mTitleBarLeft, mTitleBarRight, mTitleBarMore;
    protected RelativeLayout mBaseTitle;
    protected int mResultCode = RESULT_CANCELED;
    private View mView;
    private View mContentView;

    /**
     * 界面状态管理
     */
    protected RPageStatusController rPageStatusController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置标题
        if (isHaveTitle) {
            mView = initContentView();
            setContentView(mView); // 设置布局。

            // 界面多状态管理
            rPageStatusController = RPageStatusController.get();
            rPageStatusController.bind(mBaseContent);
        } else {
            setContentView(setLayoutId());// 设置布局。

            // 界面多状态管理
            rPageStatusController = RPageStatusController.get();
            rPageStatusController.bind(this);
        }
        // 禁止所有的activity横屏
        if (isScreenRotate()) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        // ButterKnife
        ButterKnife.bind(this);
        // EventBus
//        EventBus.getDefault().register(this);
        if (isRegisterEventBus()) {
            EventBusUtil.register(this);
        }
        // activity管理栈
        AppManager.getInstance().addActivity(this);
        // 设置状态栏颜色
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
        // 虚拟键颜色
        setNavigationBarColor(this);
        // 软键盘
        initSoftKeyboard();
        // 初始化UI
        initView(savedInstanceState);
        // 初始化数据
        initData();

    }

    /**
     * 如果当前的 Activity（singleTop 启动模式） 被复用时会回调
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 设置为当前的 Intent，避免 Activity 被杀死后重启 Intent 还是最原先的那个
        setIntent(intent);
    }

    /**
     * 初始化软键盘
     */
    protected void initSoftKeyboard() {
        // 点击外部隐藏软键盘，提升用户体验
        findViewById(Window.ID_ANDROID_CONTENT).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
            }
        });
    }

    /**
     * 初始化标题栏的view
     */
    protected View initContentView() {
        if (null == mView) {
            mContentView = LayoutInflater.from(this).inflate(R.layout.activity_base, null);
            mBaseContent = (FrameLayout) mContentView.findViewById(R.id.base_content);
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
    public void finish() {
        hideSoftKeyboard();
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 移除和这个 Activity 相关的消息回调
        HANDLER.removeCallbacksAndMessages(mHandlerToken);
        // 移除栈
        AppManager.getInstance().finishActivity(this);
        // 注销eventBus
//        EventBus.getDefault().unregister(this);
        if (isRegisterEventBus()) {
            EventBusUtil.unregister(this);
        }

    }

    /**
     * 是否注册事件分发
     *
     * @return true绑定EventBus事件分发，默认不绑定，子类需要绑定的话复写此方法返回true.
     */
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusAccept(MessageEvent event) {
        if (event != null) {
            receiveEvent(event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEventBusAccept(MessageEvent event) {
        if (event != null) {
            receiveStickyEvent(event);
        }
    }

    /**
     * 接收到分发到事件
     *
     * @param event 事件
     */
    protected void receiveEvent(MessageEvent event) {

    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    protected void receiveStickyEvent(MessageEvent event) {

    }

    /**
     * 设置屏幕是否可以旋转
     */
    public boolean isScreenRotate() {
        return true;
    }

    /**
     * 设置 虚拟按键的颜色
     */
    private static void setNavigationBarColor(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setNavigationBarColor(Color.BLACK);
        }
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

    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    public final Object mHandlerToken = hashCode();

    /**
     * 延迟执行         （运行在子线程
     */
    public final boolean post(Runnable r) {
        return postDelayed(r, 0);
    }

    /**
     * 延迟一段时间执行
     */
    public final boolean postDelayed(Runnable r, long delayMillis) {
        if (delayMillis < 0) {
            delayMillis = 0;
        }
        return postAtTime(r, SystemClock.uptimeMillis() + delayMillis);
    }

    /**
     * 在指定的时间执行
     */
    public final boolean postAtTime(Runnable r, long uptimeMillis) {
        // 发送和这个 Activity 相关的消息回调
        return HANDLER.postAtTime(r, mHandlerToken, uptimeMillis);
    }

    /**
     * startActivity 方法优化
     */

    public void startActivity(Class<? extends Activity> cls) {
        startActivity(new Intent(this, cls));
    }

    public void startActivityFinish(Class<? extends Activity> cls) {
        startActivityFinish(new Intent(this, cls));
    }

    public void startActivityFinish(Intent intent) {
        startActivity(intent);
        finish();
    }

    /**
     * startActivityForResult 方法优化
     */

    private ActivityCallback mActivityCallback;
    private int mActivityRequestCode;

    public void startActivityForResult(Class<? extends Activity> cls, ActivityCallback callback) {
        startActivityForResult(new Intent(this, cls), null, callback);
    }

    public void startActivityForResult(Intent intent, ActivityCallback callback) {
        startActivityForResult(intent, null, callback);
    }

    public void startActivityForResult(Intent intent, @Nullable Bundle options, ActivityCallback callback) {
        // 回调还没有结束，所以不能再次调用此方法，这个方法只适合一对一回调，其他需求请使用原生的方法实现
        if (mActivityCallback == null) {
            mActivityCallback = callback;
            // 随机生成请求码，这个请求码在 0 - 255 之间
            mActivityRequestCode = new Random().nextInt(255);
            startActivityForResult(intent, mActivityRequestCode, options);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (mActivityCallback != null && mActivityRequestCode == requestCode) {
            mActivityCallback.onActivityResult(resultCode, data);
            mActivityCallback = null;
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    /**
     * 处理 Activity 多重跳转：https://www.jianshu.com/p/579f1f118161
     */

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (startActivitySelfCheck(intent)) {
            hideSoftKeyboard();
            // 查看源码得知 startActivity 最终也会调用 startActivityForResult
            super.startActivityForResult(intent, requestCode, options);
        }
    }

    private String mStartActivityTag;
    private long mStartActivityTime;

    /**
     * 检查当前 Activity 是否重复跳转了，不需要检查则重写此方法并返回 true 即可
     *
     * @param intent 用于跳转的 Intent 对象
     * @return 检查通过返回true, 检查不通过返回false
     */
    protected boolean startActivitySelfCheck(Intent intent) {
        // 默认检查通过
        boolean result = true;
        // 标记对象
        String tag;
        if (intent.getComponent() != null) {
            // 显式跳转
            tag = intent.getComponent().getClassName();
        } else if (intent.getAction() != null) {
            // 隐式跳转
            tag = intent.getAction();
        } else {
            // 其他方式
            return true;
        }

        if (tag.equals(mStartActivityTag) && mStartActivityTime >= SystemClock.uptimeMillis() - 500) {
            // 检查不通过
            result = false;
        }

        // 记录启动标记和时间
        mStartActivityTag = tag;
        mStartActivityTime = SystemClock.uptimeMillis();
        return result;
    }

    /**
     * Activity 回调接口
     */
    public interface ActivityCallback {

        /**
         * 结果回调
         *
         * @param resultCode 结果码
         * @param data       数据
         */
        void onActivityResult(int resultCode, @Nullable Intent data);
    }


    /**
     * 隐藏软键盘
     */
    private void hideSoftKeyboard() {
        // 隐藏软键盘，避免软键盘引发的内存泄露
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager != null) {
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }

/*        try {
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
        }*/

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
