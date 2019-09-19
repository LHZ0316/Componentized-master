package com.xiaodou.core.presenter;

import com.lhz.android.libBaseCommon.base.BaseApi;
import com.lhz.android.libBaseCommon.https.RequestParam;
import com.lhz.android.libBaseCommon.https.observers_extension.ProgressObserver;
import com.lhz.android.libBaseCommon.https.scheduler.RxSchedulers;
import com.xiaodou.core.module.MainModule;
import com.xiaodou.core.contract.IMainContract;
import com.xiaodou.core.module.bean.TestBean;

import java.util.logging.Logger;

import okhttp3.RequestBody;


/**
 * lhz  on 2019/8/21.
 */

public class MainPresenter extends IMainContract.Presenter {
    private static final String TAG = "MainPresenter";


    @Override
    public void httpTest(RequestParam requestParam) {
        RequestBody requestBody = BaseApi.getRequestBody(requestParam);
        MainModule.createRetrofit()
                .getLogin("15810733266", "733266", "160a3797c85f6410840",
                        "telephone", "", "39.979885", "116.417365", "xd_test")
                .compose(RxSchedulers.observableIO2Main(getView()))
                .subscribe(new ProgressObserver<TestBean>(this) {
                    @Override
                    public void onSuccess(TestBean result) {

                        Logger.getLogger("MainPresenter-----").warning("请求成功");
                    }
                });
    }


}
