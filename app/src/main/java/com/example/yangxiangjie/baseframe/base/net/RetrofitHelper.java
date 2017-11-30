package com.example.yangxiangjie.baseframe.base.net;

import com.example.yangxiangjie.baseframe.App;
import com.example.yangxiangjie.baseframe.Config;
import com.example.yangxiangjie.baseframe.base.net.interceptor.HttpHeadInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.socks.library.KLog;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangxiangjie on 2017/9/15.
 * 网络访问库
 */

public class RetrofitHelper {

    private static final String TAG = "RetrofitHelper";

    /**
     * 超时时间 5s
     */
    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_READ_WRITE_TIME_OUT = 10;
    private Retrofit mRetrofit;

    private OkHttpClient mHttpClient;

    /**
     * 静态内部类创建单列
     */
    private static class SingleHolder {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    private RetrofitHelper() {

        // 创建 OKHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //连接超时时间
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        //写操作 超时时间
        builder.writeTimeout(DEFAULT_READ_WRITE_TIME_OUT, TimeUnit.SECONDS);
        //读操作超时时间
        builder.readTimeout(DEFAULT_READ_WRITE_TIME_OUT, TimeUnit.SECONDS);

        // TODO: 添加公共参数拦截器
        HttpHeadInterceptor commonInterceptor = new HttpHeadInterceptor.Builder()
//                .addHeaderParams("paltform", "android")
                .build();
        builder.addInterceptor(commonInterceptor);

        //添加日志拦截
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                if (Config.isDebug) {
                    try {
                        String msg = URLDecoder.decode(message, "UTF-8");
                        KLog.json(TAG, msg);
                    } catch (UnsupportedEncodingException e) {
                        KLog.json(TAG, message);
                    }
                }
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        //开启缓存  //10M缓存
        File httpCacheDirectory = new File(App.getAppContext().getExternalCacheDir(), "Cache");
        builder.cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024));

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Urls.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * 获取当前类的对象
     *
     * @return
     */
    public static RetrofitHelper getInstance() {
        return SingleHolder.INSTANCE;
    }

    /**
     * 创建Retrofit所需的Service
     *
     * @param service Class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }


    // TODO: 2017/10/30 后期后期定义一个BaseHttpResponse
    public <T> void toSubscribe(Observable<T> observable, BaseObserver<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


}
