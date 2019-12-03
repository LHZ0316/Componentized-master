package com.lhz.android.libBaseCommon.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * 项目名称：MvpFrame
 * 创建人：LHZ
 * 创建时间：2019/11/28 11:49
 * 类描述：
 */


public class PermissionFragment extends Fragment {
    /**
     * 申请权限的requestCode
     */
    private static final int PERMISSIONS_REQUEST_CODE = 1;

    /**
     * 权限监听接口
     */
    private PermissionUtil.PermissionsListener listener;
    private PermissionUtil.rxPermissionsListener rxlistener;

    public void setListener(PermissionUtil.PermissionsListener listener) {
        this.listener = listener;
    }

    public void setRxListener(PermissionUtil.rxPermissionsListener listener) {
        this.rxlistener = listener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * 申请权限 自定义方式
     *
     * @param permissions 需要申请的权限
     */
    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissions(@NonNull String[] permissions) {
        List<String> requestPermissionList = new ArrayList<>();
        //找出所有未授权的权限
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionList.add(permission);
            }
        }
        if (requestPermissionList.isEmpty()) {
            //已经全部授权
            permissionAllGranted();
        } else {
            //申请授权
            requestPermissions(requestPermissionList.toArray(new String[requestPermissionList.size()]), PERMISSIONS_REQUEST_CODE);
        }
    }


    /**
     * 申请权限 RxPermission方式
     *
     * @param permissions 需要申请的权限
     */
    public void requestRxPermissions(String[] permissions, Activity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEachCombined(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            Logger.w("开启权限成功，I can control the camera now");
                            rxlistener.permissionSuccess();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            rxlistener.permissionFailed();
                            Logger.w("Denied permission without ask never again");
                        } else {
                            rxlistener.permissionRefused();
                            // 弹框
                            Logger.w("Denied permission with ask never again");
                        }
                    }
                });
    }


    /**
     * fragment回调处理权限的结果
     *
     * @param requestCode  请求码 要等于申请时候的请求码
     * @param permissions  申请的权限
     * @param grantResults 对应权限的处理结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != PERMISSIONS_REQUEST_CODE) {
            return;
        }

        if (grantResults.length > 0) {
            List<String> deniedPermissionList = new ArrayList<>();
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissionList.add(permissions[i]);
                }
            }

            if (deniedPermissionList.isEmpty()) {
                //已经全部授权
                permissionAllGranted();
            } else {

                //勾选了对话框中”Don’t ask again”的选项, 返回false
                for (String deniedPermission : deniedPermissionList) {
                    boolean flag = shouldShowRequestPermissionRationale(deniedPermission);
                    if (!flag) {
                        //拒绝授权
                        permissionShouldShowRationale(deniedPermissionList);
                        return;
                    }
                }
                //拒绝授权
                permissionHasDenied(deniedPermissionList);

            }


        }

    }


    /**
     * 权限全部已经授权
     */
    private void permissionAllGranted() {
        if (listener != null) {
            listener.onGranted();
        }
    }

    /**
     * 有权限被拒绝
     *
     * @param deniedList 被拒绝的权限
     */
    private void permissionHasDenied(List<String> deniedList) {
        if (listener != null) {
            listener.onDenied(deniedList);
        }
    }

    /**
     * 权限被拒绝并且勾选了不在询问
     *
     * @param deniedList 勾选了不在询问的权限
     */
    private void permissionShouldShowRationale(List<String> deniedList) {
        if (listener != null) {
            listener.onShouldShowRationale(deniedList);
        }
    }
}


