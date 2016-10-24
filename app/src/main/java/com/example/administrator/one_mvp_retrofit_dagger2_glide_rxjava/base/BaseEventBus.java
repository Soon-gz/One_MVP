package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base;

/**
 * Created by Shuwen on 2016/10/13.
 */
//用于Activity和Fragment之间的数据交互，可在该基础上扩展
public abstract class BaseEventBus {
    public String msg;

    public BaseEventBus(String msg) {
        this.msg = msg;
    }

}
