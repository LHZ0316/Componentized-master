package com.xiaodou.module_home.view.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.lhz.android.baseUtils.utils.DialogUtils;
import com.lhz.android.baseUtils.utils.KProgressUtil;
import com.lhz.android.libBaseCommon.base.BaseActivity;
import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatus;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.xiaodou.module_home.R;
import com.xiaodou.module_home.R2;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.LambdaObserver;


/**
 * 项目名称：MvpFrame
 * 创建人：Administrator
 * 创建时间：2019/9/9 17:47
 * 类描述：
 */
public class OneActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setHaveTitle(true);
        super.onCreate(savedInstanceState);
    }

    @BindView(R2.id.fl_content)
    FrameLayout flContent;
    @BindView(R2.id.mb_button1)
    Button mb_button1;

    @Override
    public int setLayoutId() {
        return R.layout.activity_one;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
//        mb_button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rPageStatusController.changePageStatus(RPageStatus.ERROR);
//            }
//        });
    }

    @Override
    protected void initData() {

    }

    @OnClick({R2.id.mb_button1, R2.id.mb_button2, R2.id.mb_button3, R2.id.mb_button4, R2.id.mb_button5, R2.id.mb_button6})
    public void onViewClicked(View view) {

        int i = view.getId();
        if (i == R.id.mb_button1) {

            Logger.w("我的点击事件===========1");
            String[] strings = new String[2];
            strings[0] = "取消";
            strings[1] = "确定";
            DialogUtils.newInstance().showDiaLog(this, "提示" + "\r\n" + "\r\n" +
                    " - 界面全新改版，更清晰直观的视觉与操作体验。" + "\r\n" +
                    " - 可以在看一看里浏览朋友认为好看的文章。" + "\r\n" +
                    " - 解决了一些已知问题。文案瞎写的。", strings, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUtils.newInstance().dismissDialog();
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUtils.newInstance().dismissDialog();
                }
            });


        } else if (i == R.id.mb_button2) {

            Logger.w("加载框===========2");
            KProgressUtil.newInstance().showProgress(this);

        } else if (i == R.id.mb_button3) {
            Logger.w("申请权限===========3");
            //        lambda 表达式
            rxPermissions
                    .requestEach(Manifest.permission.READ_PHONE_STATE)
                    .subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) throws Exception {
                            if (permission.granted) {
                                Logger.w("开启权限成功，I can control the camera now");
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                Logger.w("Denied permission without ask never again");
                            } else {
                                Logger.w("Denied permission with ask never again");
                            }
                        }
                    });
        } else if (i == R.id.mb_button4) {
            Logger.w("界面状态管理===========4");
            rPageStatusController.changePageStatus(RPageStatus.ERROR);
        } else if (i == R.id.mb_button5) {
            Logger.w("我的点击事件===========5");
        } else if (i == R.id.mb_button6) {
            Logger.w("我的点击事件===========6");
        }
        switch (view.getId()) {
            case R2.id.mb_button1:
                break;
            case R2.id.mb_button2:
                break;
            case R2.id.mb_button3:
                break;
            case R2.id.mb_button4:
                break;
            case R2.id.mb_button5:
                break;
            case R2.id.mb_button6:
                break;
        }
    }
}
