package com.lhz.android.libBaseCommon.https.widget;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhz.android.libBaseCommon.R;


/**
 * lhz  on 2019/8/21.
 * <p>
 * 网络请求加载的进度条
 */

public class LoadingDialog extends ProgressDialog {

    private AnimationDrawable mAnimation;
    private Context mContext;
    private ImageView mImageView;
    private String mLoadingTip;
    private TextView mLoadingTv;
    private int count = 0;
    private String oldLoadingTip;
    private int mResid;

    public LoadingDialog(Context context) {
        super(context, R.style.httpDialog);
        this.mContext = context;
        setCanceledOnTouchOutside(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        setContentView(R.layout.dialog_http_loading2);
//        initView();
//        initData();
    }

    private void initData() {

        // 通过ImageView对象拿到背景显示的AnimationDrawable
        mAnimation = (AnimationDrawable) mImageView.getBackground();
        // 为了防止在onCreate方法中只显示第一帧的解决方案之一
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();

            }
        });

    }

    public void setContent(String str) {
        mLoadingTv.setText(str);
    }

    private void initView() {
        setContentView(R.layout.dialog_http_loading);
        mImageView = (ImageView) findViewById(R.id.loadingIv);
    }

	/*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
		mAnimation.start();
		super.onWindowFocusChanged(hasFocus);
	}*/
}