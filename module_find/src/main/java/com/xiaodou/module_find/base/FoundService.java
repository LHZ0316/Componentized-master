package com.xiaodou.module_find.base;

import com.lhz.android.libBaseCommon.base.BaseResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * lhz  on 2019/8/21.
 */

public interface FoundService {


    /**
     * 发现列表
     */
//    @FormUrlEncoded
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST(FoundApi.FIND)
    Observable<BaseResponse> find(@Body RequestBody route);


}
