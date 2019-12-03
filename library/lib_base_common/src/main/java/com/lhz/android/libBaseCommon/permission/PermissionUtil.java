package com.lhz.android.libBaseCommon.permission;

import android.Manifest;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * 项目名称：MvpFrame
 * 创建人：LHZ
 * 创建时间：2019/11/28 11:49
 * 类描述：
 */
public class PermissionUtil {
    private static final String TAG = "PermissionsUtil";
    private PermissionFragment fragment;

    public static final int CODE_AUDIO = 0;//录音
    public static final int CODE_GET_ACCOUNTS = 1;//读取通讯录权限
    public static final int CODE_PHONE = 2;//通话管理拨打电话
    public static final int CODE_CALENDAR = 3;//日志/日历
    public static final int CODE_CAMERA = 4;//拍照或录像
    public static final int CODE_LOCATION = 5;//获取位置信息
    public static final int CODE_SMS = 6;//短信操作
    public static final int CODE_STORAGE = 7;//文件操作
    public static final String[] requestPermissions = {
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,

    };

    public PermissionUtil(@NonNull FragmentActivity activity) {
        fragment = getPermissionsFragment(activity);
    }

    private PermissionFragment getPermissionsFragment(FragmentActivity activity) {
        PermissionFragment fragment = (PermissionFragment) activity.getSupportFragmentManager().findFragmentByTag(TAG);
        boolean isNewInstance = fragment == null;
        if (isNewInstance) {
            fragment = new PermissionFragment();
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            fragmentManager
                    .beginTransaction()
                    .add(fragment, TAG)
                    .commit();
            fragmentManager.executePendingTransactions();
        }

        return fragment;
    }

    /**
     * 外部调用申请权限 自定义方式
     *
     * @param permissions 申请的权限
     * @param listener    监听权限接口
     */
    public void requestPermissions(String[] permissions, PermissionsListener listener) {
        fragment.setListener(listener);
        fragment.requestPermissions(permissions);

    }

    /**
     * 外部调用申请权限 rxPermissions方式
     *
     * @param permissions 申请的权限
     * @param listener    监听权限接口
     */
    public void requestRxPermissions(String[] permissions, Activity activity,rxPermissionsListener listener) {
        fragment.setRxListener(listener);
        fragment.requestRxPermissions(permissions,activity);

    }

    public interface PermissionsListener {
        void onGranted();

        void onDenied(List<String> deniedPermission);

        void onShouldShowRationale(List<String> deniedPermission);
    }

    public interface rxPermissionsListener {
        void permissionSuccess();

        void permissionFailed();

        void permissionRefused();
    }

}