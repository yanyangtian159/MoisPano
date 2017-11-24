package com.deepai.moispano.network;

import com.deepai.moispano.App;
import com.deepai.moispano.constant.Constant;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * Created by lenovo on 2017/11/24.
 */

public class OkHttp3Utils {
    private static OkHttpClient mOkHttpClient;

    //设置缓存目录
    private static File cacheDirectory = new File(App.getInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "MyCache");
    private static Cache cache = new Cache(cacheDirectory, Constant.cacheSize);


    /**
     * 获取OkHttpClient对象
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {

        if (null == mOkHttpClient) {
            mOkHttpClient = new OkHttpClient.Builder()
//                    .cookieJar(new CookieJar() {
//                        @Override
//                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//
//                        }
//
//                        @Override
//                        public List<Cookie> loadForRequest(HttpUrl url) {
//                            return null;
//                        }
//                    }) //设置一个自动管理cookies的管理器
                    //.addInterceptor(new MyIntercepter()) //添加拦截器
                    //.addNetworkInterceptor(new CookiesInterceptor(MyApplication.getInstance().getApplicationContext())) //添加网络连接器
                    //设置请求读写的超时时间
                    .connectTimeout(Constant.connectTimeOut, TimeUnit.SECONDS)
                    .writeTimeout(Constant.writerTimeOut, TimeUnit.SECONDS)
                    .readTimeout(Constant.readTimeOut, TimeUnit.SECONDS)
                    .cache(cache)
                    .build();
        }
        return mOkHttpClient;
    }
}
