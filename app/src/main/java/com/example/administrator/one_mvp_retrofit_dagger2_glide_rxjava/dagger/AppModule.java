package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.dagger;

import android.app.Application;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.APIService;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.RetrofitUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Shuwen on 2016/10/12.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @Singleton
    @Provides
    public Application providesApplication(){
        return application;
    }

    @Provides
    @Singleton
    APIService provideAPIService() {
        return RetrofitUtils.createApi(RxJavaCallAdapterFactory.create());
    }

}
