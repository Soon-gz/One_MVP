package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.CustomAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PinnedHeaderListView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.RFVPAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadingFragment_vp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadingFragment_vp extends BaseFragment implements ReadingFVPMvpView, AbsListView.OnScrollListener, View.OnTouchListener, ViewPager.OnPageChangeListener {

    @Inject
    ReadingFVPPresenter mPresenter;
    @Bind(R.id.reading_page_framelayout)
    FrameLayout reading_page_framelayout;
    @Bind(R.id.reading_page_ListView)
    PinnedHeaderListView reading_page_ListView;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private ImageView[]dots;
    private List<ImageView> list_img;
    private RFVPAdapter rfvpAdapter;
    private List<ItemEntity> list_item;
    private CustomAdapter customAdapter;
    private LinearLayout linearLayout;
    private ViewPager viewpager_reading_head;
    private boolean isBottom = false;
    private String page = "0";
    private int currentPage = 0 ;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    currentPage++;
                    if (currentPage>8){
                        currentPage = 0;
                    }
                    viewpager_reading_head.setCurrentItem(currentPage);
                    break;
            }
        }
    };


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
    }

    private void loadNetData(String page,boolean showProgress) {
        mPresenter.getReadingData(page,showProgress);
    }

    private void loadImgs(){
        mPresenter.getReadingHeadImgs();
    }

    /**
     * 初始化数据和控件及其相关逻辑
     */
    private void initListenerAndData() {
        View Header = LayoutInflater.from(getActivity()).inflate(R.layout.item_reading_viewpager, null);
        View HeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.item_pinedlv_header, reading_page_ListView, false);
        initDots(Header);
        reading_page_ListView.addHeaderView(Header);
        reading_page_ListView.setPinnedHeader(HeaderView);
        list_item = new ArrayList<>();
        list_img = new ArrayList<>();

        customAdapter = new CustomAdapter(getActivity().getApplication(), list_item);
        rfvpAdapter = new RFVPAdapter(list_img);
        reading_page_ListView.setAdapter(customAdapter);
        viewpager_reading_head.setAdapter(rfvpAdapter);
        //设置滑动监听事件
        reading_page_ListView.setOnScrollListener(this);
        //防止冲突
        viewpager_reading_head.setOnTouchListener(this);
        //设置viewpager监听事件
        viewpager_reading_head.addOnPageChangeListener(this);
    }

    private void initDots(View view) {
        linearLayout = (LinearLayout) view.findViewById(R.id.layout_dots);
        viewpager_reading_head = (ViewPager) view.findViewById(R.id.viewpager_reading_head);
        dots = new ImageView[9];
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
                initListenerAndData();
                loadNetData(page,true);
                loadImgs();
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
        ((BaseActivity)getActivity()).activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_reading_fragment_vp;
    }

    @Override
    public void showHeadImags(ReadingFVPImagsBean imagsBean) {
        for (int index = 0; index < imagsBean.getData().size(); index++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(getActivity()).load(imagsBean.getData().get(index).getCover()).thumbnail(0.1f).dontAnimate().into(imageView);
            list_img.add(imageView);
        }
        //轮播图每3秒一转
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 0, 3000);
        rfvpAdapter.notifyDataSetChanged();
    }

    @Override
    public void showData(List<ItemEntity> list) {
        list_item.addAll(list);
        customAdapter.notifyDataSetChanged();
    }

    @Override
    public void showData(Object data) {
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        //这里可以做简单的分页加载
        if ("0".equals(page) && isBottom && i == SCROLL_STATE_IDLE){
            page = "1";
            loadNetData(page,false);
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        isBottom = (i+i1 == i2);
        if ( absListView instanceof PinnedHeaderListView) {
            ((PinnedHeaderListView) absListView).controlPinnedHeader(i);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        viewpager_reading_head.getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
        for (int i = 0; i < 9; i++) {
            dots[i].setEnabled(true);
        }
        dots[position].setEnabled(false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachViews();
    }
}
