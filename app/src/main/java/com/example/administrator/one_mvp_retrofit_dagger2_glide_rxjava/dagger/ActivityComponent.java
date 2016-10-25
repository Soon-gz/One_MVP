package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.dagger;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Shuwen on 2016/10/13.
 */

/**
 *在这里我遇见了一个大坑，必须要写点注释，以安慰我的受伤的小心灵。
 * 这里的ActivityComponent是子Component也就是依附于ApplicationComponent,依附的时候分手动生成依附方法，和自动依附方法
 * 当写Subcomponent的时候，需要在ApplicationComponent建一个用于ActivityComponent依附的方法plus（f方法名任意），返回值
 * 必须是ActivityComponent，而写Component(dependencies = AllicationComponent.class)时，就不用创建一个方法去依附，
 *DaggerActivityComponent会自动生成一个方法applicationComponent（ApplicationComponent component）只需要在BaseActivity
 * 调用依附即可。
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    //通过ActivityComponent提供Activity实例
}
