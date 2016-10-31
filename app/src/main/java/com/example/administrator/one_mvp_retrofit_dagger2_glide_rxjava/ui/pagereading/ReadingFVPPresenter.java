package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseSubscribe;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.GsonHelper;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.JsonUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Shuwen on 2016/10/30.
 */

public class ReadingFVPPresenter extends BasePresenter<ReadingFVPMvpView> {
    private DataManager dataManager;
    private Context context;
    private ReadingFVPMvpView mMvpView;
    private Subscription subscription;

    @Inject
    public ReadingFVPPresenter(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
    }

    @Override
    public void attachView(ReadingFVPMvpView mMvpView) {
        this.mMvpView = mMvpView;
    }

    /**
     * 获取阅读界面数据
     * @param page1
     */
    public void getReadingData(String page1,boolean showProgress){
        subscription = dataManager.getReadingData(page1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(context,showProgress) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        TLog.getInstance().i(jsonObject.toString());
                        mMvpView.showData(JsonUtils.getItemEntitys(jsonObject.toString()));
                    }
                });
    }

    /**
     * 获取阅读界面的轮播图
     */
    public void getReadingHeadImgs(){
        subscription = dataManager.getReadingHeadImgs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscribe<JSONObject>(context,false) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.showHeadImags(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),ReadingFVPImagsBean.class));
                    }
                });
    }
}
