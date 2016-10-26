package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit;

import org.json.JSONObject;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Shuwen on 2016/10/12.
 * 项目提供数据协议
 */
public interface APIService {
    /**
     * 获取MainPageFragment的数据标记
     * @return
     */
    @GET("hp/idlist/0")
    Observable<JSONObject> getDatas();
}
