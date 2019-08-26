package com.xiaodou.core.presenter;

import com.google.gson.Gson;
import com.lhz.android.libBaseCommon.https.observers_extension.ProgressObserver;
import com.lhz.android.libBaseCommon.https.scheduler.RxSchedulers;
import com.xiaodou.core.module.MainModule;
import com.xiaodou.core.contract.IMainContract;
import com.xiaodou.core.module.bean.TestBean;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * lhz  on 2019/8/21.
 */

public class MainPresenter extends IMainContract.Presenter {
    private static final String TAG = "MainPresenter";


    @Override
    public void httpTest() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "");
        map.put("2", "");
        map.put("3", "");
        Gson gson = new Gson();
        String s2 = gson.toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), s2);

        MainModule.createRetrofit()
                .getUserIfExists(requestBody)
                .compose(RxSchedulers.observableIO2Main(getView()))
                .subscribe(new ProgressObserver<TestBean>(this) {
                    @Override
                    public void onSuccess(TestBean result) {

                    }
                });
    }




}
