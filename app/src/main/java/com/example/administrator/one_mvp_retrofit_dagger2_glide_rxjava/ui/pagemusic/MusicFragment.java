package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends BaseFragment {

    @Bind(R.id.music_page_viewpager)
    ViewPager music_page_viewpager;

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_music;
    }



}
