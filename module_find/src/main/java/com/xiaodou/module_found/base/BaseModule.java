package com.xiaodou.module_found.base;

import com.lhz.android.libBaseCommon.https.RetrofitManager;

/**
 * lhz  on 2019/8/21.
 */

public class BaseModule {

    public static FoundService createrRetrofit() {
        return RetrofitManager.getInstance().getRetrofitService(FoundService.class);
    }

}
