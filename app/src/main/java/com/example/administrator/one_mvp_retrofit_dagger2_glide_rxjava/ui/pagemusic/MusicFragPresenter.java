package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseSubscribe;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.GsonHelper;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemain.MainPageBean;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/3.
 */

public class MusicFragPresenter extends BasePresenter<IBaseView<MainPageBean>> {
    private DataManager dataManager;
    private Subscription subscription;
    private Context context;
    private IBaseView<MainPageBean>mMvpView;

    @Inject
    public MusicFragPresenter(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
    }

    @Override
    public void attachView(IBaseView<MainPageBean> mMvpView) {
        this.mMvpView = mMvpView;
    }

    /**获取音乐界面所有需要展示的数据 **/
    public void getMusicPageData(){
        subscription = dataManager.getMusicPageData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(context) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),MainPageBean.class));
                    }
                });
    }
}
