package com.xiaodou.module_home.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lhz.android.libBaseCommon.base.BaseActivity;
import com.xiaodou.module_home.R;


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

    @Override
    public int setLayoutId() {
        return R.layout.activity_one;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
