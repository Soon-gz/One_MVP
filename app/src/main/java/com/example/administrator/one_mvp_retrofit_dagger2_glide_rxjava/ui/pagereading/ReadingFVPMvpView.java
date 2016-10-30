package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/30.
 */

public interface ReadingFVPMvpView  extends IBaseView{
    void showHeadImags(ReadingFVPImagsBean imagsBean);
    void showData(List<ItemEntity> list);
}
