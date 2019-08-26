package com.lhz.android.libBaseCommon.https.observers_extension;


import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lhz.android.libBaseCommon.base.BaseApplication;
import com.lhz.android.libBaseCommon.base.BaseResponse;
import com.lhz.android.libBaseCommon.base.RouterPath;
import com.lhz.android.libBaseCommon.eventbus.EventType;
import com.lhz.android.libBaseCommon.https.exception.ExceptionUtil;


import org.greenrobot.eventbus.EventBus;

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
        Log.d(TAG, "服务器返回的code: " + tBaseResponse.getCode());
        switch (tBaseResponse.getCode()) {
            case 200:
                onSuccess(tBaseResponse.getData());
                break;
            default:
                onFailure(new Exception(tBaseResponse.getMsg()), tBaseResponse.getCode(), tBaseResponse.getMsg());
                break;
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        int code = ExceptionUtil.exceptionHandler(e);
        Log.d(TAG, "错误信息---" + e.getMessage() + "-----错误码: " + code);
        String msg = ExceptionUtil.getMsg(code);
        onFailure(e, code, msg);
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(T result);

    public void onFailure(Throwable e, int code, String errorMsg) {
        Log.d(TAG, "onFailure:  什么错误： " + e + "-------错误信息:" + errorMsg);
        if (code == 401) {
            EventBus.getDefault().post(new EventType(EventType.EVENT_NO_LOGIN));
            if (e instanceof HttpException) {
                HttpException httpException = (HttpException) e;
                HttpUrl url = httpException.response().raw().request().url();
                String s = String.valueOf(url);
                Log.d(TAG, "onFailure: " + s);
                if (!s.contains("api/account/index")) {
                    ARouter.getInstance().build(RouterPath.LOGIN_REGISTER_ACTIVITY).navigation();
                }
            }
        } else if (code == 402) {//实名认证
            EventBus.getDefault().post(new EventType(EventType.TO_REA_NAME_AUTHENTICATION));
        } else {
            Toast.makeText(BaseApplication.getInstance(),errorMsg,Toast.LENGTH_SHORT).show();
        }
    }

}
