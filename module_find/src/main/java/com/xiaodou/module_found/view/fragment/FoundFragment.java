package com.xiaodou.module_found.view.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lhz.android.libBaseCommon.base.RouterPath;
import com.lhz.android.libBaseCommon.mvp_senior.annotations.CreatePresenterAnnotation;
import com.xiaodou.module_find.R;
import com.xiaodou.module_found.base.BaseFoundFragment;
import com.xiaodou.module_found.contract.IFoundFragmentContract;
import com.xiaodou.module_found.presenter.FoundFragmentPresenter;

/**
 * lhz  on 2019/8/21.
 */
@Route(path = RouterPath.FOUND_FRAGMENT) // 路由地址，必须注明
@CreatePresenterAnnotation(FoundFragmentPresenter.class)
public class FoundFragment extends BaseFoundFragment<IFoundFragmentContract.View, FoundFragmentPresenter>
        implements IFoundFragmentContract.View {

    private static final String TAG = "FoundFragment";



    @Override
    protected void initView() {
    }


    @Override
    protected void initData() {
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_found;
    }

}
