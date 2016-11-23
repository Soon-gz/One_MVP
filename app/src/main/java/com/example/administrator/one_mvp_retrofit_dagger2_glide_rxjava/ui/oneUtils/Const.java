package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils;

/**
 * Created by Shuwen on 2016/10/19.
 * 所有变量
 */

public class Const {
    //欢迎界面的动画识别标识
    public static final String MOVIE_GUIDE = "MOVIE_GUIDE";
    public static final String MUSIC_GUIDE = "MUSIC_GUIDE";
    public static final String READING_GUIDE = "READING_GUIDE";
    public static final String ONE_GUIDE = "ONE_GUIDE";

    //是否第一次打开app
    public static final String ISFIRSTRUN = "IsFirstRun";
    //进入欢迎界面
    public static final int START_WELCOME = 0;
    //进入引导页
    public static final int START_GUIDE = 1;

    //主页fragment的标签
    public static final int MAIN_PAGE_FRAGMENT = 0;
    public static final int READING_PAGE_FRAGMENT = 1;
    public static final int MUSIC_PAGE_FRAGMENT = 2;
    public static final int MOVIE_PAGE_FRAGMENT = 3;

    //创建viewpager的Fragment标记
    public static final String PAGE_MAIN_IS_FIRST = "PAGE_MAIN_IS_FIRST";
    public static final String PAGE_MAIN_IS_LAST = "PAGE_MAIN_IS_LAST";
    public static final String PAGE_MAIN_OTHER = "PAGE_MAIN_OTHER";

    //post请求返回状态
    public static final String POST_SUCCESS = "success";

    //点赞类型
    public static final String HP_CONTENT = "hpcontent";
    public static final String MUSIC = "music";
    public static final String MOVIE = "movie";


    //月份
    public static final String DEC = "Dec";
    public static final String NOV = "Nov";
    public static final String OCT = "Oct";
    public static final String SEP = "Sep";
    public static final String AUG = "Aug";
    public static final String JUL = "Jul";
    public static final String JUN = "Jun";
    public static final String MAY = "May";
    public static final String APR = "Apr";
    public static final String MAR = "Mar";
    public static final String FEB = "Feb";
    public static final String JAN = "Jan";

    //阅读类型
    public static final String ESSAY = "1";//短篇
    public static final String SERIAL = "2";//连载
    public static final String QUESTION = "3";//问答

    //音乐界面多样式布局
    public static final int MUSIC_HEAD_ITEM = 100;
    public static final int MUSIC_SECOND_ITEM = 200;
    public static final int MUSIC_THRID_ITEM = 300;
    public static final int MUSIC_CONTENT_ITEM = 400;

    //电影详情界面多样式布局
    public static final int MOVIE_HEAD_ITEM = 10;
    public static final int Movie_CONTENT_ITEM = 20;
    public static final String MOVIE_ID = "MOVIE_ID";
    public static final String MOVIE_VIDEO_URL = "MOVIE_VIDEO_URL";
    public static final String MOVIE_VIDEO_TITLE = "MOVIE_VIDEO_TITLE";
    public static final String MOVIE_VIDEO_INNER_COVER = "MOVIE_VIDEO_INNER_COVER";
    public static final String MOVIE_SCORE_TYPE = "MOVIE_SCORE_TYPE";
    public static final String NO_SCORE = "NO_SCORE";

    //热门评论
    public static final String HOT_CONTENT = "0";
    public static final String NOT_HOT_CONTENT = "1";
    public static final String ITEM_ID = "ITEM_ID";
    public static final String COMMETNT_TYPE = "COMMETNT_TYPE";
    public static final int COMMENT_REQUEST_CODE = 1000;
    public static final int COMMENT_RESULT_CODE = 1001;

    //刷新
    public static final String REFRESH = "REFRESH";

    //搜索界面的标签
    public static final String SEARCH_PICTURE = "hp";
    public static final String SEARCH_READING = "reading";
    public static final String SEARCH_MUSIC = "music";
    public static final String SEARCH_MOVIE = "movie";
    public static final String SEARCH_AUTHOR = "author";



}
