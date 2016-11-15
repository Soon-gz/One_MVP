package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerViewHolder;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.recycleritemanimator.AlphaAnimatorAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.recycleritemanimator.SlideScaleInOutRightItemAnimator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class MovieDetailActivity extends BaseActivity implements MovieDetailMvpView<MovieDetailDataBean>{

    @Inject
    MovieDetailPresenter mPresenter;
    @Bind(R.id.movie_detail_recyclerview)
    RecyclerView movie_detail_recyclerview;

    private MovieDetailAdapter<MovieDetailContentBean>adapter;
    private List<MovieDetailContentBean>dataList;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_movie_detail;
    }

    @Override
    protected void iniInjector() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        initViewsAndListener();
    }

    private void initViewsAndListener() {
        dataList = new ArrayList<>();
        dataList.add(new MovieDetailContentBean());
        adapter = new MovieDetailAdapter<MovieDetailContentBean>(this,dataList) {
            @Override
            public int getItemLayoutId(int viewType) {
                int layoutId = 0;
                switch (viewType){
                    case Const.MOVIE_HEAD_ITEM:
                        layoutId = R.layout.item_movie_detail_first;
                        break;
                    case Const.Movie_CONTENT_ITEM:
                        layoutId = R.layout.item_movie_detail_content;
                        break;
                }
                return layoutId;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, MovieDetailContentBean item) {
                switch (position){
                    case 0:
                        break;
                    default:
                        break;
                }
            }
        };
        movie_detail_recyclerview.setHasFixedSize(true);
        movie_detail_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        movie_detail_recyclerview.setItemAnimator(new SlideScaleInOutRightItemAnimator(movie_detail_recyclerview));
        AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(adapter,movie_detail_recyclerview);
        movie_detail_recyclerview.setAdapter(animatorAdapter);

    }

    @OnClick(R.id.movie_detail_return)
    public void movieDetailOnClick(){
        finish();
    }

    @Override
    public void showData(MovieDatailStoryBean movieDatailStoryBean) {

    }

    @Override
    public void showData(MovieDetailContentBean movieDetailContentBean) {

    }

    @Override
    public void showData(MovieDetailDataBean data) {

    }
}
