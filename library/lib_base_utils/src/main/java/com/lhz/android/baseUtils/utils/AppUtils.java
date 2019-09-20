package com.lhz.android.baseUtils.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import java.util.List;

/**
 * Created by lhz on 2016/9/28.
 * 跟App相关的辅助类
 */

public class AppUtils {

    private static PackageInfo getPackageManager(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取应用程序名称
     *
     * @return 当前应用的名称
     */
    public static String getAppName(Context context) {
        if (getPackageManager(context)==null) {
            return "";
        }
        return context.getResources().getString(getPackageManager(context).applicationInfo.labelRes);
    }

    /**
     * 获取应用程序版本名称
     *
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        if (getPackageManager(context)==null) {
            return "";
        }
        return getPackageManager(context).versionName;
    }

    /**
     * 获取应用程序版本 Code
     *
     * @return 当前应用的版本 Code
     */
    public static int getVersionCode(Context context) {
        if (getPackageManager(context)!=null) {
            return 0;
        }
        return getPackageManager(context).versionCode;
    }


}
