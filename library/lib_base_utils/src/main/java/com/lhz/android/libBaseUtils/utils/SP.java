package com.lhz.android.libBaseUtils.utils;

import android.content.Context;


/**
 * Created by lhz on 2016/10/18.
 */

public class SP {

    public static boolean getLoginStatus(Context context) {
        return (boolean) SPUtils.get(context, SPKey.loginStatus, false);
    }

}
