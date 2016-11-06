package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.BaseFgAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends BaseFragment {

    @Bind(R.id.movie_viewpager)
    ViewPager movie_viewpager;
    private List<BaseFragment>fragVPs;
    private BaseFgAdapter adapter;

    @Override
    protected void initViewsAndEvents() {
        fragVPs = new ArrayList<>();
        fragVPs.add(MovieFragVP.newInstance(Const.PAGE_MAIN_IS_FIRST,""));
        fragVPs.add(MovieFragVP.newInstance(Const.PAGE_MAIN_OTHER,""));
        adapter = new BaseFgAdapter(getFragmentManager(),fragVPs);
        movie_viewpager.setAdapter(adapter);
        movie_viewpager.setCurrentItem(1);
        movie_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    movie_viewpager.setCurrentItem(1);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_movie;
    }

}
