package com.xiaodou.core.module;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiaodou.core.view.MainActivity;
import com.xiaodou.router.moduleCore.CoreRouterPath;
import com.xiaodou.router.moduleCore.ICoreProvider;

/**
 * 项目名称：MvpFrame
 * 创建人：LHZ
 * 创建时间：2019/11/26 18:42
 * 类描述：核心模块界面的路由服务
 */
@Route(path = CoreRouterPath.ROUTER_PATH_TO_LOGIN)
public class ICoreService implements ICoreProvider {
    @Override
    public void init(Context context) {

    }

    @Override
    public void goToLogin(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
