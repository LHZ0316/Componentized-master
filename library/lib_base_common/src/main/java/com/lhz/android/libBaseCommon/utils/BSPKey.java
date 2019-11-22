package com.lhz.android.libBaseCommon.utils;

import android.content.Context;

import com.lhz.android.libBaseCommon.https.parameters.HeaderParams;


/**
 * Created by lhz on 2016/10/18.
 */

public class BSPKey {

    /**
     * 渠道信息
     */
    public static void setChannel(Context context, String supportBankUrl) {
        BSPUtil.put(context, HeaderParams.CHANNEL, supportBankUrl);
    }

    /**
     * 渠道信息
     */
    public static String getChannel(Context context) {
        return (String) BSPUtil.get(context, HeaderParams.CHANNEL, "0");
    }

}
