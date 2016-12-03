package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.personaInfo.PersonInfoBean;

/**
 * Created by Administrator on 2016/12/3.
 */

public class DataCache {
    /**用于缓存用户是否登录 **/
    private static boolean isLogined = false;
    /**缓存登录用户的信息 **/
    private static String userId;

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        DataCache.userId = userId;
    }

    public static boolean isLogined() {
        return isLogined;
    }

    public static void setIsLogined(boolean isLogined) {
        DataCache.isLogined = isLogined;
    }


    public static void clearCache(){
        isLogined = false;
        userId = "";
    }


}
