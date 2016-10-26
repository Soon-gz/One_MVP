package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils;


import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/10/26.
 */

public class GsonHelper {
    private static Gson gson;

    public static Gson getGsonObject(){
        if (gson == null){
            gson = new Gson();
        }
        return gson;
    }

}
