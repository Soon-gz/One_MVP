package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.main;

import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */

public class Mianbean {

    /**
     * res : 0
     * data : ["1502","1501","1500","1499","1496","1495","1494","1493","1492","1491"]
     */

    private int res;
    private List<String> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
