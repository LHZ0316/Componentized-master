package com.xiaodou.core.contract;

import com.lhz.android.libBaseCommon.base.BasePresenter;
import com.lhz.android.libBaseCommon.base.IBaseView;
import com.xiaodou.core.view.LaunchActivity;
import com.xiaodou.core.view.MainActivity;

/**
 * lhz  on 2019/8/21.
 */

public interface IMainContract {

    interface View extends IBaseView {
        MainActivity getThis();
    }

    interface LaunchView extends IBaseView {
        LaunchActivity getThis();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void httpTest();
    }

    abstract class LaunchPresenter extends BasePresenter<LaunchView> {
        public abstract void getImage();
    }
}
