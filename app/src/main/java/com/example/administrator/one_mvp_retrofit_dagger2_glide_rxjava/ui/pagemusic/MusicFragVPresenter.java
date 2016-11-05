package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BasePresenter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit.DataManager;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseSubscribe;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.GsonHelper;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PostResultBean;

import org.json.JSONObject;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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

    @Override
    public void detachViews() {
        super.detachViews();
        if (subscription != null){
            subscription.unsubscribe();
        }
        if (mMvpView != null){
            mMvpView = null;
        }
    }

    /**
     * 获取音乐界面详细数据
     * @param musicId
     */
    public void getMusicDetail(final String musicId, final String pageNum){
        subscription = dataManager.getMusicDetailData(musicId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new Func1<JSONObject, Observable<JSONObject>>() {
                    @Override
                    public Observable<JSONObject> call(JSONObject jsonObject) {
                        mMvpView.showData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),MusicFragVPDataBean.class));
                        return dataManager.getMusicContent(musicId,pageNum).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new BaseSubscribe<JSONObject>(context,false) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.showContentData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),MusicFragVPContentBean.class).getData().getData());
                    }
                });
    }

    /**
     * 加载更多的评论数据
     * @param musicId
     * @param pageNum
     */
    public void getMusicContentMore(final String musicId,final String pageNum){
        subscription = dataManager.getMusicContent(musicId,pageNum)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(context,false) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.showContentData(GsonHelper.getGsonObject().fromJson(jsonObject.toString(),MusicFragVPContentBean.class).getData().getData());
                    }
                });
    }

    /**
     * 音乐点赞
     * @param musicId
     * @param type
     */
    public void postMusicPraise(String musicId,String type){
        subscription = dataManager.postPraise(musicId,type)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(context,false) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.praise(GsonHelper.getGsonObject().fromJson(jsonObject.toString(), PostResultBean.class));
                    }
                });
    }

    /**
     * 音乐评论点赞
     * @param musicId
     * @param type
     */
    public void postMusicLike(String musicId,String type,String cmtId){
        subscription = dataManager.postMusicPraise(musicId,type,cmtId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(context,false) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.praise(GsonHelper.getGsonObject().fromJson(jsonObject.toString(), PostResultBean.class));
                    }
                });
    }
    /**
     * 音乐评论取消赞
     * @param musicId
     * @param type
     */
    public void postMusicUnPraise(String musicId,String type,String cmtId){
        subscription = dataManager.postMusicUnPraise(musicId,type,cmtId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseSubscribe<JSONObject>(context,false) {
                    @Override
                    public void onNextJSONObject(JSONObject jsonObject) {
                        mMvpView.praise(GsonHelper.getGsonObject().fromJson(jsonObject.toString(), PostResultBean.class));
                    }
                });
    }


}
