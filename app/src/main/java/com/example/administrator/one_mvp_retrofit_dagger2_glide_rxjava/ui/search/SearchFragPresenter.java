package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.search;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseSubscribe;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.GsonHelper;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/8.
 */

public class SearchFragPresenter extends BasePresenter<SearchFragMvpView<SearchPictureBean>>{
    private DataManager dataManager;
    private Context context;
    private Subscription subscription;
    private SearchFragMvpView<SearchPictureBean>mMvpView;

    @Inject
    public SearchFragPresenter(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
    }

    @Override
    public void attachView(SearchFragMvpView<SearchPictureBean> mMvpView) {
        this.mMvpView = mMvpView;
    }

    @Override
    public void detachViews() {
        super.detachViews();
        if (subscription != null){
            subscription.unsubscribe();
        }
        subscription = null;
        mMvpView = null;
    }

    /**
     * 搜索界面
     * @param type
     * @param content
     */
    public void goSearch(final String type, String content){
        subscription = dataManager.getSearch(type,content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(context,false) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        switch (type){
                            case Const.SEARCH_PICTURE:
                                mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),SearchPictureBean.class));
                                break;
                            case Const.SEARCH_READING:
                                mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),SearchReadingBean.class));
                                break;
                            case Const.SEARCH_MUSIC:
                                mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),SearchMusicBean.class));
                                break;
                            case Const.SEARCH_MOVIE:
                                mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),SearchMovieBean.class));
                                break;
                            case Const.SEARCH_AUTHOR:
                                mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),SearchAuthorBean.class));
                                break;
                        }
                    }
                });
    }
}
