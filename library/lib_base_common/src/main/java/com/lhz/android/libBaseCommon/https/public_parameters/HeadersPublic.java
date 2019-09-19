package com.lhz.android.libBaseCommon.https.public_parameters;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 请求头公共参数
 */

public class HeadersPublic {

    /**
     * module 模块
     */
    public static final String MODULE = "module";
    /**
     * sessionToken 登录Token
     */
    public static final String SESSION_TOKEN = "sessionToken";
    /**
     * deviceId 设备号
     */
    public static final String DEVICE_ID = "deviceId";
    /**
     * clientIp 设备IP
     */
    public static final String CLIENT_IP = "clientIp";
    /**
     * clientType 设备类型
     */
    public static final String CLIENT_TYPE = "clientType";
    /**
     * version app版本号
     */
    public static final String VERSION = "version";
    /**
     * traceId 会话标识符，请求接口标识
     */
    public static final String TRACE_ID = "traceId";
    /**
     * type 平台
     */
    public static final String TYPE = "android";//0:web端注册; 1:android移动端; 2:ios移动端; 3:微信注册;

    public static final String MODULE_APP = "2";

    public static final String APP_KEY = "6643150578D49C0BFAB2A53DBC52B801";

    public static final String APP_SECRET = "D580AB0E7BEAEAB465463444BC1A9533";
    /**
     * 登陆的token
     */
    public static final String TOKEN = "token";
    /**
     * 渠道信息
     */
    public static final String CHANNEL = "channel";
    public static final String CHANNEL_SIGNA = "channelSigna";
    /**
     * 接口版本号
     */
    public static final String METHOD_VERSION_CODE = "";
}
