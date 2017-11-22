package com.deepai.moispano.observer;

import android.content.Context;

import com.deepai.moispano.base.BaseObserver;
import com.deepai.moispano.data.exception.ApiException;
import com.deepai.moispano.utils.LogUtil;
import com.deepai.moispano.utils.NetworkUtil;

import io.reactivex.disposables.Disposable;

/**
 * 封装Observer
 *
 * @author gc
 * @since 1.0
 */
public abstract class CommonObserver<T> extends BaseObserver<T> {
    private static final String TAG = "CommonObserver";
    private Context context;
    // Disposable 相当于RxJava1.x中的 Subscription，用于解除订阅 disposable.dispose();
    protected Disposable disposable;

    public CommonObserver(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
        disposable = d;
        if (!NetworkUtil.isNetworkAvailable(context)) {
            LogUtil.e("网络不可用");
        } else {
            LogUtil.e("网络可用");
        }
    }

    @Override
    protected void onError(ApiException e) {
        LogUtil.e("HTTP错误 --> " + "code:" + e.code + ", message:" + e.message);
    }

    @Override
    public void onComplete() {
        LogUtil.e("成功了");
    }

}
