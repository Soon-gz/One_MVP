package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by ShuWen on 2016/10/15.
 */

public abstract class BaseFragment extends Fragment {
    protected Bundle bundle;
    protected View mContentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        //注入控制器
        iniInjector();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            mContentView = inflater.inflate(getContentViewLayoutID(),null);
            ButterKnife.bind(this, mContentView);
            //初始化方法
            initViewsAndEvents();
            return mContentView;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }


    /**
     * 初始化视图和事件
     */
    protected abstract void initViewsAndEvents();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    /**
     * 依赖注入
     */
    protected abstract void iniInjector();

    /**
     * 返回View的id
     * @return
     */
    protected abstract int getContentViewLayoutID();
}
