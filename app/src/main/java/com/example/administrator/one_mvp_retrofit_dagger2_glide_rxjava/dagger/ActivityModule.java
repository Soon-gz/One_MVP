package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.dagger;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Shuwen on 2016/10/13.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Provides
    @PerActivity
    public Activity prividesActivity(){
        return mActivity;
    }

    @Provides
    @PerActivity
    public Context providesContext(){
        return mActivity.getBaseContext();
    }
}
