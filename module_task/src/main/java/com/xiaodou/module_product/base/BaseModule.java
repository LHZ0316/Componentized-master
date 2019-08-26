package com.xiaodou.module_product.base;


import com.lhz.android.libBaseCommon.https.RetrofitManager;

/**
 * lhz  on 2019/8/21.
 */

public class BaseModule {

   public static ProductService createrRetrofit() {
      return RetrofitManager.getInstance().getRetrofitService(ProductService.class);
   }
}
