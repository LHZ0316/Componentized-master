package com.xiaodou.module_found.view.fragment;

import android.graphics.Color;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lhz.android.libBaseCommon.base.RouterPath;
import com.lhz.android.libBaseCommon.mvp_senior.annotations.CreatePresenterAnnotation;
import com.lhz.android.libBaseUtils.utils.StatusBar;
import com.lhz.android.libBaseUtils.widget.titleBar.CommonTitleBar;
import com.xiaodou.module_find.R2;
import com.xiaodou.module_find.R;
import com.xiaodou.module_found.base.BaseFoundFragment;
import com.xiaodou.module_found.contract.IFoundFragmentContract;
import com.xiaodou.module_found.presenter.FoundFragmentPresenter;

import butterknife.BindView;


/**
 * lhz  on 2019/8/21.
 */
@Route(path = RouterPath.FOUND_FRAGMENT) // 路由地址，必须注明
@CreatePresenterAnnotation(FoundFragmentPresenter.class)
public class FoundFragment extends BaseFoundFragment<IFoundFragmentContract.View, FoundFragmentPresenter>
        implements IFoundFragmentContract.View {

    private static final String TAG = "FoundFragment";
    @BindView(R2.id.view_status_bar)
    View mViewStatusBar;
    @BindView(R2.id.titleBar)
    CommonTitleBar titleBar;

    @Override
    protected void initView() {
        StatusBar.setStatusViewAttr(mViewStatusBar, getHoldingActivity(), R.color.color_blue_6AA2FF);
        titleBar.setBackgroundColor(Color.parseColor("#6AA2FF"));
    }


    @Override
    protected void initData() {
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_found;
    }

}
