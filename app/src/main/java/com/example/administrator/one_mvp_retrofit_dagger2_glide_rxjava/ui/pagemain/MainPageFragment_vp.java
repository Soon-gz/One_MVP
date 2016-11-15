package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemain;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.StringUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.PostResultBean;

import javax.inject.Inject;

import butterknife.Bind;

public class MainPageFragment_vp extends BaseFragment implements MainPageMvpView_vp<MainPageHomeBean>, CompoundButton.OnCheckedChangeListener {

    @Inject
    MainPagePresenter_vp mPresenter;

    @Bind(R.id.main_page_framelayout)
    FrameLayout main_page_framelayout;
    @Bind(R.id.main_page_image)
    ImageView main_page_image;
    @Bind(R.id.main_page_VOL)
    TextView main_page_VOL;
    @Bind(R.id.main_page_author)
    TextView main_page_author;
    @Bind(R.id.main_page_content)
    TextView main_page_content;
    @Bind(R.id.main_page_time)
    TextView main_page_time;
    @Bind(R.id.main_page_land_number)
    TextView main_page_land_number;
    @Bind(R.id.main_page_land_checkbox)
    CheckBox main_page_land_checkbox;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static MainPageFragment_vp newInstance(String param1, String param2) {
        MainPageFragment_vp fragment = new MainPageFragment_vp();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViewsAndEvents() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //选择显示界面
        initLayout(mParam1);
    }


    //显示主界面或加载
    private void initLayout(String mParam1) {
        switch (mParam1){
            case Const.PAGE_MAIN_IS_FIRST:
                showLayoutId(0);
                break;
            case Const.PAGE_MAIN_OTHER:
                showLayoutId(1);
                //发起网络请求
                mPresenter.getMainPageDetail(mParam2);
                //设置监听
                main_page_land_checkbox.setOnCheckedChangeListener(this);
                break;
            case Const.PAGE_MAIN_IS_LAST:
                showLayoutId(2);
                break;
        }
    }

    public void showLayoutId(int layputId){
        for (int index = 0; index < main_page_framelayout.getChildCount(); index++) {
            if (index == layputId){
                main_page_framelayout.getChildAt(index).setVisibility(View.VISIBLE);
            }else {
                main_page_framelayout.getChildAt(index).setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void iniInjector() {
        ((BaseActivity)getActivity()).activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_main_page_fragment_vp;
    }

    @Override
    public void showData(MainPageHomeBean data) {
        Glide.with(getActivity()).load(data.getData().getHp_img_url()).dontAnimate().thumbnail(0.1f).into(main_page_image);
        main_page_VOL.setText(data.getData().getHp_title());
        main_page_author.setText(data.getData().getHp_author());
        main_page_content.setText(data.getData().getHp_content());
        main_page_time.setText(data.getData().getHp_makettime().split(" ")[0]);
        main_page_land_number.setText(data.getData().getPraisenum()+"");
    }

    @Override
    public void praise(PostResultBean resultBean) {
        if (Const.POST_SUCCESS.equals(resultBean.getMsg())){
            ToastUtils.showToast("点赞成功");
        }else {
            ToastUtils.showToast("点赞失败");
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b){
            if (!StringUtils.isEmpty(main_page_land_number.getText().toString())){
                main_page_land_number.setText(Integer.parseInt(main_page_land_number.getText().toString())+1+"");
                mPresenter.postPraise(mParam2,Const.HP_CONTENT);
            }
        }else {
            main_page_land_number.setText(Integer.parseInt(main_page_land_number.getText().toString())-1+"");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachViews();
    }
}
