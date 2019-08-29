package com.xiaodou.module_home.view.fragment;


import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lhz.android.libBaseCommon.base.RouterPath;
import com.lhz.android.libBaseCommon.mvp_senior.annotations.CreatePresenterAnnotation;
import com.xiaodou.module_home.R;
import com.xiaodou.module_home.R2;
import com.xiaodou.module_home.base.BaseHomeFragment;
import com.xiaodou.module_home.contract.IHomeFragmentContract;
import com.xiaodou.module_home.presenter.HomeFragmentPresenter;

import butterknife.BindView;

/**
 * lhz  on 2019/8/21.
 */
@CreatePresenterAnnotation(HomeFragmentPresenter.class)
@Route(path = RouterPath.HOME_FRAGMENT) // 路由地址，必须注明
public class HomeFragment extends BaseHomeFragment<IHomeFragmentContract.View, HomeFragmentPresenter>
        implements IHomeFragmentContract.View {
    @BindView(R2.id.view_status_bar)
    View mViewStatusBar;

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {

    }


}
