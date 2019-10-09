package com.lhz.android.baseUtils.utils;

import android.content.Context;

import com.lhz.android.baseUtils.widget.kprogresshud.KProgressHUD;

/**
 * 项目名称：MvpFrame
 * 创建人：Administrator
 * 创建时间：2019/9/20 15:26
 * 类描述：网络请求加载框
 * <p>
 * 界面销毁的时候，记得将alertDialog 对象销毁
 */
public class KProgressUtil {
    private static KProgressUtil kProgressUtil = new KProgressUtil();
    private KProgressHUD alertDialog;

    public static KProgressUtil newInstance() {
        return kProgressUtil;
    }

    public void showProgress(Context context) {
        if (alertDialog == null) {
            alertDialog = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(1)
                    .setDimAmount(0.5f)
                    .show();
        } else {
            if (!alertDialog.isShowing()) {
                alertDialog.show();
            }
        }
    }

    public void dismissProgress() {
        if (alertDialog != null && !alertDialog.isShowing()) {
            alertDialog.dismiss();
            alertDialog = null;
        }
    }
}

