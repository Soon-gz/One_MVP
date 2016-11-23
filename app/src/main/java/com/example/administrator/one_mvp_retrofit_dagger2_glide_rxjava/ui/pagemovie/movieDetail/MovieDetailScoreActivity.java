package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Block;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.CusInstrumentPanelView;

import butterknife.Bind;

public class MovieDetailScoreActivity extends BaseActivity implements CusInstrumentPanelView.CurrentScore {

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

    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        setFinishOnTouchOutside(true);
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
            TLog.getInstance().i("Float.parseFloat(score):"+Float.parseFloat(score)/100);
            instrumentPanelView.pointerTo(Float.parseFloat(score)/100);
        }
        typeface = Typeface.createFromAsset(getAssets(), "Ruthie.ttf");
        movie_detail_score.setTypeface(typeface);
    }

    @Override
    public void getScore(String s) {
        movie_detail_score.setText(s);
    }
}
