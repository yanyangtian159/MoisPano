//package com.deepai.moispano.transformer;
//
//
//import io.reactivex.Observable;
//import io.reactivex.ObservableSource;
//import io.reactivex.ObservableTransformer;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.annotations.NonNull;
//import io.reactivex.schedulers.Schedulers;
//import okhttp3.MediaType;
//
///**
// * 封装Transformer
// *
// * @author gc
// * @since 1.0
// */
//public class CommonTransformer<T> implements ObservableTransformer<BaseHttpResult<T>, T> {
//    protected final MediaType STRING_TYPE = MediaType.parse("text/plain");
//    protected final MediaType IMAGE_TYPE = MediaType.parse("image/*");
//    protected final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
//    @Override
//    public ObservableSource<T> apply(@NonNull Observable<BaseHttpResult<T>> upstream) {
//        return upstream.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()) // UI线程
//                .compose(ErrorTransformer.<T>getInstance());
//    }
//}
//
