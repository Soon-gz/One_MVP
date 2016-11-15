package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.personaInfo;

import android.os.Bundle;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;

import butterknife.OnClick;

public class PersonalActivity extends BaseActivity implements PersonalMvpView<PersonInfoBean>{

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_personal;
    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @Override
    public void showData(PersonMoreBean personMoreBean) {

    }

    @Override
    public void showData(PersonInfoBean data) {

    }

    @OnClick(R.id.personal_unsign_return_ib)
    public void personalOnClick(){
        finish();
    }
}
