package com.lhz.android.libBaseCommon.eventbus;

/**
 * lhz  on 2019/8/21.
 * <p>
 * eventBus发送到不同页面的dialog
 */

public class EventParameter {

    /**
     * 参数key from
     * 用法
     * ARouter.getInstance().build(RouterPath.MY_ASSETS_AUTHENTICATION_ACTIVITY)
     .withString(EventParameter.EXTRA_FROM, EventParameter.EXTRA_FROM_HTML).navigation();
     */
    public final static String EXTRA_FROM = "from";
    public final static String EXTRA_FROM_HOME = "home";//首页
    public final static String EXTRA_FROM_HTML = "html";//H5页面

}
