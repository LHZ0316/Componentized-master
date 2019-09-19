package com.xiaodou.core.module;

import com.lhz.android.libBaseCommon.base.BaseResponse;
import com.xiaodou.common.view.CommonApi;
import com.xiaodou.core.module.bean.TestBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Path;
import retrofit2.http.FieldMap;
import retrofit2.http.Query;

public interface MainService {

    @POST(CommonApi.LOGIN_IN)
    Observable<BaseResponse<TestBean>> getLogin(@Body RequestBody requestBody);

    @POST(CommonApi.LOGIN_IN)
    @FormUrlEncoded
    Observable<BaseResponse<TestBean>> getLogin(@Field("phoneNum") String phoneNum,
                                                @Field("pwd") String pwd,
                                                @Field("registrationId") String registrationId,
                                                @Field("platform") String platform,
                                                @Field("uniqueId") String uniqueId,
                                                @Field("lat") String lat,
                                                @Field("lng") String lng,
                                                @Field("packageTag") String packageTag);

    @GET(CommonApi.WAN_WX_LIST)
        // https://wanandroid.com/wxarticle/chapters/json
    Observable<BaseResponse<TestBean>> getWxList(@Path("param") String param);
}
