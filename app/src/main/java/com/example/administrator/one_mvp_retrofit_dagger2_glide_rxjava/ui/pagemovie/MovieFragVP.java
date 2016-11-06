package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieFragVP#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieFragVP extends BaseFragment {

    @Bind(R.id.fragment_movie_framelayout)
    FrameLayout fragment_movie_framelayout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    public MovieFragVP() {
    }

    public static MovieFragVP newInstance(String param1, String param2) {
        MovieFragVP fragment = new MovieFragVP();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initViewsAndEvents() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        switchFrameLayout();
    }

    private void switchFrameLayout() {
        switch (mParam1){
            case Const.PAGE_MAIN_IS_FIRST:
                FragmentHelper.showLayoutId(fragment_movie_framelayout,0);
                break;
            case Const.PAGE_MAIN_OTHER:
                FragmentHelper.showLayoutId(fragment_movie_framelayout,1);
                break;
        }
    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_movie_frag_v;
    }

}
