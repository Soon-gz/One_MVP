package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.BaseApplication;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.BaseEventBus;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ActivityManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.dagger.ActivityComponent;

import butterknife.ButterKnife;

/**
 * Created by Shuwen on 2016/10/13.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private ActivityComponent mActivityComponent;
    private long currentBackPressedTime = 0;                   // 点击返回键时间
    private static final int BACK_PRESSED_INTERVAL = 1000;    // 两次点击返回键时间间隔

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置Activity的layout
        if (getContentViewLayoutID() != 0) {
            setContentView(getContentViewLayoutID());
        } else {
            throw new IllegalArgumentException("你必须设置一个可以用的Layout资源 ID");
        }

        //一般绑定Precenter
        iniInjector();
        //在子Activity中类似onCreate方法
        initViewsAndEvents(savedInstanceState);
        //将Activity放入ActivityManager统一管理
        ActivityManager.getInstance().putActivity(this);
    }

    /**
     * 连续两次点击返回键，回到桌面
     */
    @Override
    public void onBackPressed() {
        // 判断时间间隔
        if (System.currentTimeMillis() - currentBackPressedTime > BACK_PRESSED_INTERVAL) {
            currentBackPressedTime = System.currentTimeMillis();
            ToastUtils.showToast(getResources().getString(R.string.toast_main_back_hint));
        } else {
            //返回桌面
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            ActivityManager.getInstance().finishAllActivity();
            startActivity(intent);
        }
    }

    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = BaseApplication.get(this).createActivityComponent(this);
        }
        return mActivityComponent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //将activity从自定义管理栈中移除
        ActivityManager.getInstance().finishACtivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    //EventBus的使用，用于组件之间的数据交互
    public void onEventMainThread(BaseEventBus event) {
    }

    //载入layoutID
    protected abstract int getContentViewLayoutID();
    //依赖注入
    protected abstract void iniInjector();
    //初始化视图或者方法
    protected abstract void initViewsAndEvents(Bundle savedInstanceState);
}
