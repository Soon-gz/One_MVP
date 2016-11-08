package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ActivityManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.search.SearchActivity;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Bind(R.id.main_title_tv)
    TextView main_title_tv;
    @Bind(R.id.main_radio)
    RadioGroup main_radio;
    @Bind(R.id.main_title_iv)
    ImageView main_title_iv;

    private long currentBackPressedTime = 0;                   // 点击返回键时间
    private static final int BACK_PRESSED_INTERVAL = 1000;    // 两次点击返回键时间间隔


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void iniInjector() {
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        FragmentHelper.initFragments(getSupportFragmentManager());
        FragmentHelper.initMainFragments();
        initListener();
    }

    private void initListener() {
        main_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.one_home_page_button:
                        main_title_iv.setVisibility(View.VISIBLE);
                        main_title_tv.setVisibility(View.GONE);
                        FragmentHelper.showOneFragment(Const.MAIN_PAGE_FRAGMENT);
                        break;
                    case R.id.one_reading_page_button:
                        main_title_iv.setVisibility(View.GONE);
                        main_title_tv.setVisibility(View.VISIBLE);
                        main_title_tv.setText(getResources().getString(R.string.reading_page_title));
                        FragmentHelper.showOneFragment(Const.READING_PAGE_FRAGMENT);
                        break;
                    case R.id.one_music_page_button:
                        main_title_iv.setVisibility(View.GONE);
                        main_title_tv.setVisibility(View.VISIBLE);
                        main_title_tv.setText(getResources().getString(R.string.music_page_title));
                        FragmentHelper.showOneFragment(Const.MUSIC_PAGE_FRAGMENT);
                        break;
                    case R.id.one_movie_page_button:
                        main_title_iv.setVisibility(View.GONE);
                        main_title_tv.setVisibility(View.VISIBLE);
                        main_title_tv.setText(getResources().getString(R.string.movie_page_title));
                        FragmentHelper.showOneFragment(Const.MOVIE_PAGE_FRAGMENT);
                        break;
                }
            }
        });
    }

    @Override
    public int getStatusColor() {
        return R.color.black;
    }

    @OnClick({R.id.main_search_ib,R.id.main_individle_ib})
    public void mainOnClick(View view){
        switch (view.getId()){
            case R.id.main_search_ib:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.main_individle_ib:
                break;
        }
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
}
