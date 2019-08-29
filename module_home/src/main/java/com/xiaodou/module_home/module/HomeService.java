package com.xiaodou.module_home.module;

import com.lhz.android.libBaseCommon.base.BaseResponse;


import retrofit2.http.POST;
import io.reactivex.Observable;
import retrofit2.http.Headers;


/**
 * lhz  on 2019/8/21.
 * 首页retrofit  接口
 */

public interface HomeService {

    @Headers({"Content-Type: application/json", "Accept: application/json"})//需要添加头
    @POST(HomeApi.home_new)
    Observable<BaseResponse> homeNew();


}