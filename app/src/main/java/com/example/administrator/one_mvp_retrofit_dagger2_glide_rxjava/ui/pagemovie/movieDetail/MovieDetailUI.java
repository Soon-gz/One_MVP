package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerViewHolder;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.StringUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.Comment.CommentActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/11/16.
 * 为MovieDetail UI逻辑服务
 */

public class MovieDetailUI {

    /**
     * 初始化RadioGroup,切换图片和电影基本信息
     * @param radioGroup
     * @param frameLayout
     */
    public static void initRadioGroup(RadioGroup radioGroup, final FrameLayout frameLayout){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.item_movie_detail_grossbtn:
                        FragmentHelper.showLayoutId(frameLayout,0);
                        break;
                    case R.id.item_movie_detail_stillbtn:
                        FragmentHelper.showLayoutId(frameLayout,1);
                        break;
                    case R.id.item_movie_detail_plotbtn:
                        FragmentHelper.showLayoutId(frameLayout,2);
                        break;
                }
            }
        });
    }

    /**
     * 初始化图片的滚动条
     * @param horizontalScrollView
     * @param movieDetailDataBean
     * @param context
     */
    public static void initHorizontalScollView(HorizontalScrollView horizontalScrollView, MovieDetailDataBean movieDetailDataBean, Context context){
        if (movieDetailDataBean.getData().getPhoto().size() > 0){
            horizontalScrollView.removeAllViews();
            LinearLayout linearLayout = new LinearLayout(context);
            for (int index = 0; index < movieDetailDataBean.getData().getPhoto().size(); index++) {
                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setPadding(10,10,10,10);
                TextView textView = new TextView(context);
                textView.setLayoutParams(new ViewGroup.LayoutParams(10, ViewGroup.LayoutParams.MATCH_PARENT));
                textView.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
                Glide.with(context).load(movieDetailDataBean.getData().getPhoto().get(index)).placeholder(R.drawable.default_indi_bg).dontAnimate().thumbnail(0.1f).into(imageView);
                linearLayout.addView(imageView);
                linearLayout.addView(textView);
            }
            horizontalScrollView.addView(linearLayout);
        }
    }

    /**
     * 初始化故事点赞的checkBox
     * @param holder
     * @param mPresenter
     * @param item
     * @param movieId
     * @param checkBoxId
     * @param textId
     * @param storyId
     */
    public static void initCheckBox(final BaseRecyclerViewHolder holder, final MovieDetailPresenter mPresenter, final MovieDetailContentBean.DataBean0.DataBean item, final String movieId, int checkBoxId, final int textId, final String storyId){
        holder.getCheckBox(checkBoxId).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    holder.getTextView(textId).setText((Integer.parseInt(holder.getTextView(textId).getText().toString()) + 1)+"");
                    mPresenter.postMovieStoryPraise(storyId,movieId);
                }else {
                    holder.getTextView(textId).setText((Integer.parseInt(holder.getTextView(textId).getText().toString()) - 1)+"");
                    mPresenter.postMovieStoryUnPraise(storyId,movieId);
                }
            }
        });
    }

    /**
     * 初始化评论点赞的checkBox
     * @param holder
     * @param mPresenter
     * @param item
     * @param movieId
     * @param checkBoxId
     * @param textId
     */
    public static void initCheckBox(final BaseRecyclerViewHolder holder, final MovieDetailPresenter mPresenter, final MovieDetailContentBean.DataBean0.DataBean item, final String movieId, int checkBoxId, final int textId){
        holder.getCheckBox(checkBoxId).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    holder.getTextView(textId).setText((Integer.parseInt(holder.getTextView(textId).getText().toString()) + 1)+"");
                    mPresenter.postMusicLike(movieId,Const.MOVIE,item.getId());
                }else {
                    holder.getTextView(textId).setText((Integer.parseInt(holder.getTextView(textId).getText().toString()) - 1)+"");
                    mPresenter.postMusicUnPraise(movieId,Const.MOVIE,item.getId());
                }
            }
        });
    }

    /**
     * 初始化评论回复
     * @param item
     * @param holder
     */
    public static void initToUser(final MovieDetailContentBean.DataBean0.DataBean item,final BaseRecyclerViewHolder holder){
        if (item.getTouser() != null){
            holder.getLinearLayout(R.id.linearLayout_movie_detail_content).setVisibility(View.VISIBLE);
            holder.getTextView(R.id.text_movie_detail_content_tousername).setText(item.getTouser().getUser_name()+"：");
            holder.getTextView(R.id.text_movie_detail_content_quote).setText(item.getQuote());
        }else {
            holder.getLinearLayout(R.id.linearLayout_movie_detail_content).setVisibility(View.GONE);
        }
    }

    /**
     * 初始化热门评论
     * @param item
     * @param holder
     * @param dataList
     * @param position
     */
    public static void initHotComment(MovieDetailContentBean.DataBean0.DataBean item, BaseRecyclerViewHolder holder, List<MovieDetailContentBean.DataBean0.DataBean> dataList, int position) {
        if (Const.NOT_HOT_CONTENT.equals(item.getType()+"") && Const.HOT_CONTENT.equals(dataList.get(position - 1).getType()+"")){
            holder.getRelativeLayout(R.id.item_movie_detail_content_hot).setVisibility(View.VISIBLE);
        }else {
            holder.getRelativeLayout(R.id.item_movie_detail_content_hot).setVisibility(View.GONE);
        }
    }

    /**
     * recyclerview多样式布局
     * @param viewType
     * @return
     */
    public static int initLayoutId(int viewType){
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

    /**
     * 初始化图片点击事件
     * @param imageView
     * @param context
     */
    public static void initImageOnCLick(ImageView imageView, final Context context, final String videoUrl, final String videoTitle, final String innerCover) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MovieDetailVideoActivity.class);
                intent.putExtra(Const.MOVIE_VIDEO_URL,videoUrl);
                intent.putExtra(Const.MOVIE_VIDEO_TITLE,videoTitle);
                intent.putExtra(Const.MOVIE_VIDEO_INNER_COVER,innerCover);
                context.startActivity(intent);
            }
        });
    }

    /**
     * 电影票链接
     * @param imageButton
     */
    public static void initImageBtnOnClick(ImageButton imageButton, final WebView webView, final String movieId) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://v3.wufazhuce.com:8000/api/movie/buyticket/"+movieId+"?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE0NzkyMDE0NDcsInVzZXJpZCI6IjY0MDcyNzMifQ.nuhymkpKMR2q71Je7H2BgCwXOmaVF0Qy_Vh5peAYHL4");
            }
        });
    }

    /**
     * 初始化评论提交
     * @param imageButton
     * @param movieDetailActivity
     * @param id
     */
    public static void initCommentOnClick(ImageButton imageButton, final MovieDetailActivity movieDetailActivity, final String id) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(movieDetailActivity, CommentActivity.class);
                intent.putExtra(Const.ITEM_ID,id);
                intent.putExtra(Const.COMMETNT_TYPE,Const.MOVIE);
                movieDetailActivity.startActivityForResult(intent,Const.COMMENT_REQUEST_CODE);
            }
        });
    }

    public static void initScoreButton(CircleImageView imageButton, RelativeLayout relativeLayout, final MovieDetailActivity movieDetailActivity, final MovieDetailScoreBean movieDetailScoreBean, TextView textView, TextView item_movie_detail_grade_number) {
        if (null == movieDetailScoreBean.getData().getScore()){
            item_movie_detail_grade_number.setText("");
            imageButton.setImageResource(0);
            textView.setText(R.string.movie_grade);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieDetailActivity.startActivity(new Intent(movieDetailActivity,MovieDetailScoreActivity.class).putExtra(Const.MOVIE_SCORE_TYPE,Const.NO_SCORE));
                }
            });
        }else {
            item_movie_detail_grade_number.setText(movieDetailScoreBean.getData().getScore());
            textView.setText(R.string.your_movie_grade);
            imageButton.setImageResource(getColorByScore(Integer.parseInt(movieDetailScoreBean.getData().getScore())));
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieDetailActivity.startActivity(new Intent(movieDetailActivity,MovieDetailScoreActivity.class).putExtra(Const.MOVIE_SCORE_TYPE,movieDetailScoreBean.getData().getScore()));
                }
            });
        }
    }

    private static int getColorByScore(int i) {
        int color = 0;
        if (i < 0) {
            color = R.color.negative_score;
        }else if (i == 0){
            color = R.color.zero_score;
        }else if (i < 10){
            color = R.color.ten_score;
        }else if (i < 20){
            color = R.color.twenty_score;
        }else if (i < 30){
            color = R.color.thirty_score;
        }else if (i < 40){
            color = R.color.fourty_score;
        }else if (i < 50){
            color = R.color.fifty_score;
        }else if (i < 60){
            color = R.color.sixty_score;
        }else if (i < 70){
            color = R.color.seventy_score;
        }else if (i < 80){
            color = R.color.eighty_score;
        }else if (i < 90){
            color = R.color.ninety_score;
        }else if (i <= 100){
            color = R.color.full_score;
        }
        return color;
    }
}
