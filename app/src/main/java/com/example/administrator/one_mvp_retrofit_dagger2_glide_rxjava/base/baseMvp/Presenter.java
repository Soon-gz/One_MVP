package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp;

/**
 * Created by Shuwen on 2016/10/13.
 */

public interface Presenter<T extends IBaseView> {
    /**
     *  绑定View和Presenter，mvp用于调用将Presenter网络请求获取的数据传入Activity
     *  这也是MVP较于MVC的一个优势，将View层和网络请求分开，数据加载和解析与显示逻辑分开
     */
    void attachViews(T mvpView);
    //解绑
    void detachViews();
}
