package com.xiaodou.module_my.base;

import com.lhz.android.libBaseCommon.base.BaseResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author lhz
 * @version 1.0.0
 * @date 2018/5/7
 * @description
 */

public interface MyAssetsService {

    /**
     * 查询我的资产
     *
     * @param requestBody
     * @return
     */
    @POST(MyAssetsApi.MY_ASSETS_MYACCOUNT)
    Observable<BaseResponse> getMyAccount(@Body RequestBody requestBody);




}
