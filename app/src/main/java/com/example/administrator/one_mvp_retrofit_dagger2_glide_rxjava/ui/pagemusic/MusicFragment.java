package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.issue.MainIssueActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.BaseFgAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemain.MainPageBean;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemain.MainPageFragment_vp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends BaseFragment implements IBaseView<MainPageBean>, ViewPager.OnPageChangeListener {

    @Inject
    MusicFragPresenter mPresenter;

    @Bind(R.id.music_page_viewpager)
    ViewPager music_page_viewpager;

    private List<BaseFragment>musicFragVPs;
    private BaseFgAdapter baseFgAdapter;

    @Override
    protected void initViewsAndEvents() {
        musicFragVPs = new ArrayList<>();
        baseFgAdapter = new BaseFgAdapter(getChildFragmentManager(),musicFragVPs);
        music_page_viewpager.setAdapter(baseFgAdapter);
        music_page_viewpager.addOnPageChangeListener(this);
        mPresenter.getMusicPageData();
    }

    @Override
    protected void iniInjector() {
        ((BaseActivity)getActivity()).activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_music;
    }


    @Override
    public void showData(MainPageBean data) {
        for (int index = -1; index < data.getData().size()+1; index++) {
            if (index == -1){
                musicFragVPs.add(MusicFragVP.newInstance(Const.PAGE_MAIN_IS_FIRST,""));
            }else if (index == data.getData().size()){
                musicFragVPs.add(MusicFragVP.newInstance(Const.PAGE_MAIN_IS_LAST,""));
            }else {
                musicFragVPs.add(MusicFragVP.newInstance(Const.PAGE_MAIN_OTHER,data.getData().get(index)));
            }
        }
        notifyDataChangedViewPager();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //设置左滑刷新，右滑更多
        if (position == musicFragVPs.size()-1) {
            music_page_viewpager.setCurrentItem(musicFragVPs.size()-2);
            Intent intent = new Intent(getActivity(), MainIssueActivity.class);
            startActivity(intent);
        } else if (position == 0) {
            music_page_viewpager.setCurrentItem(1);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void notifyDataChangedViewPager(){
        baseFgAdapter.notifyDataSetChanged();
        music_page_viewpager.setCurrentItem(1);
    }
}
