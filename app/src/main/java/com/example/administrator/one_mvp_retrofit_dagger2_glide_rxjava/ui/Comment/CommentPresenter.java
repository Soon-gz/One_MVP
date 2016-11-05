package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.Comment;

import android.content.Context;
import android.content.pm.ResolveInfo;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseSubscribe;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.GsonHelper;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PostResultBean;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/5.
 */

public class CommentPresenter extends BasePresenter<IBaseView<PostResultBean>> {
    private DataManager dataManager;
    private Context context;
    private Subscription subscription;
    private IBaseView<PostResultBean> mMvpView;

    @Inject
    public CommentPresenter(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        this.context = context;
    }

    @Override
    public void detachViews() {
        super.detachViews();
        if (subscription!=null){
            subscription.unsubscribe();
            subscription = null;
        }
        if (mMvpView != null){
            mMvpView = null;
        }
    }

    @Override
    public void attachView(IBaseView<PostResultBean> mMvpView) {
        this.mMvpView = mMvpView;
    }


    /**
     * 提交评论,使用默认手机的id,使用的是我个人的账号，这点没办法，毕竟是别人的后台
     * @param itemid
     * @param type
     * @param cmtid
     */
    public void postComment(String itemid,String type,String cmtid,String content){
        subscription = dataManager.postComment(itemid,type,cmtid,content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscribe<JSONObject>(context,false) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),PostResultBean.class));
                    }
                });
    }
}
