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


}
