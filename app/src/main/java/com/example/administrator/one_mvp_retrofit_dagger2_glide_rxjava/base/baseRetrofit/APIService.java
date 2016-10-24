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
    @GET("idlist/{page}")
    Observable<JSONObject> getDatas(@Path("page")int page);
}
