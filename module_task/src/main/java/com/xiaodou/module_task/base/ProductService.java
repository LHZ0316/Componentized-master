package com.xiaodou.module_task.base;

import com.lhz.android.libBaseCommon.base.BaseResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * lhz  on 2019/8/21.
 */

public interface ProductService {
    /**
     * 产品列表
     */
    @POST(ProductApi.PRODUCT_LIST)
    Observable<BaseResponse> getProductList(@Body RequestBody requestBody);
}
