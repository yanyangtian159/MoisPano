package com.deepai.moispano.transformer;


import com.deepai.moispano.base.BaseHttpResult;
import com.deepai.moispano.data.exception.ErrorType;
import com.deepai.moispano.data.exception.ExceptionEngine;
import com.deepai.moispano.data.exception.ServerException;
import com.deepai.moispano.utils.LogUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Error Transformer
 *
 * @author gc
 * @since 1.0
 */
public class ErrorTransformer<T> implements ObservableTransformer<BaseHttpResult<T>, T> {
    private static ErrorTransformer errorTransformer = null;
    private static final String TAG = "ErrorTransformer";

    @Override
    public ObservableSource<T> apply(@NonNull Observable<BaseHttpResult<T>> upstream) {
        return upstream.map(new Function<BaseHttpResult<T>, T>() {
            @Override
            public T apply(@NonNull BaseHttpResult<T> tBaseHttpResult) throws Exception {
                if (tBaseHttpResult == null) {
                    throw new ServerException(ErrorType.EMPTY_BEAN, "解析对象为空");
                }
                LogUtil.e(tBaseHttpResult.toString());
                if (tBaseHttpResult.getCode() != ErrorType.SUCCESS) {
                    throw new ServerException(tBaseHttpResult.getCode(),
                            tBaseHttpResult.getMessage());
                }
                return tBaseHttpResult.getData();
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
            @Override
            public ObservableSource<? extends T> apply(@NonNull Throwable throwable)
                    throws Exception {
                // ExceptionEngine 为处理异常的驱动器 throwable
                throwable.printStackTrace();
                return Observable.error(ExceptionEngine.handleException(throwable));
            }
        });

    }

    /**
     * @return 线程安全, 双层校验
     */
    public static <T> ErrorTransformer<T> getInstance() {
        if (errorTransformer == null) {
            synchronized (ErrorTransformer.class) {
                if (errorTransformer == null) {
                    errorTransformer = new ErrorTransformer<>();
                }
            }
        }
        return errorTransformer;
    }

}
