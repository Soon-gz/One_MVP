package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;

import javax.inject.Inject;

import butterknife.Bind;

public class MusicFragVP extends BaseFragment implements MusicFragVPMvpView<MusicFragVPDataBean> {

    @Inject
    MusicFragVPresenter mPresenter;

    @Bind(R.id.music_page_frame)
    FrameLayout music_page_frame;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public MusicFragVP() {
    }

    public static MusicFragVP newInstance(String param1, String param2) {
        MusicFragVP fragment = new MusicFragVP();
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
        switchLayoutId();
    }

    private void switchLayoutId() {
        switch (mParam1){
            case Const.PAGE_MAIN_IS_FIRST:
                FragmentHelper.showLayoutId(music_page_frame,0);
                break;
            case Const.PAGE_MAIN_OTHER:
                FragmentHelper.showLayoutId(music_page_frame,1);
                mPresenter.getMusicDetail(mParam2);
                break;
            case Const.PAGE_MAIN_IS_LAST:
                FragmentHelper.showLayoutId(music_page_frame,2);
                break;
        }
    }

    @Override
    protected void iniInjector() {
        ((BaseActivity)getActivity()).activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_music_frag_v;
    }

    @Override
    public void showData(MusicFragVPDataBean data) {

    }
}
