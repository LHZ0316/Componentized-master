package com.lhz.android.libBaseCommon.https.scheduler;

import com.lhz.android.libBaseCommon.base.IBaseView;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * lhz  on 2019/8/21.
 * <p>
 * 切换线程 与绑定rxLifecycle 生命周期
 */

public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> observableIO2Main(IBaseView iBaseView) {
        return upstream -> upstream.subscribeOn(Schedulers.io())// 事件发生的线程
                .observeOn(AndroidSchedulers.mainThread())// 事件消费回调后的线程
                .compose(iBaseView.bindLifecycle()); // 对 Observable自身 整体的变换
    }
}