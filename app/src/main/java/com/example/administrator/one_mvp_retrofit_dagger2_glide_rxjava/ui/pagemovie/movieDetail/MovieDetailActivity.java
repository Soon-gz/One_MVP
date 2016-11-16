package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerViewHolder;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
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
    private MovieDetailDataBean movieDetailDataBean;
    private MovieDetailStoryBean movieDetailStoryBean;
    private AlphaAnimatorAdapter animatorAdapter;
    private Typeface typeface;

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
        typeface = Typeface.createFromAsset(getAssets(), "Ruthie.ttf");
        dataList.add(new MovieDetailContentBean());
        dataList.add(new MovieDetailContentBean());
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
                        //初始化radioGoup点击出现相应的framelayout界面
                        MovieDetailUI.initRadioGroup(holder.getRadioGroup(R.id.item_movie_detail_radiogroup),holder.getFrameLayout(R.id.item_movie_detail_frameLayout));
                        Glide.with(MovieDetailActivity.this).load(movieDetailDataBean.getData().getDetailcover()).placeholder(R.drawable.default_indi_bg).dontAnimate().thumbnail(0.1f).into(holder.getImageView(R.id.item_movie_detail_cover));
                        Glide.with(MovieDetailActivity.this).load(movieDetailStoryBean.getData().getData().get(0).getUser().getWeb_url()).placeholder(R.drawable.head).dontAnimate().thumbnail(0.1f).into(holder.getCircleImageView(R.id.item_movie_detail_story_headview));
                        holder.getTextView(R.id.item_movie_detail_content_username).setText(movieDetailStoryBean.getData().getData().get(0).getUser().getUser_name());
                        holder.getTextView(R.id.item_movie_detail_score).setText(movieDetailDataBean.getData().getScore());
                        holder.getTextView(R.id.item_movie_detail_score).setTypeface(typeface);
                        holder.getTextView(R.id.item_movie_detail_story_time).setText(movieDetailDataBean.getData().getLast_update_date().substring(0,10));
                        holder.getTextView(R.id.item_movie_detail_story_laund).setText(movieDetailStoryBean.getData().getData().get(0).getPraisenum()+"");
                        holder.getTextView(R.id.item_movie_detail_story_data).setText(movieDetailStoryBean.getData().getData().get(0).getContent());
                        //FrameLayout显示第一个
                        holder.getTextView(R.id.item_movie_detail_gross_0).setText(movieDetailDataBean.getData().getKeywords().split(";")[0]);
                        holder.getTextView(R.id.item_movie_detail_gross_1).setText(movieDetailDataBean.getData().getKeywords().split(";")[1]);
                        holder.getTextView(R.id.item_movie_detail_gross_2).setText(movieDetailDataBean.getData().getKeywords().split(";")[2]);
                        holder.getTextView(R.id.item_movie_detail_gross_3).setText(movieDetailDataBean.getData().getKeywords().split(";")[3]);
                        holder.getTextView(R.id.item_movie_detail_gross_4).setText(movieDetailDataBean.getData().getKeywords().split(";")[4]);
                        //FrameLayout显示第二个
                        MovieDetailUI.initHorizontalScollView(holder.getHorizontalScrollView(R.id.item_movie_detail_photo),movieDetailDataBean,MovieDetailActivity.this);
                        //FrameLayout显示第三个
                        holder.getTextView(R.id.item_movie_detail_plot_text).setText(movieDetailDataBean.getData().getInfo().replaceAll("\r\n","\n"));
                        break;
                    default:
                        break;
                }
            }
        };
        movie_detail_recyclerview.setHasFixedSize(true);
        movie_detail_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        movie_detail_recyclerview.setItemAnimator(new SlideScaleInOutRightItemAnimator(movie_detail_recyclerview));
        animatorAdapter = new AlphaAnimatorAdapter(adapter,movie_detail_recyclerview);
        //发起网络请求
        mPresenter.getMovieDetailAndStory(getIntent().getStringExtra(Const.MOVIE_ID));
    }

    @OnClick(R.id.movie_detail_return)
    public void movieDetailOnClick(){
        finish();
    }

    @Override
    public void showData(MovieDetailStoryBean movieDetailStoryBean) {
        this.movieDetailStoryBean =  movieDetailStoryBean;
        if (movieDetailDataBean != null){
            movie_detail_recyclerview.setAdapter(animatorAdapter);
        }
    }

    @Override
    public void showData(MovieDetailContentBean movieDetailContentBean) {

    }

    @Override
    public void showData(MovieDetailDataBean data) {
        movieDetailDataBean = data;
        if (movieDetailStoryBean != null){
            movie_detail_recyclerview.setAdapter(animatorAdapter);
        }
    }
}
