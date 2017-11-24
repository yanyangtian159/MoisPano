package com.deepai.moispano.network;

import com.deepai.moispano.constant.Constant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2017/11/24.
 */

public abstract class RetrofitUtils {

    //服务器路径
    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    /**
     * 获取Retrofit对象
     *
     * @return
     */
    protected static Retrofit getRetrofit() {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Constant.hostName + "/")
                    .addConverterFactory(GsonConverterFactory.create())//添加转化库，默认是Gson
                    .addConverterFactory(StringConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //添加回调库，采用RxJava
                    .client(mOkHttpClient) //设置使用okhttp网络请求
                    .build();
        }
        return mRetrofit;
    }

}
