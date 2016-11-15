package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;

/**
 * Created by Administrator on 2016/11/15.
 */

public interface MovieDetailMvpView<MovieDetailDataBean> extends IBaseView<MovieDetailDataBean>{
    void showData(MovieDatailStoryBean movieDatailStoryBean);
    void showData(MovieDetailContentBean movieDetailContentBean);
}
