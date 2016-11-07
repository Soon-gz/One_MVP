package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.search;

import android.os.Bundle;
import android.view.View;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;

import butterknife.OnClick;

public class SearchActivity extends BaseActivity {

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_search;
    }

    @Override
    protected void iniInjector() {

    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {

    }

    @OnClick({R.id.search_return_btn,R.id.search_go_btn})
    public void searchOnClick(View view){
        switch (view.getId()){
            case R.id.search_return_btn:
                finish();
                break;
            case R.id.search_go_btn:
                break;
        }
    }
}
