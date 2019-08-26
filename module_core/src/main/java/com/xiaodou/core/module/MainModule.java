package com.xiaodou.core.module;

import com.lhz.android.libBaseCommon.https.RetrofitManager;

/**
 * @author lhz
 * @version 1.0.0
 * @date 2018/5/7
 * @description
 */

public class MainModule {

    public static MainService createRetrofit() {
        return RetrofitManager.getInstance().getRetrofitService(MainService.class);
    }
}
