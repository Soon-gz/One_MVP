package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ProgressDialogHelper;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.StringUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.BaseFgAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.search_viewpager)
    ViewPager search_viewpager;
    @Bind(R.id.search_framelayout)
    FrameLayout search_framelayout;
    @Bind(R.id.search_edittext)
    EditText search_edittext;
    @Bind(R.id.search_radiogroup_gp)
    RadioGroup search_radiogroup_gp;

    private List<BaseFragment> searchFragments;
    private BaseFgAdapter baseFgAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        searchFragments = new ArrayList<>();
        baseFgAdapter = new BaseFgAdapter(getSupportFragmentManager(), searchFragments);
        search_viewpager.setAdapter(baseFgAdapter);
        search_radiogroup_gp.setOnCheckedChangeListener(this);
        search_viewpager.addOnPageChangeListener(this);
        search_viewpager.setOffscreenPageLimit(5);
        //打开软键盘
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                InputMethodManager inputManager = (InputMethodManager) search_edittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(search_edittext, 0);
            }

        }, 500);
    }

    public void setChecked(int position) {
        for (int index = 0; index < search_radiogroup_gp.getChildCount(); index++) {
            if (position == index) {
                RadioButton button = (RadioButton) search_radiogroup_gp.getChildAt(index);
                button.setTextColor(getResources().getColor(R.color.coarse_blue));
            } else {
                RadioButton button = (RadioButton) search_radiogroup_gp.getChildAt(index);
                button.setTextColor(getResources().getColor(R.color.black_80));
            }

        }
    }

    @OnClick({R.id.search_return_btn, R.id.search_go_btn})
    public void searchOnClick(View view) {
        switch (view.getId()) {
            case R.id.search_return_btn:
                finish();
                break;
            case R.id.search_go_btn:
                if (!StringUtils.isEmpty(search_edittext.getText().toString().trim())) {
                    ProgressDialogHelper.getInstance().showProgressDialog(this,"正在加载中...");
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            if (ProgressDialogHelper.getInstance().isWorking()){
                                ProgressDialogHelper.getInstance().hideProgressDialog();
                            }
                        }
                    },500);
                    FragmentHelper.showLayoutId(search_framelayout, 1);
                    initFragments();
                } else {
                    ToastUtils.showToast("请输入需要搜索的内容。");
                }
                break;
        }
    }

    private void initFragments() {
        if (searchFragments.size() > 0) {
            for (BaseFragment baseFragment:searchFragments) {
                ((SearchFragment)baseFragment).loadNetWork(search_edittext.getText().toString().trim());
            }
            baseFgAdapter.notifyDataSetChanged();
        } else {
            searchFragments.add(SearchFragment.newInstance(Const.SEARCH_PICTURE, search_edittext.getText().toString().trim()));
            searchFragments.add(SearchFragment.newInstance(Const.SEARCH_READING, search_edittext.getText().toString().trim()));
            searchFragments.add(SearchFragment.newInstance(Const.SEARCH_MUSIC, search_edittext.getText().toString().trim()));
            searchFragments.add(SearchFragment.newInstance(Const.SEARCH_MOVIE, search_edittext.getText().toString().trim()));
            searchFragments.add(SearchFragment.newInstance(Const.SEARCH_AUTHOR, search_edittext.getText().toString().trim()));
            baseFgAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int index = 0; index < search_radiogroup_gp.getChildCount(); index++) {
            if (position == index){
                RadioButton button = (RadioButton) search_radiogroup_gp.getChildAt(position);
                button.setChecked(true);
            }else {
                RadioButton button = (RadioButton) search_radiogroup_gp.getChildAt(position);
                button.setChecked(false);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.search_picture:
                search_viewpager.setCurrentItem(0);
                setChecked(0);
                break;
            case R.id.search_reading:
                search_viewpager.setCurrentItem(1);
                setChecked(1);
                break;
            case R.id.search_music:
                search_viewpager.setCurrentItem(2);
                setChecked(2);
                break;
            case R.id.search_movie:
                search_viewpager.setCurrentItem(3);
                setChecked(3);
                break;
            case R.id.search_author:
                search_viewpager.setCurrentItem(4);
                setChecked(4);
                break;
        }
    }
}
