package com.xiaodou.module_my.base;

import com.lhz.android.libBaseCommon.https.RetrofitManager;

/**
 * @author lhz
 * @version 1.0.0
 * @date 2018/5/7
 * @description
 */

public class BaseModule {

    public static MyAssetsService createrRetrofit() {
        return RetrofitManager.getInstance().getRetrofitService(MyAssetsService.class);
    }
}
