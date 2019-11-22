package com.xiaodou.module_my.view.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lhz.android.libBaseCommon.base.BaseMvpFragment;
import com.lhz.android.libBaseCommon.base.RouterPath;
import com.lhz.android.libBaseCommon.seniorMvp.annotations.CreatePresenterAnnotation;
import com.xiaodou.module_my.R;
import com.xiaodou.module_my.contract.MyAssetsContract;
import com.xiaodou.module_my.presenter.MyAssetsPresenter;

/**
 * lhz  on 2019/8/21.
 */
@CreatePresenterAnnotation(MyAssetsPresenter.class)
@Route(path = RouterPath.MY_FRAGMENT) // 路由地址，必须注明
public class MyFragment extends BaseMvpFragment<MyAssetsContract.View, MyAssetsPresenter> implements MyAssetsContract.View {


    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_my_assets;
    }



}
