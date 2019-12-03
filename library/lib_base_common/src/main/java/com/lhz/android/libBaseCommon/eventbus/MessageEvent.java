package com.lhz.android.libBaseCommon.eventbus;

/**
 * 项目名称：MvpFrame
 * 创建人：LHZ
 * 创建时间：2019/11/27 17:43
 * 类描述：
 */
public class MessageEvent<T> {
    private int code;
    private T data;

    public MessageEvent(int code) {
        this.code = code;
    }

    public MessageEvent(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
