package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.personaInfo;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;

/**
 * Created by Administrator on 2016/11/14.
 */

public interface PersonalMvpView<PersonInfoBean>  extends IBaseView<PersonInfoBean>{
    void showData(PersonMoreBean personMoreBean);
}
