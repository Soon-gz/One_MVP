package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Administrator on 2016/10/13.
 */

public class RetrofitUtils {
    public static final String BASE_URL = "http://v3.wufazhuce.com:8000/api/hp/";
    public static Retrofit singleton;
    public static <T> T createApi(Class<T> clazz, Interceptor interceptor, RxJavaCallAdapterFactory factory) {
        //if (singleton == null) {
        synchronized (RetrofitUtils.class) {
            if (singleton == null) {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        //.addConverterFactory(FastJsonConverterFactory.create())
                        .addConverterFactory(JsonConverterFactory.create());
                // .addConverterFactory(FastJsonConvertFactory.create());
                if (interceptor != null) {
                    builder.client(createInterceptor(interceptor));
                }
                if (factory != null) {
                    builder.addCallAdapterFactory(factory);
                }
                singleton = builder.build();
            }
        }
        return singleton.create(clazz);
    }


    public static <T> APIService createApi(RxJavaCallAdapterFactory factory) {
        return createApi(APIService.class, null, factory);
    }

    private static OkHttpClient createInterceptor(Interceptor interceptor) {
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(interceptor);
        return client;
    }
}
