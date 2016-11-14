package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.personaInfo;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;

import javax.inject.Inject;

import rx.Subscription;

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
    public void attachView(PersonalMvpView<PersonInfoBean> mMvpView) {
        this.mMvpView = mMvpView;
    }
}
