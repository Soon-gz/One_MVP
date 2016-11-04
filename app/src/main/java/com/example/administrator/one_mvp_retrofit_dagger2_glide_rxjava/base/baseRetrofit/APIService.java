package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit;

import org.json.JSONObject;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
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

    /**
     * 根据主页详情id，获取详细信息
     * @param hpcontent_id
     * @return
     */
    @GET("hp/detail/{hpcontent_id}")
    Observable<JSONObject> getMainPageDetail(@Path("hpcontent_id") String hpcontent_id);

    /**
     * 主页点赞，采用表单样式上传post数据
     * @param itemid
     * @param type
     * @param deviceid
     * @param version
     * @param devicetype
     * @param platform
     * @return
     */
    @FormUrlEncoded
    @POST("praise/add")
    Observable<JSONObject> postPraise(@Field("itemid")String itemid, @Field("type")String type,
                                      @Field("deviceid")String deviceid, @Field("version")String version,
                                      @Field("devicetype")String devicetype, @Field("platform")String platform);

    /**
     * 查看往期数据
     * @param date
     * @return
     */
    @GET("hp/bymonth/{date}")
    Observable<JSONObject> getMainPageIssueAll(@Path("date") String date);

    /**
     * 获取阅读界面的数据
     * @return
     */
    @GET("reading/index/{page}")
    Observable<JSONObject> getReadingData(@Path("page")String page);

    /**
     * 获取阅读界面的轮播图
     * @return
     */
    @GET("reading/carousel/")
    Observable<JSONObject> getReadingHeadImgs();


    /**
     * 获取音乐界面需要展示的数据
     * @return
     */
    @GET("music/idlist/0")
    Observable<JSONObject> getMusicPageData();

    /**
     * 获取音乐详细信息
     * @param musicId
     * @return
     */
    @GET("music/detail/{musicId}")
    Observable<JSONObject> getMusicHomeData(@Path("musicId") String musicId);
}
