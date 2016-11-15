package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseSubscribe;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.GsonHelper;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/7.
 */

public class MovieFragVPresenter extends BasePresenter<IBaseView<MovieFragVPBean>> {
    private DataManager dataManager;
    private Context context;
    private Subscription subscription;
    private IBaseView<MovieFragVPBean>mMvpView;

    @Inject
    public MovieFragVPresenter(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
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

    @Override
    public void attachView(IBaseView mMvpView) {
        this.mMvpView = mMvpView;
    }

    /**
     * 获取电影界面展示数据
     * @param movieId
     * @param showProgress
     */
    public void getMovieList(String movieId,boolean showProgress){
        subscription = dataManager.getMovieList(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(context,showProgress) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),MovieFragVPBean.class));
                    }
                });
    }
}
