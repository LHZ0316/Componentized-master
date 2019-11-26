package com.xiaodou.module_task.view.fragment;

import android.graphics.Color;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.lhz.android.libBaseCommon.base.RouterPath;
import com.lhz.android.libBaseCommon.seniorMvp.annotations.CreatePresenterAnnotation;
import com.lhz.android.baseUtils.widget.TitleBar;
import com.xiaodou.module_task.base.BaseProductFragment;
import com.xiaodou.module_task.contract.IProductFragmentContract;
import com.xiaodou.module_task.presenter.ProductFragmentPresenter;
import com.xiaodou.module_task.R;
import com.xiaodou.module_task.R2;

import butterknife.BindView;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 产品列表页面
 */
@CreatePresenterAnnotation(ProductFragmentPresenter.class)
@Route(path = RouterPath.PRODUCT_FRAGMENT) // 路由地址，必须注明
public class ProductFragment extends BaseProductFragment<IProductFragmentContract.View, ProductFragmentPresenter>
        implements IProductFragmentContract.View {

    private static final String TAG = "ProductFragment";


    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_product;
    }

    @BindView(R2.id.myTitleBar)
    TitleBar myTitleBar;

    @Override
    protected void initView() {
        myTitleBar.setBackgroundColor(Color.GREEN);
    }



    @Override
    protected void initData() {

    }

}
