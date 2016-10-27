package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Shuwen on 2016/10/13.
 */
@Singleton
public class DataManager {
    private final APIService apiService;

    @Inject
    public DataManager(APIService apiService) {
        this.apiService = apiService;
    }

    /**
     * 获取主页展示数据
     * @return
     */
    public Observable<JSONObject> getMainPageData() {
        return apiService.getDatas();
    }

    /**
     * 根据主页详情id，获取详细信息
     * @param hpContent_id
     * @return
     */
    public Observable<JSONObject> getMainPageDetail(String hpContent_id){
        return apiService.getMainPageDetail(hpContent_id);
    }

    /**
     * 主页点赞,这里的设备id是加密的，我只能截取到我的手机的id，不知道加密算法，不过这样别的手机也能用
     * @param itemid
     * @param type
     * @return
     */
    public Observable<JSONObject> postPraise(String itemid,String type){
        return apiService.postPraise(itemid,type,"ffffffff-b821-e83f-45c3-423b5c7ea996","3.5.0","android","android");
    }

    /**
     * 查看主页往期数据
     * @param date
     * @return
     */
    public Observable<JSONObject> getMainPageIssueAll(String date){
        return apiService.getMainPageIssueAll(date);
    }

}
