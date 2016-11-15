package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemain;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseSubscribe;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ProgressDialogHelper;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.dagger.PerActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.GsonHelper;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/26.
 */

public class MainPagePresenter extends BasePresenter<MainPageMvpView<MainPageBean>> {
    private final DataManager mDataManager;
    public Subscription mSubscription;
    private MainPageMvpView<MainPageBean> mMvpView;
    private Context mContext;

    @Inject
    public MainPagePresenter(DataManager mDataManager, Context context) {
        this.mDataManager = mDataManager;
        mContext = context;
    }

    @Override
    public void attachView(MainPageMvpView<MainPageBean> mMvpView) {
        this.mMvpView = mMvpView;
    }

    @Override
    public void detachViews() {
        super.detachViews();
        if (mSubscription != null){
            mSubscription.unsubscribe();
            mSubscription = null;
        }
        mMvpView = null;
    }

    /**
     * 获取主页展示数据
     */
    public void getMainpageData(){
        mSubscription = mDataManager.getMainPageData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(mContext) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),MainPageBean.class));
                    }
                });
    }


}
