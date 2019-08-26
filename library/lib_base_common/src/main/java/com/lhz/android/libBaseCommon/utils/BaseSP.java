package com.lhz.android.libBaseCommon.utils;

import android.content.Context;

import com.lhz.android.libBaseCommon.https.public_parameters.HeadersPublic;


/**
 * Created by lhz on 2016/10/18.
 */

public class BaseSP {

    /**
     * 渠道号
     */
    public static void setChannel(Context context, String supportBankUrl) {
        BaseSPUtils.put(context, HeadersPublic.CHANNEL, supportBankUrl);
    }

    /**
     * 渠道号
     */
    public static String getChannel(Context context) {
        return (String) BaseSPUtils.get(context, HeadersPublic.CHANNEL, "0");
    }

}
