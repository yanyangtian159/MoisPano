package com.deepai.moispano.network;

import com.deepai.moispano.data.entry.BannerBean;
import com.deepai.moispano.data.entry.UserBean;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2017/11/24.
 */

public class NetWorks extends RetrofitUtils {

    //创建实现接口调用
    protected static final NetService service = getRetrofit().create(NetService.class);

    //设缓存有效期为1天
    protected static final long CACHE_STALE_SEC = 60;//单位s
    //查询缓存的Cache-Control设置，使用缓存
    protected static final String CACHE_CONTROL_CACHE = "max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置。不使用缓存
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private interface NetService {

        //POST请求
        @FormUrlEncoded
        @Headers("Cache-Control: public," + CACHE_CONTROL_CACHE)
        @POST("bjws/app.user/login")
        Observable<BannerBean> getVerfcationCodePostMap(@FieldMap Map<String, String> map);

        //POST请求
        @FormUrlEncoded
        @Headers("Cache-Control: public," + CACHE_CONTROL_CACHE)
        @POST("ais/plan/7/boot.xml")
        Observable<String> StringCodePostMap(@FieldMap Map<String, String> map);

        //GET请求，设置缓存
        @Headers("Cache-Control: public," + CACHE_CONTROL_CACHE)
        @GET("ais/plan/7/boot.xml")
        Observable<String> getVerfcationGetCache();
    }



    //POST请求参数以map传入
    public static void verfacationCodePostMap(Map<String, String> map,Observer<BannerBean> observer) {
        setSubscribe(service.getVerfcationCodePostMap(map),observer);
    }

    //POST请求参数以map传入
    public static void StringCodePostMap(Map<String, String> map,Observer<String> observer) {
        setSubscribe(service.StringCodePostMap(map),observer);
    }

    //Get请求参数以map传入
    public static void getStringCodePostMap(Observer<String> observer) {
        setSubscribe(service.getVerfcationGetCache(),observer);
    }

    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

}
