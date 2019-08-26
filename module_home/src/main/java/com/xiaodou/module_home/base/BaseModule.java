package com.xiaodou.module_home.base;

import com.lhz.android.libBaseCommon.https.RetrofitManager;

/**
 * lhz  on 2019/8/21.
 */

public class BaseModule {

    public static HomeService createrRetrofit() {
        return RetrofitManager.getInstance().getRetrofitService(HomeService.class);
    }

}
