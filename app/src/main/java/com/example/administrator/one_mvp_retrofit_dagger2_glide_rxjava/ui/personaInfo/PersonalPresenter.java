package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.personaInfo;

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
 * Created by Administrator on 2016/11/14.
 */

public class PersonalPresenter extends BasePresenter<PersonalMvpView<PersonInfoBean>> {
    private DataManager dataManager;
    private Context context;
    private Subscription subscription;
    private PersonalMvpView<PersonInfoBean> mMvpView;

    @Inject
    public PersonalPresenter(DataManager dataManager, Context context) {
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

    /**
     * 加载用户详细信息
     * @param userId
     */
    public void loadUserInfo(String userId){
        subscription = dataManager.getUserInfo(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscribe<JSONObject>(context) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),PersonInfoBean.class));
                    }
                });
    }

    @Override
    public void attachView(PersonalMvpView<PersonInfoBean> mMvpView) {
        this.mMvpView = mMvpView;
    }
}
