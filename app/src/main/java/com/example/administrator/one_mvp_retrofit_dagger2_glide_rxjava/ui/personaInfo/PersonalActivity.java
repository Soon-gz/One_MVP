package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.personaInfo;

import android.os.Bundle;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;

import javax.inject.Inject;

import butterknife.OnClick;

public class PersonalActivity extends BaseActivity implements PersonalMvpView<PersonInfoBean>{

    @Inject
    PersonalPresenter mPresenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_personal;
    }

    @Override
    protected void iniInjector() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachViews();
    }
}
