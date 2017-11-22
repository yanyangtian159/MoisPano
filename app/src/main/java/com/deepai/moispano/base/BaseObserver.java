package com.deepai.moispano.base;

import com.deepai.moispano.data.exception.ApiException;

import io.reactivex.Observer;

/**
 * @author ZhaoZaigang
 * @Description 观察者基类
 * @date 2017/10/25  17:33
 */

public  abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onError(Throwable e) {
        ApiException apiException = (ApiException) e;
        onError(apiException);
    }

    /**
     * @param e 错误的一个回调
     */
    protected abstract void onError(ApiException e);
}
