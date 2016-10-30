package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.BaseFgAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @Bind(R.id.reading_page_viewPager)
    ViewPager reading_page_viewPager;


    private BaseFgAdapter baseFgAdapter;
    private List<BaseFragment> dataList;

    @Override
    protected void initViewsAndEvents() {
        initData();
    }

    private void initData() {
        dataList = new ArrayList<>();
        dataList.add(ReadingFragment_vp.newInstance(Const.PAGE_MAIN_IS_FIRST,""));
        dataList.add(ReadingFragment_vp.newInstance(Const.PAGE_MAIN_OTHER,""));
        dataList.add(ReadingFragment_vp.newInstance(Const.PAGE_MAIN_IS_LAST,""));
        baseFgAdapter = new BaseFgAdapter(getFragmentManager(),dataList);
        reading_page_viewPager.setAdapter(baseFgAdapter);
        reading_page_viewPager.addOnPageChangeListener(this);
        reading_page_viewPager.setCurrentItem(1);
    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_reading;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0){
            reading_page_viewPager.setCurrentItem(1);
            ToastUtils.showToast("刷新数据");
        }else if (position == 2){
            reading_page_viewPager.setCurrentItem(1);
            ToastUtils.showToast("往期数据");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
