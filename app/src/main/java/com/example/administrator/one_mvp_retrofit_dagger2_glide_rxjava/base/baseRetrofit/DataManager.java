package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseRetrofit;

import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit.http.Path;
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
     * 音乐点赞
     * @param itemid
     * @param type
     * @return
     */
    public Observable<JSONObject> postMusicPraise(String itemid,String type,String cmtid){
        return apiService.postMusicPraise(itemid,type,"ffffffff-b821-e83f-45c3-423b5c7ea996","3.5.0","android","android",cmtid);
    }
    /**
     * 音乐取消点赞
     * @param itemid
     * @param type
     * @return
     */
    public Observable<JSONObject> postMusicUnPraise(String itemid,String type,String cmtid){
        return apiService.postMusicUnPraise(itemid,type,"ffffffff-b821-e83f-45c3-423b5c7ea996","3.5.0","android","android",cmtid,"6407273");
    }



    /**
     * 查看主页往期数据
     * @param date
     * @return
     */
    public Observable<JSONObject> getMainPageIssueAll(String date){
        return apiService.getMainPageIssueAll(date);
    }

    /**
     * 获取阅读界面数据
     * @param page
     * @return
     */
    public Observable<JSONObject> getReadingData(String page){
        return apiService.getReadingData(page);
    }

    /**
     * 获取阅读界面轮播图
     * @return
     */
    public Observable<JSONObject> getReadingHeadImgs(){
        return apiService.getReadingHeadImgs();
    }

    /**
     * 获取音乐界面需要展示的数据
     * @return
     */
    public Observable<JSONObject> getMusicPageData(){
        return apiService.getMusicPageData();
    }


    /**
     * 获取音乐详细信息
     * @param musicId
     * @return
     */
    public Observable<JSONObject> getMusicDetailData(String musicId){
        return apiService.getMusicHomeData(musicId);
    }

    /**
     * 获取音乐评论
     * @param contentId
     * @return
     */
    public Observable<JSONObject> getMusicContent(String contentId,String pageNum){
        return apiService.getMusicContent(contentId,pageNum);
    }

    /**
     * 提交评论
     * @param itemid
     * @param type
     * @param cmtid
     * @return
     */
    public Observable<JSONObject> postComment(String itemid,String type,String cmtid,String content){
        return apiService.postComment(itemid,type,"ffffffff-b821-e83f-45c3-423b5c7ea996","3.5.0","android","android",cmtid,"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE0NzY4NjEyNDAsInVzZXJpZCI6IjY0MDcyNzMifQ.IeccuZPGtEScIxEuVXOTwcKOiZ_hqxGOmEiFmqjvG5M","6407273",content);
    }

    /**
     * 获取电影页面展示数据
     * @param movieId
     * @return
     */
    public Observable<JSONObject> getMovieList(String movieId){
        return apiService.getMovieList(movieId);
    }

    /**
     * 搜索界面
     * @param type
     * @param content
     * @return
     */
    public Observable<JSONObject> getSearch(String type,String content){
        return apiService.getSearch(type,content);
    }

    /**
     * 获取电影详情
     * @param movieId
     * @return
     */
    public Observable<JSONObject> getMovieDetail(String movieId){
        return apiService.getMovieDetail(movieId);
    }

    /**
     * 获取电影故事
     * @param movieId
     * @return
     */
    public Observable<JSONObject> getMovieStory(String movieId){
        return apiService.getMovieStory(movieId);
    }

    /**
     * 获取电影故事评论
     * @param movieId
     * @return
     */
    public Observable<JSONObject> getMovieComment(String movieId,String commentId){
        return apiService.getMovieComment(movieId,commentId);
    }

    /**
     * 电影故事点赞
     * @param storyid
     * @param movieId
     * @return
     */
    public Observable<JSONObject> postMovieStoryPraise(String storyid,String movieId){
        return apiService.postMovieStoryPraise(storyid,"6407273","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE0NzkyMDE0NDcsInVzZXJpZCI6IjY0MDcyNzMifQ.nuhymkpKMR2q71Je7H2BgCwXOmaVF0Qy_Vh5peAYHL4",movieId,"3.5.0","android");
    }

    /**
     * 电影故事取消赞
     * @param storyid
     * @param movieId
     * @return
     */
    public Observable<JSONObject> postMovieStoryUnPraise(String storyid,String movieId){
        return apiService.postMovieStoryUnPraise(storyid,"6407273","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE0NzkyMDE0NDcsInVzZXJpZCI6IjY0MDcyNzMifQ.nuhymkpKMR2q71Je7H2BgCwXOmaVF0Qy_Vh5peAYHL4",movieId,"3.5.0","android");
    }

    /**
     * 获取电影详情当前用户的评分
     * @param movieId
     * @return
     */
    public Observable<JSONObject> getMygrade(String movieId){
        return apiService.getMygrade(movieId,"6407273","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE0NzY4NjEyNDAsInVzZXJpZCI6IjY0MDcyNzMifQ.IeccuZPGtEScIxEuVXOTwcKOiZ_hqxGOmEiFmqjvG5M");
    }


}
