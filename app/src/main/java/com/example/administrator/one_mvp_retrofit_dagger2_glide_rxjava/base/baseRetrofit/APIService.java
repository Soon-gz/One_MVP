package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit;

import org.json.JSONObject;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
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
     * 音乐点赞，采用表单样式上传post数据
     * @param itemid
     * @param type
     * @param deviceid
     * @param version
     * @param devicetype
     * @param platform
     * @return
     */
    @FormUrlEncoded
    @POST("comment/praise")
    Observable<JSONObject> postMusicPraise(@Field("itemid")String itemid, @Field("type")String type,
                                      @Field("deviceid")String deviceid, @Field("version")String version,
                                      @Field("devicetype")String devicetype, @Field("platform")String platform,
                                           @Field("cmtid")String cmtid);

    /**
     * 音乐取消赞
     * @return
     */
    @FormUrlEncoded
    @POST("comment/unpraise")
    Observable<JSONObject> postMusicUnPraise(@Field("itemid")String itemid, @Field("type")String type,
                                             @Field("deviceid")String deviceid, @Field("version")String version,
                                             @Field("devicetype")String devicetype, @Field("platform")String platform,
                                             @Field("cmtid")String cmtid);

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

    /**
     * 获取音乐评论
     * @param contentId
     * @return
     */
    @GET("comment/praiseandtime/music/{contentId}/{pageNum}")
    Observable<JSONObject> getMusicContent(@Path("contentId") String contentId,@Path("pageNum") String pageNum);

    /**
     * 发布评论
     * @param itemid
     * @param type
     * @param deviceid
     * @param version
     * @param devicetype
     * @param platform
     * @param cmtid
     * @return
     */
    @FormUrlEncoded
    @POST("comment/add")
    Observable<JSONObject> postComment(@Field("itemid")String itemid, @Field("type")String type,
                                       @Field("deviceid")String deviceid, @Field("version")String version,
                                       @Field("devicetype")String devicetype, @Field("platform")String platform,
                                       @Field("cmtid")String cmtid,@Field("jwt") String jwt,@Field("user_id")String user_id,
                                       @Field("content")String content);

    /**
     * 获取电影展示数据
     * @param movieId
     * @return
     */
    @GET("movie/list/{movieId}")
    Observable<JSONObject> getMovieList(@Path("movieId") String movieId);

    /**
     * 搜索界面
     * @param type
     * @param content
     * @return
     */
    @GET("search/{type}/{content}")
    Observable<JSONObject> getSearch(@Path("type") String type,@Path("content") String content);

    /**
     * 获取电影详情
     * @param movieId
     * @return
     */
    @GET("movie/detail/{movieId}")
    Observable<JSONObject> getMovieDetail(@Path("movieId") String movieId);

    /**
     * 获取电影故事
     * @param movieId
     * @return
     */
    @GET("movie/{movieId}/story/1/0")
    Observable<JSONObject> getMovieStory(@Path("movieId") String movieId);

}
