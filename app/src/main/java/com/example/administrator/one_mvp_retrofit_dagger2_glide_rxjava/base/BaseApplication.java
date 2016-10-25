package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.SharedPreferencesUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.dagger.ActivityComponent;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.dagger.ActivityModule;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.dagger.AppModule;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.dagger.ApplicationComponent;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.dagger.DaggerApplicationComponent;

/**
 * Created by Shuwen on 2016/10/13.
 */

public class BaseApplication extends Application{
    //提供applicationComponent
    private static ApplicationComponent mApplicationComponent;
    //提供ActivityComponet
    private ActivityComponent activityComponent;
    //在Activity中调用该方法，获得Application
    public static BaseApplication get(Context context){
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //全局Toast可用，很方便。
        ToastUtils.init(this);
        //SharedPrefence存储小型数据，很方便的工具类
        SharedPreferencesUtils.init(this);
        mApplicationComponent = DaggerApplicationComponent.builder().appModule(new AppModule(this)).build();
    }

    public ActivityComponent createActivityComponent(Activity activity){
        activityComponent = mApplicationComponent.plus(new ActivityModule(activity));
        return activityComponent;
    }


    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
}
