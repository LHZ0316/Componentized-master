package com.lhz.android.libBaseCommon.base;

import com.google.gson.Gson;
import com.lhz.android.libBaseCommon.BuildConfig;
import com.lhz.android.libBaseCommon.https.RequestParam;
import com.orhanobut.logger.Logger;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * lhz  on 2019/8/21.
 * <p>
 * API基类
 */

public class BaseApi {

    public enum HostType {
        TEST_1,
        ON_TEST,
        ON_LINE
    }

    private static String mApiHost;
    private static String BASE_URL_API = "https://mapi-2020.moycp.com/";
    private static String BASE_URL_API_TEST = "http://service.dev.51xiaodou.com:8020/";
    private static String IMG_URL_API = "http://7xigj3.com1.z0.glb.clouddn.com/";

    public static void host(HostType hostType, boolean isDebug) {
        if (isDebug) {
            switch (hostType) {
                case ON_TEST:
                    mApiHost = "http://service.dev.51xiaodou.com:8020/";//测试环境一
                    break;
                case ON_LINE:
                    mApiHost = "https://mapi-2020.moycp.com/";//线上环境
                    break;
                default:
                    mApiHost = BuildConfig.API_URL;// 根据环境变化
                    break;
            }
        } else {
            mApiHost = BaseApi.BASE_URL_API; // 线上环境
        }
    }

    /**
     * 获取主地址
     */
    public static String getBaseUrl() {
        Logger.e("当前主地址：===" + BuildConfig.API_URL);
        return mApiHost == null ? BuildConfig.API_URL : mApiHost;
    }

    /**
     * POST请求，带参数
     * 将请求参数转化成RequestBody
     *
     * @param map
     */
    public static RequestBody getRequestBody(RequestParam map) {
        Gson gson = new Gson();
        String paramMap = gson.toJson(map);
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), paramMap);
    }
}
