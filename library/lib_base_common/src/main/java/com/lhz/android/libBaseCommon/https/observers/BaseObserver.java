package com.lhz.android.libBaseCommon.https.observers;


import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.lhz.android.libBaseCommon.base.BaseApplication;
import com.lhz.android.libBaseCommon.base.BaseResponse;
import com.lhz.android.libBaseCommon.https.exception.ExceptionUtil;
import com.lhz.android.libBaseCommon.https.exception.RxExceptionUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.HttpUrl;
import retrofit2.HttpException;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 请求服务器，返回的数据做同一处理
 */

public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {
    private static final String TAG = "BaseObserver";

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public final void onNext(BaseResponse<T> tBaseResponse) {
        Log.w(TAG, "服务器返回的code: " + tBaseResponse.getCode());
        switch (Integer.parseInt(tBaseResponse.getRetcode())) {
            case 0: // 请求成功
                onSuccess(tBaseResponse.getData());
                break;
            default:
                // 接口错误
                onFailure(new Exception(tBaseResponse.getMessage()),
                        Integer.parseInt(tBaseResponse.getRetcode()), tBaseResponse.getMessage());
                break;
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        int code = ExceptionUtil.exceptionHandler(e);
        Log.e(TAG, "错误信息---" + e.getMessage() + "-----错误码: " + code);
        String msg = ExceptionUtil.getMsg(code, e.getMessage());
        RxExceptionUtil.exceptionHandler(e);
        // 接口失败
        onFailure(e, code, msg);
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(T result);

    /**
     * 请求失败后的一些处理
     * 例如：未登录，跳转到登陆界面，eventBus发送通知
     */
    private void onFailure(Throwable e, int code, String errorMsg) {
        Log.e(TAG, "onFailure:  错误提示： " + e + "-------错误信息:" + errorMsg);
        if (code == 401) {
            // 401需要登录
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                HttpUrl url = httpException.response().raw().request().url();
                String s = String.valueOf(url);
                Log.e(TAG, "onFailure: " + s);
            }
        } else {
            Toast.makeText(BaseApplication.getInstance(), errorMsg, Toast.LENGTH_SHORT).show();
        }
    }

}
