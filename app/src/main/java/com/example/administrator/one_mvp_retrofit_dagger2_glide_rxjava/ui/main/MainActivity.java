package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Bind(R.id.main_title_tv)
    TextView main_title_tv;
    @Bind(R.id.main_radio)
    RadioGroup main_radio;
    @Bind(R.id.main_title_iv)
    ImageView main_title_iv;


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
                        main_title_tv.setText(getResources().getString(R.string.music_page_title));
                        FragmentHelper.showOneFragment(Const.MUSIC_PAGE_FRAGMENT);
                        break;
                    case R.id.one_movie_page_button:
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
                break;
            case R.id.main_individle_ib:
                break;
        }
    }
}
