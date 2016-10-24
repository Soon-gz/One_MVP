package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.welcome;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.SharedPreferencesUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import butterknife.Bind;

public class WelComeActivity extends BaseActivity {


    @Bind(R.id.one_welcome_vp)
    ViewPager one_welcome_vp;
    //viewpager的适配器
    private WelcomeVpAdapter welcomeVpAdapter;
    //viewpager的数据源
    private List<WelcomeFragment>fragmentList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_wel_come;
    }

    @Override
    protected void iniInjector() {
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        //判断是否是第一次运行软件
        SharedPreferencesUtils.put(Const.ISFIRSTRUN,false);
        //初始化
        fragmentList = new ArrayList<>();
        fragmentList.add(WelcomeFragment.newInstance(Const.READING_GUIDE,""));
        fragmentList.add(WelcomeFragment.newInstance(Const.MUSIC_GUIDE,""));
        fragmentList.add(WelcomeFragment.newInstance(Const.MOVIE_GUIDE,""));
        fragmentList.add(WelcomeFragment.newInstance(Const.ONE_GUIDE,""));
        welcomeVpAdapter = new WelcomeVpAdapter(getSupportFragmentManager(),fragmentList);
        one_welcome_vp.setOffscreenPageLimit(4);
        one_welcome_vp.setAdapter(welcomeVpAdapter);
        //设置viewpager的监听事件
        one_welcome_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < 4; i++) {
                    fragmentList.get(i).setPause();
                    if (i == 3){
                        fragmentList.get(i).stopBtn();
                    }
                }
                if (position == 3){
                    fragmentList.get(position).startBtn();
                }
                fragmentList.get(position).start();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
