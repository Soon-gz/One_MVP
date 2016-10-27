package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemain;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PostResultBean;

/**
 * Created by Administrator on 2016/10/26.
 */

public interface MainPageMvpView_vp<T> extends IBaseView<T>{
    void praise(PostResultBean resultBean);
}
