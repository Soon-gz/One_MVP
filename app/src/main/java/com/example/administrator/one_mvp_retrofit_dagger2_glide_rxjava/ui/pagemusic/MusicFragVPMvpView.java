package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PostResultBean;

import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */

public interface MusicFragVPMvpView<MusicFragVPDataBean> extends IBaseView<MusicFragVPDataBean> {
    void showContentData(List<MusicFragVPContentBean.DataBean0.DataBean>list);
    void praise(PostResultBean resultBean);
}
