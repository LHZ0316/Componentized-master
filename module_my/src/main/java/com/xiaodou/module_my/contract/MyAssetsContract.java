package com.xiaodou.module_my.contract;

import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;

/**
 * @author lhz
 * @version 1.0.0
 * @date 2018/4/28
 * @description
 */

public interface MyAssetsContract {

    interface View extends IBaseView {
    }

    abstract class Presenter extends BasePresenter<View> {

    }
}
