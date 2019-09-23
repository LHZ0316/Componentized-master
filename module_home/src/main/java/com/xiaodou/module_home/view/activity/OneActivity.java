package com.xiaodou.module_home.view.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.lhz.android.baseUtils.widget.TitleBar;
import com.lhz.android.libBaseCommon.base.BaseActivity;
import com.lhz.android.libBaseCommon.statelayout.RPageStatusController;
import com.lhz.android.libBaseCommon.statelayout.annotation.RPageStatus;
import com.xiaodou.module_home.R;
import com.xiaodou.module_home.R2;

import butterknife.BindView;


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

    @Override
    public int setLayoutId() {
        return R.layout.activity_one;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rPageStatusController.changePageStatus(RPageStatus.ERROR);

    }

    @Override
    protected void initData() {

    }
}
