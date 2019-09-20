package com.lhz.android.baseUtils.utils;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.lhz.android.baseUtils.R;
import com.lhz.android.baseUtils.widget.kprogresshud.KProgressHUD;


/**
 * lhz  on 2019/8/21.
 */


public class DialogUtils {
    private static DialogUtils dialogUtils = new DialogUtils();
    private AlertDialog alertDialog;

    public static DialogUtils newInstance() {
        return dialogUtils;
    }

    public void showDiaLog(Activity activity, Object obj, String[] str,
                           View.OnClickListener closeListener,
                           View.OnClickListener confirmListener) {

        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(activity, R.style.dialog).create();
            alertDialog.show();
            Window window = alertDialog.getWindow();
            if (window != null) {
                window.setWindowAnimations(R.style.dialog_style);
                window.setContentView(R.layout.dialog_default);
                TextView tv = window.findViewById(R.id.tv_content);
                tv.setVisibility(View.VISIBLE);
                tv.setText(obj.toString());

                TextView tvClose = window.findViewById(R.id.tv_close);
                tvClose.setText(str[0]);
                tvClose.setOnClickListener(closeListener);

                TextView tvConfirm = window.findViewById(R.id.tv_confirm);
                tvConfirm.setText(str[1]);
                tvConfirm.setOnClickListener(confirmListener);
            }
            alertDialog.setCanceledOnTouchOutside(false);//设置点击Dialog外部任意区域关闭Dialog
        }
    }


    public void showDiaLogButton(Activity activity, Object obj, String[] str, View.OnClickListener confirmListener) {
        if (alertDialog == null) {
            alertDialog = new AlertDialog.Builder(activity, R.style.dialog).create();
            alertDialog.show();
            Window window = alertDialog.getWindow();
            if (window != null) {
                window.setWindowAnimations(R.style.dialog_style);
                window.setContentView(R.layout.dialog_default_sure);
                TextView tv = window.findViewById(R.id.tv_content);
                tv.setVisibility(View.VISIBLE);
                tv.setText(obj.toString());

                TextView tvConfirm = window.findViewById(R.id.tv_confirm);
                tvConfirm.setText(str[1]);
                tvConfirm.setOnClickListener(confirmListener);
            }
            alertDialog.setCanceledOnTouchOutside(false);//设置点击Dialog外部任意区域关闭Dialog
        }

    }

    public void dismissDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
            alertDialog = null;

        }
    }
}
