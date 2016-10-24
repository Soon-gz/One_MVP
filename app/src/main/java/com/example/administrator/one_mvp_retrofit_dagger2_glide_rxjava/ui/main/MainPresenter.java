package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.main;

import android.util.Log;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Shuwen on 2016/10/13.
 */

public class MainPresenter extends BasePresenter<MainMvpView<Mianbean>> {

    private final DataManager dataManager;
    private Subscription subscription;
    private MainMvpView<Mianbean> mMvpView;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mMvpView) {
        this.mMvpView = mMvpView;
    }

    public void getData(int page){
        Log.i("tag00","发起网络请求");
        subscription = dataManager.getdatas(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<JSONObject>() {
            @Override
            public void onCompleted() {
                Log.i("tag00","完成");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("tag00","错误");
            }

            @Override
            public void onNext(JSONObject result) {
                Log.i("TAG00",result.toString());
                Gson gson = new Gson();
                Mianbean mianbean = gson.fromJson(result.toString(),Mianbean.class);
//                List<Mianbean>list = new ArrayList<>();
//                list.add(mianbean);
                mMvpView.showData(mianbean);
            }
        });
    }

    @Override
    public void detachViews() {
        super.detachViews();
        if (subscription != null){
            subscription.unsubscribe();
        }
    }
}
