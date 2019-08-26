package com.xiaodou.core.module;

import com.lhz.android.libBaseCommon.base.BaseResponse;
import com.xiaodou.common.view.CommonApi;
import com.xiaodou.core.module.bean.TestBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface MainService {


    @POST(CommonApi.LOGIN_USER_IF_EXISTS)
    Observable<BaseResponse<TestBean>> getUserIfExists(@Body RequestBody requestBody);


}
