package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerViewHolder;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.recycleritemanimator.AlphaAnimatorAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.recycleritemanimator.SlideScaleInOutRightItemAnimator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends BaseFragment implements SearchFragMvpView<SearchPictureBean> {

    @Inject
    SearchFragPresenter mPresenter;

    @Bind(R.id.search_fragment_framelayout)
    FrameLayout search_fragment_framelayout;
    @Bind(R.id.search_fragment_recyclerview)
    RecyclerView search_fragment_recyclerview;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<SearchBean>searchBeanList;
    private BaseRecyclerAdapter<SearchBean>adapter;
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
    }

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        initDataAndListener();
        mPresenter.goSearch(mParam1,mParam2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachViews();
    }

    private void initDataAndListener() {
        searchBeanList = new ArrayList<>();
        adapter = new BaseRecyclerAdapter<SearchBean>(getActivity(),searchBeanList) {
            @Override
            public int getItemLayoutId(int viewType) {
                return getLayoutId();
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, SearchBean item) {
                switch (mParam1){
                    case Const.SEARCH_PICTURE:
                        Glide.with(getActivity()).load(((SearchPictureBean.DataBean)item).getHp_img_url()).dontAnimate().thumbnail(0.1f).placeholder(R.drawable.default_indi_bg).into(holder.getImageView(R.id.item_search_picture_image));
                        holder.getTextView(R.id.item_search_picture_title).setText(((SearchPictureBean.DataBean)item).getHp_title());
                        holder.getTextView(R.id.item_search_picture_content).setText(((SearchPictureBean.DataBean)item).getHp_content());
                        break;
                    case Const.SEARCH_READING:
                        switch (((SearchReadingBean.DataBean)item).getType()){
                            case Const.ESSAY:
                                holder.getImageView(R.id.item_search_reading_image).setImageResource(R.drawable.essay_image);
                                break;
                            case Const.SERIAL:
                                holder.getImageView(R.id.item_search_reading_image).setImageResource(R.drawable.serial_image);
                                break;
                            case Const.QUESTION:
                                holder.getImageView(R.id.item_search_reading_image).setImageResource(R.drawable.question_image);
                                break;
                        }
                        holder.getTextView(R.id.item_search_reading_title).setText(((SearchReadingBean.DataBean)item).getTitle());
                        break;
                    case Const.SEARCH_MUSIC:
                        Glide.with(getActivity()).load(((SearchMusicBean.DataBean)item).getCover()).dontAnimate().thumbnail(0.1f).placeholder(R.drawable.default_indi_bg).into(holder.getImageView(R.id.item_search_music_image));
                        holder.getTextView(R.id.item_search_music_title).setText(((SearchMusicBean.DataBean)item).getTitle());
                        holder.getTextView(R.id.item_search_music_name).setText(((SearchMusicBean.DataBean)item).getAuthor().getUser_name());
                        break;
                    case Const.SEARCH_MOVIE:
                        holder.getTextView(R.id.item_search_movie_title).setText(((SearchMovieBean.DataBean)item).getTitle());
                        break;
                    case Const.SEARCH_AUTHOR:
                        Glide.with(getActivity()).load(((SearchAuthorBean.DataBean)item).getWeb_url()).dontAnimate().thumbnail(0.1f).placeholder(R.drawable.default_indi_bg).into(holder.getImageView(R.id.item_search_author_image));
                        holder.getTextView(R.id.item_search_author_title).setText(((SearchAuthorBean.DataBean)item).getUser_name());
                        holder.getTextView(R.id.item_search_author_content).setText(((SearchAuthorBean.DataBean)item).getDesc());
                        break;
                }
            }
        };
        search_fragment_recyclerview.setItemAnimator(new SlideScaleInOutRightItemAnimator(search_fragment_recyclerview));
        search_fragment_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        search_fragment_recyclerview.setHasFixedSize(true);
        AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(adapter,search_fragment_recyclerview);
        search_fragment_recyclerview.setAdapter(animatorAdapter);
    }


    @Override
    protected void iniInjector() {
        ((BaseActivity)getActivity()).activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_search;
    }

    public int getLayoutId() {
        int layoutId = 0;
        switch (mParam1){
            case Const.SEARCH_PICTURE:
                layoutId = R.layout.item_search_picture;
                break;
            case Const.SEARCH_READING:
                layoutId = R.layout.item_search_reading;
                break;
            case Const.SEARCH_MUSIC:
                layoutId = R.layout.item_search_music;
                break;
            case Const.SEARCH_MOVIE:
                layoutId = R.layout.item_search_movie;
                break;
            case Const.SEARCH_AUTHOR:
                layoutId = R.layout.item_search_author;
                break;
        }
        return layoutId;
    }

    public void loadNetWork(String content){
        mPresenter.goSearch(mParam1,content);
    }

    public void notifycation(){
        if (searchBeanList.size() == 0){
            FragmentHelper.showLayoutId(search_fragment_framelayout,1);
        }else {
            FragmentHelper.showLayoutId(search_fragment_framelayout,0);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showData(SearchReadingBean searchReadingBean) {
        searchBeanList.clear();
        if (searchReadingBean != null){
            searchBeanList.addAll(searchReadingBean.getData());
        }
        notifycation();
    }

    @Override
    public void showData(SearchMusicBean searchMusicBean) {
        searchBeanList.clear();
        if (searchMusicBean != null){
            searchBeanList.addAll(searchMusicBean.getData());
        }
        notifycation();
    }

    @Override
    public void showData(SearchMovieBean searchMovieBean) {
        searchBeanList.clear();
        if (searchMovieBean != null){
            searchBeanList.addAll(searchMovieBean.getData());
        }
        notifycation();
    }

    @Override
    public void showData(SearchAuthorBean searchAuthorBean) {
        searchBeanList.clear();
        TLog.getInstance().i("作者："+searchAuthorBean.getData().size());
        if (searchAuthorBean != null){
            searchBeanList.addAll(searchAuthorBean.getData());
        }
        notifycation();
    }

    @Override
    public void showData(SearchPictureBean data) {
        searchBeanList.clear();
        if (data != null){
            searchBeanList.addAll(data.getData());
        }
        notifycation();
    }
}
