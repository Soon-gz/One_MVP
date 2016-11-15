package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerViewHolder;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class MovieDetailActivity extends BaseActivity {

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

    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        initViewsAndListener();
    }

    private void initViewsAndListener() {
        dataList = new ArrayList<>();
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

            }
        };
    }

    @OnClick(R.id.movie_detail_return)
    public void movieDetailOnClick(){
        finish();
    }
}
