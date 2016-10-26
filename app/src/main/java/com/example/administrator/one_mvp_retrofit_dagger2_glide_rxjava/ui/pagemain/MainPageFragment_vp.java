package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemain;

import android.os.Bundle;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;

public class MainPageFragment_vp extends BaseFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static MainPageFragment_vp newInstance(String param1, String param2) {
        MainPageFragment_vp fragment = new MainPageFragment_vp();
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
    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main_page_fragment_vp;
    }

}
