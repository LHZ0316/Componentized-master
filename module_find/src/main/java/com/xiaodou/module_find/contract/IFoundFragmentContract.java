package com.xiaodou.module_find.contract;

import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;

/**
 * lhz  on 2019/8/21.
 */

public interface IFoundFragmentContract {

    interface View extends IBaseView {

    }

    abstract class Presenter extends BasePresenter<View> {

    }

}
