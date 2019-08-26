package com.xiaodou.module_product.view.fragment;

import com.alibaba.android.arouter.facade.annotation.Route;

import com.lhz.android.libBaseCommon.base.RouterPath;
import com.lhz.android.libBaseCommon.mvp_senior.annotations.CreatePresenterAnnotation;
import com.xiaodou.module_product.base.BaseProductFragment;
import com.xiaodou.module_product.contract.IProductFragmentContract;
import com.xiaodou.module_product.presenter.ProductFragmentPresenter;
import com.xiaodou.module_task.R;

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

    @Override
    protected void initView() {

    }


    @Override
    protected void initData() {

    }

}
