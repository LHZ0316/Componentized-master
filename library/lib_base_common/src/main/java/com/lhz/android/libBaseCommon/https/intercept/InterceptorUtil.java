package com.lhz.android.libBaseCommon.https.intercept;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.lhz.android.libBaseCommon.BuildConfig;
import com.lhz.android.libBaseCommon.base.BaseApplication;
import com.lhz.android.libBaseCommon.https.public_parameters.HeadersPublic;
import com.lhz.android.libBaseCommon.utils.BaseSPUtil;
import com.lhz.android.libBaseCommon.utils.MD5Util;
import com.lhz.android.libBaseCommon.utils.Utils;
import com.orhanobut.logger.Logger;

import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import okio.BufferedSource;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 日志拦截器
 */

public class InterceptorUtil {
    private static String TAG = "HTTP-----";
    private static final Charset UTF8 = Charset.forName("UTF-8");

    // 日志拦截器
    public static HttpLoggingInterceptor LogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Log.w(TAG, "log: " + message);
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY);//设置打印数据的级别
    }

    // 请求头拦截器
    public static Interceptor HeadersInterceptor(Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                // 设备编号
                String deviceId = getDeviceId(context);
                if (TextUtils.isEmpty(deviceId)) {
                    deviceId = "";
                }
                // 时间戳
                String timestamp = String.valueOf(System.currentTimeMillis());
                Request request = chain.request().newBuilder()
                        .addHeader(HeadersPublic.MODULE, HeadersPublic.MODULE_APP)
                        .addHeader(HeadersPublic.SESSION_TOKEN, String.valueOf(BaseSPUtil.get(context, HeadersPublic.TOKEN, "")))
                        .addHeader(HeadersPublic.DEVICE_ID, deviceId)
                        .addHeader(HeadersPublic.CLIENT_IP, getLocalIpAddress(BaseApplication.getInstance()))
                        .addHeader(HeadersPublic.CLIENT_TYPE, HeadersPublic.TYPE)
                        .addHeader(HeadersPublic.VERSION, String.valueOf(BuildConfig.VERSION_NAME))
                        .addHeader(HeadersPublic.TRACE_ID, getSigna(timestamp)).build();
                return chain.proceed(request);
            }
        };
    }

    /**
     * 添加header
     *
     * @return
     */
    public Interceptor HeaderInterceptor(Context context) {

        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                // 重新处理请求
                Request newRequest = refreshRequest(chain.request(), context);
                // 执行请求
                Response response = chain.proceed(newRequest);
                // 打印请求url和结果
                logResponse(response);
                return response;
            }
        };
    }

    /**
     * 添加header参数
     *
     * @param request
     * @return
     */
    private Request refreshRequest(Request request, Context context) throws IOException {
        Request.Builder requestBuilder = request.newBuilder();
        // 获取请求地址
        HttpUrl url = request.url();
        // 获取请求参数（可以加密）
        collectParams(request);
        // 添加请求头
        addHeaderParams(requestBuilder, context);
        return requestBuilder.build();
    }

    /**
     * 处理请求头
     * app包名：appPackageName BaseApplication.getInstance().getPackageName()
     * app版本号：version  String.valueOf(BuildConfig.VERSION_CODE)
     * 请求方法版本号：method_version   接口无多个版本方法，可以为空
     * 渠道名称：channel        BaseSP.getChannel(context)
     * 渠道签名：channelSigna   getSigna(BaseSP.getChannel(context))
     * 登录标识：token			 String.valueOf(BaseSPUtil.get(context, HeadersPublic.TOKEN, ""))
     * 请求签名：sign
     * 时间戳：timestamp
     * 设备序列号：deviceId
     * 移动设备类型：deviceType    1.安卓  2.IOS
     * <p>
     * //     * @param builder
     */
    private void addHeaderParams(Request.Builder request, Context context) {
        // 设备编号
        String deviceId = getDeviceId(context);
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "";
        }
        // 时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());

        // 请求头参数
        request.addHeader(HeadersPublic.MODULE, HeadersPublic.MODULE_APP);
        request.addHeader(HeadersPublic.SESSION_TOKEN, String.valueOf(BaseSPUtil.get(context, HeadersPublic.TOKEN, "")));
        request.addHeader(HeadersPublic.DEVICE_ID, deviceId);
        request.addHeader(HeadersPublic.CLIENT_IP, getLocalIpAddress(BaseApplication.getInstance()));
        request.addHeader(HeadersPublic.CLIENT_TYPE, HeadersPublic.TYPE);
        request.addHeader(HeadersPublic.VERSION, String.valueOf(BuildConfig.VERSION_NAME));
        request.addHeader(HeadersPublic.TRACE_ID, getSigna(timestamp));

    }

    private Map<String, String> mHeadParamMap = new LinkedHashMap<>();

    /**
     * 把获取的参数添加到map集合
     *
     * @param builder
     * @return
     */
    private void collectParams(Request builder) throws IOException {
        // GET请求有可能为空
        if (builder.body() == null) {
            return;
        }
        RequestBody requestBody = builder.body();
        if (requestBody != null) {
            Map<String, String> bodyMap = new LinkedHashMap<>();
            String jsonBody = "";
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            if (isPlaintext(buffer)) {
                if (charset != null) {
                    jsonBody = buffer.readString(charset);
                }
                Logger.e("请求参数-----jsonBody:" + jsonBody);
            }

            // 请求体json形式
//        if (!TextUtils.isEmpty(jsonBody)) {
//            bodyMap = new Gson().fromJson(jsonBody, new TypeToken<HashMap<String, Object>>() {
//            }.getType());
//        }
//        mHeadParamMap = bodyMap;

        }

    }

    /**
     * 直接读取body().toString()的话，会消耗掉响应流，这里手动复制响应流
     */
    private void logResponse(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        if (responseBody == null) {
            return;
        }
        long contentLength = responseBody.contentLength();
        if (!bodyEncoded(response.headers())) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = UTF8;
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(UTF8);
                } catch (UnsupportedCharsetException e) {
                    e.printStackTrace();
                    return;
                }
            }
            if (!isPlaintext(buffer)) {
                return;
            }
            if (contentLength != 0) {
                String result = buffer.clone().readString(charset);
                Logger.e("---------新API，请求结果----------------------");
                Logger.e("请求结果-----response.body():" + result);
            }
        }
    }

    private static boolean isPlaintext(Buffer buffer) throws EOFException {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }


    private static boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    /**
     * 一般接口调用-signa签名生成规则
     *
     * @param ts 时间戳
     */
    private static String getSigna(String ts) {
        // appsecrt拼接ts的字符串后进行MD5（32）
        String signa = MD5Util.MD5Encode(HeadersPublic.APP_SECRET + ts, "UTF-8");
        // 得到的MD5串拼接appkey再次MD5，所得结果转大写
        signa = MD5Util.MD5Encode(signa + HeadersPublic.APP_KEY, "UTF-8").toUpperCase();
        return signa;
    }

    /**
     * 获取手机deviceId
     */

    @SuppressLint({"HardwareIds", "MissingPermission"})
    private static String getDeviceId(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
    }

    /**
     * 获取本地IP地址
     *
     * @param context
     * @return
     */
    private static String getLocalIpAddress(Context context) {

        String ip = null;
        ConnectivityManager conMann = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileNetworkInfo = null;
        if (conMann != null) {
            mobileNetworkInfo = conMann.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        }
        NetworkInfo wifiNetworkInfo = null;
        if (conMann != null) {
            wifiNetworkInfo = conMann.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        }

        if (mobileNetworkInfo != null) {
            if (mobileNetworkInfo.isConnected()) {
                ip = getNetworkAddress();
                System.out.println("localIp" + ip);

            } else if (wifiNetworkInfo.isConnected()) {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext()
                        .getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = null;
                if (wifiManager != null) {
                    wifiInfo = wifiManager.getConnectionInfo();
                }
                int ipAddress = 0;
                if (wifiInfo != null) {
                    ipAddress = wifiInfo.getIpAddress();
                }
                ip = intToIp(ipAddress);
                System.out.println("localIp" + ip);
            }
        }
        return ip;
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }

    private static String getNetworkAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            System.out.println("localIp" + ex.toString());
        }
        return null;
    }

}