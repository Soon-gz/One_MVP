package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.dagger;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Shuwen on 2016/10/12.
 */
@Singleton
@Component(modules = AppModule.class)
public interface ApplicationComponent {
    //提供全局实例
    ActivityComponent plus(ActivityModule activityModule);
}
