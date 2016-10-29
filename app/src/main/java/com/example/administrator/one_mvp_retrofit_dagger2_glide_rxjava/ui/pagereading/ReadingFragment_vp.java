package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PinnedHeaderListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadingFragment_vp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadingFragment_vp extends BaseFragment {

    @Bind(R.id.reading_page_framelayout)
    FrameLayout reading_page_framelayout;
    @Bind(R.id.reading_page_ListView)
    PinnedHeaderListView reading_page_ListView;
    @Bind(R.id.layout_dots)
    LinearLayout linearLayout;
    @Bind(R.id.viewpager_reading_head)
    ViewPager viewpager_reading_head;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ImageView[]dots;
    private List<ImageView> list_img;


    public ReadingFragment_vp() {
    }

    public static ReadingFragment_vp newInstance(String param1, String param2) {
        ReadingFragment_vp fragment = new ReadingFragment_vp();
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
        initLayoutId(mParam1);
        initListenerAndData();
    }

    private void initListenerAndData() {
        View Header = LayoutInflater.from(getActivity()).inflate(R.layout.item_reading_viewpager, null);
        //初始化轮播图小点
        initDots();
    }

    private void initDots() {
        dots = new ImageView[9];
        list_img = new ArrayList<>();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) linearLayout.getChildAt(i);
            imageView.setEnabled(true);
            dots[i] = imageView;
        }
        dots[0].setEnabled(false);
    }

    private void initLayoutId(String mParam1) {
        switch (mParam1){
            case Const.PAGE_MAIN_IS_FIRST:
                showLayoutId(0);
                break;
            case Const.PAGE_MAIN_OTHER:
                showLayoutId(1);
                break;
            case Const.PAGE_MAIN_IS_LAST:
                showLayoutId(2);
                break;
        }
    }

    public void showLayoutId(int layputId){
        for (int index = 0; index < reading_page_framelayout.getChildCount(); index++) {
            if (index == layputId){
                reading_page_framelayout.getChildAt(index).setVisibility(View.VISIBLE);
            }else {
                reading_page_framelayout.getChildAt(index).setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_reading_fragment_vp;
    }

}
