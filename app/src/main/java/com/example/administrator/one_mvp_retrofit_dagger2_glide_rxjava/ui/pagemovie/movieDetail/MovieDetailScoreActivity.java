package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.os.Bundle;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Block;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.CusInstrumentPanelView;

import butterknife.Bind;

public class MovieDetailScoreActivity extends BaseActivity implements CusInstrumentPanelView.CurrentScore {

    @Bind(R.id.info)
    CusInstrumentPanelView instrumentPanelView;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_movie_deatil_score;
    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        instrumentPanelView.addBlock(new Block(0xFFDC143C,0.3f));
        instrumentPanelView.addBlock(new Block(0xFFCD4B1C, 0.6f));
        instrumentPanelView.addBlock(new Block(0xFFE3B33F, 0.8f));
        instrumentPanelView.addBlock(new Block(0xFF6B9E22, 1f));
        instrumentPanelView.setCurrentScore(this);
    }

    @Override
    public void getScore(String s) {
        TLog.getInstance().i("当前分数："+s);
    }
}
