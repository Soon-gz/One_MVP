package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseSubscribe;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.GsonHelper;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PostResultBean;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/23.
 */

public class MovieDetailScorePresenter extends BasePresenter<IBaseView<PostResultBean>>{
    private DataManager dataManager;
    private Context context;
    private Subscription subscription;
    private IBaseView<PostResultBean>mMvpView;

    @Inject
    public MovieDetailScorePresenter(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
    }

    @Override
    public void detachViews() {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
        mMvpView = null;
    }

    @Override
    public void attachView(IBaseView<PostResultBean> mMvpView) {
        this.mMvpView = mMvpView;
    }

    /**
     * 提交我的分数
     * @param movieId
     * @param score
     */
    public void postMyGrade(String movieId,String score){
        subscription = dataManager.postMyGrade(score,movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscribe<JSONObject>(context,100) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),PostResultBean.class));
                    }
                });
    }
}
