package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseSubscribe;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.GsonHelper;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/3.
 */

public class MusicFragVPresenter extends BasePresenter<MusicFragVPMvpView<MusicFragVPDataBean>> {
    private DataManager dataManager;
    private Context context;
    private Subscription subscription;
    private MusicFragVPMvpView<MusicFragVPDataBean> mMvpView;

    @Inject
    public MusicFragVPresenter(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
    }

    @Override
    public void attachView(MusicFragVPMvpView<MusicFragVPDataBean> mMvpView) {
        this.mMvpView = mMvpView;
    }

    /**
     * 获取音乐界面详细数据
     * @param musicId
     */
    public void getMusicDetail(String musicId){
        subscription = dataManager.getMusicDetailData(musicId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(context,false) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),MusicFragVPDataBean.class));
                    }
                });
    }
}
