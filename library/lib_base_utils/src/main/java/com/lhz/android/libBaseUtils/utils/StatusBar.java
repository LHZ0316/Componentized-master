package com.lhz.android.libBaseUtils.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.lhz.android.libBaseUtils.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * Created by lhz on 2017/8/31.
 * <p>
 * 沉趁式   透明状态栏
 */
public class StatusBar {

    private static final int DEFAULT_STATUS_BAR_ALPHA = 112;


    /**
     * 设置状态栏颜色
     * 纯色
     *
     * @param activity 需要设置的 activity
     * @param color    状态栏颜色值
     */
    public static void setBarColor(Activity activity, @ColorInt int color) {
        setColor(activity, color, 0);
    }

    /**
     * 设置状态栏纯色
     * 半透明效果
     *
     * @param activity 需要设置的 activity
     * @param color    状态栏颜色值
     */
    public static void setBarColorHalf(Activity activity, @ColorInt int color) {
        setColor(activity, color, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏背景色
     * 4.4以下不处理
     * 4.4使用默认沉浸式状态栏
     *
     * @param color 要为状态栏设置的颜色值
     */
    private static void setColor(Activity activity, int color, @IntRange(from = 0, to = 255) int statusBarAlpha) {

        //沉浸式状态栏(4.4-5.0透明，5.0以上半透明)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = activity.getWindow();
            ViewGroup decorView = (ViewGroup) win.getDecorView();
            WindowManager.LayoutParams localLayoutParams = win.getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);

            //android5.0以上设置透明效果
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//清除flag，为了android5.0以上也全透明效果
                //让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | option);
                win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                win.setStatusBarColor(calculateStatusColor(color, statusBarAlpha));//设置状态栏背景色

                // 还要设置偏移，否则状态栏和内容重叠
                // 设置 paddingTop
                ViewGroup rootView = decorView.findViewById(android.R.id.content);
                rootView.setPadding(0, getStatusBarHeight(activity), 0, 0);
                //根布局添加占位状态栏
                View statusBarView = new View(activity);
                ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        getStatusBarHeight(activity));
//                statusBarView.setBackgroundColor(Color.parseColor("#fb5a5c"));
                statusBarView.setBackgroundColor(calculateStatusColor(color, statusBarAlpha));
                decorView.addView(statusBarView, lp);

            }
        }
    }


    //////////////////////////////////////////////

    /**
     * 为布局文件中新增的状态栏布局设置背景色和高度
     */
    public static void setStatusViewAttr(View view, Activity activity) {
        if (view == null || activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = StatusBar.getStatusBarHeight(activity);
            view.setLayoutParams(layoutParams);

            view.setBackgroundColor(activity.getResources().getColor(R.color.colorMain));
        }
    }

    /**
     * 为布局文件中新增的状态栏布局设置背景色和高度
     */
    public static void setStatusViewAttr(View view, Activity activity, int color) {
        if (view == null || activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = StatusBar.getStatusBarHeight(activity);
            view.setLayoutParams(layoutParams);
            view.setBackgroundColor(activity.getResources().getColor(color));
        }
    }

    //////////////////////////////////////////////

    /**
     * 设置状态栏全透明
     *
     * @param activity 需要设置的activity
     */
    public static void setTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            return;
        }
        transparentStatusBar(activity);
    }

    public static void setTransparentDark(Activity activity) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            return;
        }
        transparentStatusBar(activity);
        setDarkMode(activity);
    }

    public static void setTransparentLight(Activity activity) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            return;
        }
        transparentStatusBar(activity);
        setLightMode(activity);
    }

    /**
     * 使状态栏透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 修正 Toolbar 的位置
     * 在 Android 4.4 版本下无法显示内容在 StatusBar 下，所以无需修正 Toolbar 的位置
     *
     * @param toolbar
     */
    public static void fixToolbar(Toolbar toolbar, Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int statusHeight = getStatusBarHeight(activity);
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
            layoutParams.setMargins(0, statusHeight, 0, 0);
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 计算状态栏颜色
     *
     * @param color color值
     * @param alpha alpha值
     * @return 最终的状态栏颜色
     */
    public static int calculateStatusColor(@ColorInt int color, int alpha) {
        if (alpha == 0) {
            return color;
        }
        float a = 1 - alpha / 255f;
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;
        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static void setDarkMode(Activity activity) {
        setMIUIStatusBarDarkIcon(activity, true);
        setMeiZuStatusBarDarkIcon(activity, true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static void setLightMode(Activity activity) {
        setMIUIStatusBarDarkIcon(activity, false);
        setMeiZuStatusBarDarkIcon(activity, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    /**
     * 修改 MIUI V6  以上状态栏颜色
     */
    private static void setMIUIStatusBarDarkIcon(@NonNull Activity activity, boolean darkIcon) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkIcon ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改魅族状态栏字体颜色 Flyme 4.0
     */
    private static void setMeiZuStatusBarDarkIcon(@NonNull Activity activity, boolean darkIcon) {
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (darkIcon) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            activity.getWindow().setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
