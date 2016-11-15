package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Administrator on 2016/11/15.
 */

public class MovieDetailPresenter extends BasePresenter<MovieDetailMvpView<MovieDetailDataBean>> {
    private DataManager dataManager;
    private Context context;
    private MovieDetailMvpView<MovieDetailDataBean> mMvpView;
    private Subscription subscription;

    @Inject
    public MovieDetailPresenter(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
    }

    @Override
    public void attachView(MovieDetailMvpView<MovieDetailDataBean> mMvpView) {
        this.mMvpView = mMvpView;
    }

    @Override
    public void detachViews() {
        super.detachViews();
        if (subscription != null){
            subscription.unsubscribe();
            subscription = null;
        }
        mMvpView = null;
    }
}
