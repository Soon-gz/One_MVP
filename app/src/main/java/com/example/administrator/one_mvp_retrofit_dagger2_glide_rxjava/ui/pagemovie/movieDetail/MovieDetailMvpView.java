package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PostResultBean;

/**
 * Created by Administrator on 2016/11/15.
 */

public interface MovieDetailMvpView<MovieDetailDataBean> extends IBaseView<MovieDetailDataBean>{
    void showData(MovieDetailStoryBean movieDetailStoryBean);
    void showData(MovieDetailContentBean movieDetailContentBean);
    void showData(MovieDetailScoreBean movieDetailScoreBean);
    void praise(PostResultBean resultBean);
}
