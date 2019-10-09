package com.xiaodou.module_home.view.activity;

import android.Manifest;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.lhz.android.baseUtils.utils.DialogUtils;
import com.lhz.android.baseUtils.utils.KProgressUtil;
import com.lhz.android.baseUtils.widget.CustomButton;
import com.lhz.android.baseUtils.widget.CustomTextView;
import com.lhz.android.baseUtils.widget.SendButton;
import com.lhz.android.baseUtils.widget.SmsEditText;
import com.lhz.android.libBaseCommon.base.BaseActivity;
import com.lhz.android.libBaseCommon.cache.CacheBean;
import com.lhz.android.libBaseCommon.cache.DiskCache;
import com.lhz.android.libBaseCommon.dialog.AddressDialog;
import com.lhz.android.libBaseCommon.dialog.BaseDialog;
import com.lhz.android.libBaseCommon.dialog.BaseDialogFragment;
import com.lhz.android.libBaseCommon.dialog.DateDialog;
import com.lhz.android.libBaseCommon.dialog.MessageDialog;
import com.lhz.android.libBaseCommon.dialog.TimeDialog;
import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatus;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.Permission;
import com.xiaodou.module_home.R;
import com.xiaodou.module_home.R2;

import java.util.ArrayList;
import java.util.Calendar;

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
        CustomTextView ctv_text = (CustomTextView) findViewById(R.id.ctv_text);
        ctv_text.setCTColors(1, 3, getResources().getColor(R.color.colorAccent), 4, 6, getResources().getColor(R.color.colorPrimary));
        ctv_text.setCTSizes(0, 1, 10, 1, 3, 16, 4, 6, 30, 9, 10, 10);
        ctv_text.setCTUnderlines(1, 3);
        ctv_text.setCTDeleteLines(4, 6);
        ctv_text.setCTSuperscripts(0, 1);
        ctv_text.setCTSubscripts(9, 10);
        ctv_text.setCTBolds(7, 8);
        ctv_text.setCTItalic(8, 9);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0, -20, 60, 40);
        ctv_text.setCTImages(6, 7, drawable);


        CustomButton customButton = (CustomButton) findViewById(R.id.cb_button);
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ArrayList<CacheBean> arrayList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CacheBean cacheBean = new CacheBean();
            cacheBean.setId(i + "");
            arrayList.add(cacheBean);
        }
        SmsEditText sms_edit_text = (SmsEditText) findViewById(R.id.sms_edit_text);
        sms_edit_text.getSendButton().setOnSendClickListener(new SendButton.SendClickListener() {

            @Override
            public String onGetVerifyPhone() {
                CacheBean cacheBean = new CacheBean();
                cacheBean.setId("5");

                /**
                 * 测试缓存增删
                 * */
                try {
                    ArrayList<CacheBean> videoList = DiskCache.getInstance(OneActivity.this).get("videoList");
                    if (videoList == null) {
                        // 存储
                        DiskCache.getInstance(OneActivity.this).put("videoList", arrayList);
                    } else {
                        // 删除
                        DiskCache.getInstance(OneActivity.this).itemDeleteList("videoList", 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "15810733266";
            }

            @Override
            public void onSendVerificationCode() {
                ArrayList<CacheBean> videoList = DiskCache.getInstance(OneActivity.this).get("videoList");
                Toast.makeText(OneActivity.this, videoList.get(0).getId() + "===验证码发送ing", Toast.LENGTH_SHORT).show();
            }
        });
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
            new MessageDialog.Builder(this)
                    // 标题可以不用填写
                    .setTitle("我是标题")
                    // 内容必须要填写
                    .setMessage("我是内容")
                    // 确定按钮文本
                    .setConfirm(getString(R.string.common_confirm))
                    // 设置 null 表示不显示取消按钮
                    .setCancel(getString(R.string.common_cancel))
                    // 设置点击按钮后不关闭对话框
                    //.setAutoDismiss(false)
                    .setListener(new MessageDialog.OnListener() {

                        @Override
                        public void onConfirm(BaseDialog dialog) {
//                            toast("确定了");
                        }

                        @Override
                        public void onCancel(BaseDialog dialog) {
//                            toast("取消了");
                        }
                    })
                    .show();

        } else if (i == R.id.mb_button6) {
            Logger.w("我的点击事件===========6");
       /*     // 日期选择对话框
            new DateDialog.Builder(this)
                    .setTitle(getString(R.string.date_title))
                    // 确定按钮文本
                    .setConfirm(getString(R.string.common_confirm))
                    // 设置 null 表示不显示取消按钮
                    .setCancel(getString(R.string.common_cancel))
                    // 设置日期
                    //.setDate("2018-12-31")
                    //.setDate("20181231")
                    //.setDate(1546263036137)
                    // 设置年份
                    //.setYear(2018)
                    // 设置月份
                    //.setMonth(2)
                    // 设置天数
                    //.setDay(20)
                    // 不选择天数
                    //.setIgnoreDay()
                    .setListener(new DateDialog.OnListener() {
                        @Override
                        public void onSelected(BaseDialog dialog, int year, int month, int day) {
//                            toast(year + getString(R.string.common_year) + month + getString(R.string.common_month) + day + getString(R.string.common_day));

                            // 如果不指定时分秒则默认为现在的时间
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.YEAR, year);
                            // 月份从零开始，所以需要减 1
                            calendar.set(Calendar.MONTH, month - 1);
                            calendar.set(Calendar.DAY_OF_MONTH, day);
//                            toast("时间戳：" + calendar.getTimeInMillis());
                            //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                        }

                        @Override
                        public void onCancel(BaseDialog dialog) {
//                            toast("取消了");
                        }
                    })
                    .show();*/
            // 时间选择对话框
/*            new TimeDialog.Builder(this)
                    .setTitle(getString(R.string.time_title))
                    // 确定按钮文本
                    .setConfirm(getString(R.string.common_confirm))
                    // 设置 null 表示不显示取消按钮
                    .setCancel(getString(R.string.common_cancel))
                    // 设置时间
                    //.setTime("23:59:59")
                    //.setTime("235959")
                    // 设置小时
                    //.setHour(23)
                    // 设置分钟
                    //.setMinute(59)
                    // 设置秒数
                    //.setSecond(59)
                    // 不选择秒数
                    //.setIgnoreSecond()
                    .setListener(new TimeDialog.OnListener() {

                        @Override
                        public void onSelected(BaseDialog dialog, int hour, int minute, int second) {
//                            toast(hour + getString(R.string.common_hour) + minute + getString(R.string.common_minute) + second + getString(R.string.common_second));

                            // 如果不指定年月日则默认为今天的日期
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.HOUR_OF_DAY, hour);
                            calendar.set(Calendar.MINUTE, minute);
                            calendar.set(Calendar.SECOND, second);
//                            toast("时间戳：" + calendar.getTimeInMillis());
                            //toast(new SimpleDateFormat("yyyy年MM月dd日 kk:mm:ss").format(calendar.getTime()));
                        }

                        @Override
                        public void onCancel(BaseDialog dialog) {
//                            toast("取消了");
                        }
                    })
                    .show();*/
///////////////////////////////////////////

            // 自定义对话框
/*            new BaseDialogFragment.Builder(this)
                    .setContentView(R.layout.dialog_message)
                    .setOnClickListener(R.id.tv_message_cancel, new BaseDialog.OnClickListener() {
                        @Override
                        public void onClick(BaseDialog dialog, View view) {

                        }
                    })
                    .addOnShowListener(new BaseDialog.OnShowListener() {
                        @Override
                        public void onShow(BaseDialog dialog) {
                        }
                    })
                    .addOnCancelListener(new BaseDialog.OnCancelListener() {
                        @Override
                        public void onCancel(BaseDialog dialog) {
                        }
                    })
                    .addOnDismissListener(new BaseDialog.OnDismissListener() {
                        @Override
                        public void onDismiss(BaseDialog dialog) {
                        }
                    })
                    .setOnKeyListener(new BaseDialog.OnKeyListener() {
                        @Override
                        public boolean onKey(BaseDialog dialog, KeyEvent event) {
                            return false;
                        }
                    })
                    .show();*/

//////////////////////////////////////

            // 选择地区对话框
            new AddressDialog.Builder(this)
                    .setTitle(getString(R.string.address_title))
                    // 设置默认省份
                    //.setProvince("广东省")
                    // 设置默认城市（必须要先设置默认省份）
                    //.setCity("广州市")
                    // 不选择县级区域
                    //.setIgnoreArea()
                    .setListener(new AddressDialog.OnListener() {

                        @Override
                        public void onSelected(BaseDialog dialog, String province, String city, String area) {
                        }

                        @Override
                        public void onCancel(BaseDialog dialog) {
                        }
                    })
                    .show();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KProgressUtil.newInstance().dismissProgress();
    }
}
