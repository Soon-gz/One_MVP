package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.guide;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ActivityManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.SharedPreferencesUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.main.MainActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.welcome.WelComeActivity;

import butterknife.Bind;

public class GuideActivity extends BaseActivity {

    @Bind(R.id.guide_iv)
    ImageView guide_iv;

    private int START_TYPE = 0;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_guide;
    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        if (SharedPreferencesUtils.getBoolean(Const.ISFIRSTRUN)){
            START_TYPE = Const.START_WELCOME;
        }else {
            START_TYPE = Const.START_GUIDE;
        }
        swithStartType(START_TYPE);
    }

    private void swithStartType(int start_type) {
        switch (start_type){
            case Const.START_WELCOME:
                startActivity(new Intent(this, WelComeActivity.class));
                break;
            case Const.START_GUIDE:
                Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_guide_iv);
                animation.setFillAfter(true);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        startActivity(new Intent(GuideActivity.this, MainActivity.class));
                        ActivityManager.getInstance().finishACtivity(GuideActivity.this);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                guide_iv.startAnimation(animation);
                break;
        }
    }
}
