package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.Comment.CommentActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Block;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.CusInstrumentPanelView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PostResultBean;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.http.Field;

public class MovieDetailScoreActivity extends BaseActivity implements CusInstrumentPanelView.CurrentScore,IBaseView<PostResultBean> {

    @Inject
    MovieDetailScorePresenter mPresenter;

    @Bind(R.id.info)
    CusInstrumentPanelView instrumentPanelView;
    @Bind(R.id.movie_detail_score)
    TextView movie_detail_score;
    private Typeface typeface;
    private String score;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_movie_deatil_score;
    }

    @Override
    protected void iniInjector() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        score = getIntent().getStringExtra(Const.MOVIE_SCORE_TYPE);
        instrumentPanelView.addBlock(new Block(0xff7eca8e,0.1f));
        instrumentPanelView.addBlock(new Block(0xff7dd480, 0.2f));
        instrumentPanelView.addBlock(new Block(0xff89e082, 0.3f));
        instrumentPanelView.addBlock(new Block(0xff9eec72, 0.4f));
        instrumentPanelView.addBlock(new Block(0xffc2ec52, 0.5f));
        instrumentPanelView.addBlock(new Block(0xffe5ec4c, 0.6f));
        instrumentPanelView.addBlock(new Block(0xffffdb7b, 0.7f));
        instrumentPanelView.addBlock(new Block(0xffffc279, 0.8f));
        instrumentPanelView.addBlock(new Block(0xffff8a6c, 0.9f));
        instrumentPanelView.addBlock(new Block(0xffff7883, 1f));
        instrumentPanelView.setCurrentScore(this);
        if (!Const.NO_SCORE.equals(score)){
            instrumentPanelView.pointerTo(Float.parseFloat(score)/100);
        }
        typeface = Typeface.createFromAsset(getAssets(), "Ruthie.ttf");
        movie_detail_score.setTypeface(typeface);
        movie_detail_score.setText("0");
    }

    @Override
    public void getScore(String s) {
        movie_detail_score.setText(s);
    }

    @OnClick({R.id.score_complete,R.id.score_comment})
    public void scoreOnClick(View view){
        switch (view.getId()){
            case R.id.score_complete:
                if (Const.NO_SCORE.equals(score)){
                    mPresenter.postMyGrade(getIntent().getStringExtra(Const.ITEM_ID),movie_detail_score.getText().toString().contains("-")?"-1":movie_detail_score.getText().toString());
                }else {
                    finish();
                }
                break;
            case R.id.score_comment:
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra(Const.ITEM_ID,getIntent().getStringExtra(Const.ITEM_ID));
                intent.putExtra(Const.COMMETNT_TYPE,Const.MOVIE);
                startActivityForResult(intent,Const.COMMENT_REQUEST_CODE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Const.COMMENT_REQUEST_CODE && resultCode == Const.COMMENT_RESULT_CODE){
            Intent intent = getIntent();
            setResult(Const.SCORE_COMMENT_RESULT_CODE,intent);
            finish();
        }
    }

    @Override
    public void showData(PostResultBean data) {
        if ("success".equals(data.getMsg())){
            ToastUtils.showToast("恭喜，提交分数成功");
            Intent intent = getIntent();
            intent.putExtra(Const.SCORE,movie_detail_score.getText().toString());
            setResult(Const.SCORE_RESULT_CODE,intent);
            finish();
        }else if (data.getMsg() != null){
            ToastUtils.showToast(data.getMsg());
            finish();
        }else {
            ToastUtils.showToast("分数提交失败");
            finish();
        }
    }
}
