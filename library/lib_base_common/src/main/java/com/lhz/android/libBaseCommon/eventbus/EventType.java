package com.lhz.android.libBaseCommon.eventbus;

/**
 * lhz  on 2019/8/21.
 * <p>
 * eventBus发送的类型
 */

public class EventType {
    public static final int EVENT_NO_LOGIN = 1000;//请重新登录
    public static final int TO_REA_NAME_AUTHENTICATION = 1001;//没有实名认证

    private int mType = -1;

    public EventType(int type) {
        mType = type;
    }

    public int getType() {
        return mType;
    }
}
