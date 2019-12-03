package com.lhz.android.libBaseCommon.eventbus;

import org.greenrobot.eventbus.EventBus;

/**
 * 项目名称：MvpFrame
 * 创建人：LHZ
 * 创建时间：2019/11/27 17:43
 * 类描述：
 */
public class EventBusUtil {
    /**
     * 获取 eventBus 单例
     *
     * @return
     */
    public static EventBus getEventBus() {
        return EventBus.getDefault();
    }

    /**
     * eventBus 注册
     */
    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    /**
     * eventBus 注销
     */
    public static void unregister(Object subscriber) {
        EventBus.getDefault().unregister(subscriber);
    }

    /**
     * eventBus 发送普通事件
     */
    public static void sendEvent(MessageEvent event) {
        EventBus.getDefault().post(event);
    }

    /**
     * eventBus 发送粘性事件
     */
    public static void sendStickyEvent(MessageEvent event) {
        EventBus.getDefault().postSticky(event);
    }
}
