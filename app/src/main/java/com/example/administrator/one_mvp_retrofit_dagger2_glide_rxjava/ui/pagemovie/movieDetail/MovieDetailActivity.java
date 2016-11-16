package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    private MovieDetailAdapter<MovieDetailContentBean.DataBean0.DataBean>adapter;
    private List<MovieDetailContentBean.DataBean0.DataBean>dataList;
    private MovieDetailDataBean movieDetailDataBean;
    private MovieDetailStoryBean movieDetailStoryBean;
    private AlphaAnimatorAdapter animatorAdapter;
    private Typeface typeface;
    private String commentId = "0";
    private boolean isBottom = false;

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
        dataList.add(new MovieDetailContentBean.DataBean0.DataBean());
        adapter = new MovieDetailAdapter<MovieDetailContentBean.DataBean0.DataBean>(this,dataList) {
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
            public void bindData(BaseRecyclerViewHolder holder, int position, MovieDetailContentBean.DataBean0.DataBean item) {
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
                        holder.getTextView(R.id.item_movie_detail_story_data).setText(movieDetailStoryBean.getData().getData().get(0).getContent().replaceAll("<strong>","").replaceAll("</strong>",""));
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
                        Glide.with(MovieDetailActivity.this).load(item.getUser().getWeb_url()).placeholder(R.drawable.head).dontAnimate().thumbnail(0.1f).into(holder.getCircleImageView(R.id.img_movie_detail_content_head));
                        holder.getTextView(R.id.text_movie_detail_content_username).setText(item.getUser().getUser_name());
                        holder.getTextView(R.id.text_movie_detail_content_time).setText(item.getCreated_at().substring(0,10));
                        holder.getTextView(R.id.text_movie_detail_content_laund).setText(item.getPraisenum()+"");
                        holder.getTextView(R.id.text_movie_detail_content_data).setText(item.getContent());
                        holder.getTextView(R.id.item_movie_detail_score_comment).setText(item.getScore());
                        holder.getTextView(R.id.item_movie_detail_score_comment).setTypeface(typeface);
                        if (Const.NOT_HOT_CONTENT.equals(item.getType()+"") && Const.HOT_CONTENT.equals(dataList.get(position - 1).getType()+"")){
                            holder.getRelativeLayout(R.id.item_movie_detail_content_hot).setVisibility(View.VISIBLE);
                        }else {
                            holder.getRelativeLayout(R.id.item_movie_detail_content_hot).setVisibility(View.GONE);
                        }
                        if (item.getTouser() != null){
                            holder.getLinearLayout(R.id.linearLayout_movie_detail_content).setVisibility(View.VISIBLE);
                            holder.getTextView(R.id.text_movie_detail_content_tousername).setText(item.getTouser().getUser_name()+"：");
                            holder.getTextView(R.id.text_movie_detail_content_quote).setText(item.getQuote());
                        }else {
                            holder.getLinearLayout(R.id.linearLayout_movie_detail_content).setVisibility(View.GONE);
                        }
                        break;
                }
            }
        };
        movie_detail_recyclerview.setHasFixedSize(true);
        movie_detail_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        movie_detail_recyclerview.setItemAnimator(new SlideScaleInOutRightItemAnimator(movie_detail_recyclerview));
        movie_detail_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isBottom && newState == RecyclerView.SCROLL_STATE_IDLE){
                    commentId = dataList.get(dataList.size() - 1).getId();
                    mPresenter.getMovieComment(getIntent().getStringExtra(Const.MOVIE_ID),commentId);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                isBottom = ((layoutManager.findLastVisibleItemPosition() + 1) == adapter.getItemCount());
            }
        });
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
            //发起网络请求，获取评论
            mPresenter.getMovieComment(getIntent().getStringExtra(Const.MOVIE_ID),commentId);
        }
    }

    @Override
    public void showData(MovieDetailContentBean movieDetailContentBean) {
        dataList.addAll(movieDetailContentBean.getData().getData());
        animatorAdapter.notifyDataSetChanged();
    }

    @Override
    public void showData(MovieDetailDataBean data) {
        movieDetailDataBean = data;
    }

    @Override
    public int getStatusColor() {
        return R.color.black;
    }
}
