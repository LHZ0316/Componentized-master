package com.xiaodou.module_home.module;

import com.lhz.android.libBaseCommon.https.RetrofitManager;

/**
 * lhz  on 2019/8/21.
 */

public class HomeModule {

    public static HomeService createrRetrofit() {
        return RetrofitManager.getInstance().getRetrofitService(HomeService.class);
    }

}
