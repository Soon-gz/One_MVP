package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemain;

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
 * Created by Administrator on 2016/10/27.
 */

public class MainPagePresenter_vp extends BasePresenter<MainPageMvpView_vp<MainPageHomeBean>> {

    private DataManager dataManager;
    private MainPageMvpView_vp<MainPageHomeBean> mIBaseMvpView;
    private Subscription subscription;
    private Context mContext;

    @Inject
    public MainPagePresenter_vp(DataManager dataManager, Context mContext) {
        this.dataManager = dataManager;
        this.mContext = mContext;
    }

    @Override
    public void attachView(MainPageMvpView_vp<MainPageHomeBean> mMvpView) {
        mIBaseMvpView = mMvpView;
    }

    /**
     * 根据主页详情id，获取详细信息
     * @param hpcontent_id
     */
    public void getMainPageDetail(String hpcontent_id){
        subscription = dataManager.getMainPageDetail(hpcontent_id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(mContext,false) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mIBaseMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),MainPageHomeBean.class));
                    }
                });
    }

    /**
     * 主页点赞
     * @param itemid
     * @param type
     */
    public void postPraise(String itemid,String type){
        subscription = dataManager.postPraise(itemid,type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(mContext,false) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mIBaseMvpView.praise(GsonHelper.getGsonObject().fromJson(jsonObject.toString(), PostResultBean.class));
                    }
                });
    }
}
