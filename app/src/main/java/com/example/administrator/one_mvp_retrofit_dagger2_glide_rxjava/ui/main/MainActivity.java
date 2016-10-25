package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;

import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements MainMvpView<Mianbean> {

    @Inject
    MainPresenter mPresenter;

    @Bind(R.id.a)
    TextView textView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void iniInjector() {
        activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        mPresenter.getData(0);
        textView.setText("我曹！");
    }

    @Override
    public void showData(List<Mianbean> data) {
        Log.i("tag00",data.get(0).getData().get(0).toString());
    }

    @Override
    public void showData(Mianbean data) {
        Log.i("tag00",data.getData().get(0).toString());
    }
}
