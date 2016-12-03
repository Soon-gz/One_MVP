package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.personaInfo;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.StringUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.DataCache;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalActivity extends BaseActivity implements PersonalMvpView<PersonInfoBean>{

    @Inject
    PersonalPresenter mPresenter;

    @Bind(R.id.layoutAll)
    FrameLayout frameLayout;
    @Bind(R.id.personal_sign_headview)
    CircleImageView personal_sign_headview;
    @Bind(R.id.personal_sign_name)
    TextView personal_sign_name;
    @Bind(R.id.personal_sign_little_image)
    ImageView personal_sign_little_image;
    @Bind(R.id.personal_sign_more)
    LinearLayout personal_sign_more;

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
        String userId = getUserId();
        if (!StringUtils.isEmpty(userId)){
            mPresenter.loadUserInfo(userId);
        }
    }

    @Override
    public void showData(PersonMoreBean personMoreBean) {

    }

    @Override
    public void showData(PersonInfoBean data) {
        FragmentHelper.showLayoutId(frameLayout,1);
        FragmentHelper.setCircleImage(personal_sign_headview,data.getData().getWeb_url(),this);
        personal_sign_name.setText(data.getData().getUser_name());
    }

    @OnClick({R.id.personal_unsign_return_ib,R.id.personal_sign_return_ib})
    public void personalOnClick(){
        finish();
    }

    @Override
    public int getStatusColor() {
        return R.color.black;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachViews();
    }

    public String getUserId() {
        String userId = getIntent().getStringExtra(Const.USER_ID);
        if (StringUtils.isEmpty(userId)){
            userId = DataCache.getUserId();
        }
        return !StringUtils.isEmpty(userId)?userId:"";
    }

}
