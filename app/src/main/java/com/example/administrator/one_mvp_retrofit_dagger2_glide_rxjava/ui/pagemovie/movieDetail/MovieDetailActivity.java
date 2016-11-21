package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerViewHolder;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PostResultBean;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.recycleritemanimator.AlphaAnimatorAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.recycleritemanimator.SlideScaleInOutRightItemAnimator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class MovieDetailActivity extends BaseActivity implements MovieDetailMvpView<MovieDetailDataBean> {

    @Inject
    MovieDetailPresenter mPresenter;
    @Bind(R.id.movie_detail_recyclerview)
    RecyclerView movie_detail_recyclerview;
    @Bind(R.id.movie_detail_title)
    TextView movie_detail_title;
    @Bind(R.id.movie_detail_ticket)
    WebView webView;

    private MovieDetailAdapter<MovieDetailContentBean.DataBean0.DataBean> adapter;
    private List<MovieDetailContentBean.DataBean0.DataBean> dataList;
    private MovieDetailDataBean movieDetailDataBean;
    private MovieDetailStoryBean movieDetailStoryBean;
    private MovieDetailScoreBean movieDetailScoreBean;
    private AlphaAnimatorAdapter animatorAdapter;
    private Typeface typeface;
    private String commentId = "0";
    private boolean isBottom = false;
    private boolean isCallback = false;


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
        adapter = new MovieDetailAdapter<MovieDetailContentBean.DataBean0.DataBean>(this, dataList) {
            @Override
            public int getItemLayoutId(int viewType) {
                return MovieDetailUI.initLayoutId(viewType);
            }

            @Override
            public void bindData(final BaseRecyclerViewHolder holder, int position, final MovieDetailContentBean.DataBean0.DataBean item) {
                switch (position) {
                    case 0:
                        //初始化radioGoup点击出现相应的framelayout界面
                        movie_detail_title.setText(movieDetailDataBean.getData().getTitle());
                        //按钮点击事件，初始化
                        MovieDetailUI.initCommentOnClick(holder.getImageButton(R.id.item_movie_detail_review), MovieDetailActivity.this, movieDetailDataBean.getData().getId());
                        MovieDetailUI.initImageBtnOnClick(holder.getImageButton(R.id.item_movie_detail_ticket), webView, movieDetailDataBean.getData().getId());
                        MovieDetailUI.initImageOnCLick(holder.getImageView(R.id.item_movie_detail_cover), MovieDetailActivity.this, movieDetailDataBean.getData().getVideo(), movieDetailDataBean.getData().getTitle(), movieDetailDataBean.getData().getDetailcover());
                        MovieDetailUI.initRadioGroup(holder.getRadioGroup(R.id.item_movie_detail_radiogroup), holder.getFrameLayout(R.id.item_movie_detail_frameLayout));
                        MovieDetailUI.initScoreButton(holder.getImageButton(R.id.item_movie_detail_grade), MovieDetailActivity.this,movieDetailScoreBean,holder.getTextView(R.id.item_movie_detail_grade_text));
                        //加载图片
                        Glide.with(MovieDetailActivity.this).load(movieDetailDataBean.getData().getDetailcover()).placeholder(R.drawable.default_indi_bg).dontAnimate().thumbnail(0.1f).into(holder.getImageView(R.id.item_movie_detail_cover));
                        Glide.with(MovieDetailActivity.this).load(movieDetailStoryBean.getData().getData().get(0).getUser().getWeb_url()).placeholder(R.drawable.head).dontAnimate().thumbnail(0.1f).into(holder.getCircleImageView(R.id.item_movie_detail_story_headview));
                        //设置相关信息
                        holder.getTextView(R.id.item_movie_detail_content_username).setText(movieDetailStoryBean.getData().getData().get(0).getUser().getUser_name());
                        holder.getTextView(R.id.item_movie_detail_score).setText(movieDetailDataBean.getData().getScore());
                        holder.getTextView(R.id.item_movie_detail_score).setTypeface(typeface);
                        holder.getTextView(R.id.item_movie_detail_story_time).setText(movieDetailDataBean.getData().getLast_update_date().substring(0, 10));
                        holder.getTextView(R.id.item_movie_detail_story_laund).setText(movieDetailStoryBean.getData().getData().get(0).getPraisenum() + "");
                        holder.getTextView(R.id.item_movie_detail_story_data).setText(movieDetailStoryBean.getData().getData().get(0).getContent().replaceAll("<strong>", "").replaceAll("</strong>", ""));
                        //FrameLayout显示第一个
                        holder.getTextView(R.id.item_movie_detail_gross_0).setText(movieDetailDataBean.getData().getKeywords().split(";")[0]);
                        holder.getTextView(R.id.item_movie_detail_gross_1).setText(movieDetailDataBean.getData().getKeywords().split(";")[1]);
                        holder.getTextView(R.id.item_movie_detail_gross_2).setText(movieDetailDataBean.getData().getKeywords().split(";")[2]);
                        holder.getTextView(R.id.item_movie_detail_gross_3).setText(movieDetailDataBean.getData().getKeywords().split(";")[3]);
                        holder.getTextView(R.id.item_movie_detail_gross_4).setText(movieDetailDataBean.getData().getKeywords().split(";")[4]);
                        //FrameLayout显示第二个
                        MovieDetailUI.initHorizontalScollView(holder.getHorizontalScrollView(R.id.item_movie_detail_photo), movieDetailDataBean, MovieDetailActivity.this);
                        //FrameLayout显示第三个
                        holder.getTextView(R.id.item_movie_detail_plot_text).setText(movieDetailDataBean.getData().getInfo().replaceAll("\r\n", "\n"));
                        MovieDetailUI.initCheckBox(holder, mPresenter, item, getIntent().getStringExtra(Const.MOVIE_ID), R.id.movie_detail_checkbox_story, R.id.item_movie_detail_story_laund, movieDetailStoryBean.getData().getData().get(0).getId());
                        break;
                    default:
                        Glide.with(MovieDetailActivity.this).load(item.getUser().getWeb_url()).placeholder(R.drawable.head).dontAnimate().thumbnail(0.1f).into(holder.getCircleImageView(R.id.img_movie_detail_content_head));
                        holder.getTextView(R.id.text_movie_detail_content_username).setText(item.getUser().getUser_name());
                        holder.getTextView(R.id.text_movie_detail_content_time).setText(item.getCreated_at().substring(0, 10));
                        holder.getTextView(R.id.text_movie_detail_content_laund).setText(item.getPraisenum() + "");
                        holder.getTextView(R.id.text_movie_detail_content_data).setText(item.getContent());
                        holder.getTextView(R.id.item_movie_detail_score_comment).setText(item.getScore());
                        holder.getTextView(R.id.item_movie_detail_score_comment).setTypeface(typeface);
                        MovieDetailUI.initHotComment(item, holder, dataList, position);
                        MovieDetailUI.initToUser(item, holder);
                        MovieDetailUI.initCheckBox(holder, mPresenter, item, getIntent().getStringExtra(Const.MOVIE_ID), R.id.checkbox_content, R.id.text_movie_detail_content_laund);
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
                if (isBottom && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    commentId = dataList.get(dataList.size() - 1).getId();
                    mPresenter.getMovieComment(getIntent().getStringExtra(Const.MOVIE_ID), commentId);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                isBottom = ((layoutManager.findLastVisibleItemPosition() + 1) == adapter.getItemCount());
            }
        });
        animatorAdapter = new AlphaAnimatorAdapter(adapter, movie_detail_recyclerview);
        //发起网络请求
        mPresenter.getMovieDetailAndStory(getIntent().getStringExtra(Const.MOVIE_ID));
    }

    @OnClick(R.id.movie_detail_return)
    public void movieDetailOnClick() {
        finish();
    }

    @Override
    public void showData(MovieDetailStoryBean movieDetailStoryBean) {
        this.movieDetailStoryBean = movieDetailStoryBean;
        if (movieDetailDataBean != null) {
            movie_detail_recyclerview.setAdapter(animatorAdapter);
            //发起网络请求，获取评论
            mPresenter.getMovieComment(getIntent().getStringExtra(Const.MOVIE_ID), commentId);
        }
    }

    @Override
    public void showData(MovieDetailContentBean movieDetailContentBean) {
        dataList.addAll(movieDetailContentBean.getData().getData());
    }

    @Override
    public void showData(MovieDetailScoreBean movieDetailScoreBean) {
        TLog.getInstance().i("分数："+movieDetailScoreBean.getData().getScore());
        this.movieDetailScoreBean = movieDetailScoreBean;
        if (isCallback) {
            isCallback = false;
            movie_detail_recyclerview.scrollToPosition(7);
        }
        animatorAdapter.notifyDataSetChanged();
    }

    @Override
    public void praise(PostResultBean resultBean) {
        if (Const.POST_SUCCESS.equals(resultBean.getMsg())) {
            ToastUtils.showToast("操作成功");
        } else {
            ToastUtils.showToast("操作失败");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.COMMENT_REQUEST_CODE && resultCode == Const.COMMENT_RESULT_CODE) {
            isCallback = true;
            commentId = "0";
            dataList.clear();
            mPresenter.getMovieComment(getIntent().getStringExtra(Const.MOVIE_ID), commentId);
        }
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
