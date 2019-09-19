package com.lhz.android.libBaseCommon.base;

/**
 * lhz  on 2019/8/21.
 */

public class BaseResponse<T> {
    private int code;         //成功 = 0
    private String msg;
    private T data;
    // ---------------------
    private boolean retOk;
    private String retcode;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean getRetOk() {
        return retOk;
    }

    public void setRetOk(boolean retOk) {
        this.retOk = retOk;
    }

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
