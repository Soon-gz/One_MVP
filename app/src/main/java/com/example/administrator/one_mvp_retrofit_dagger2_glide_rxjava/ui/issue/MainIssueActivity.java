package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.issue;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerViewHolder;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.DateUtil;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.DividerItemDecoration;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.ToastUtils;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.recycleritemanimator.AlphaAnimatorAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.recycleritemanimator.SlideScaleInOutRightItemAnimator;

import java.util.ArrayList;
import java.util.List;


import butterknife.Bind;
import butterknife.OnClick;

public class MainIssueActivity extends BaseActivity {

    @Bind(R.id.main_page_issue_rv)
    RecyclerView main_page_issue_rv;


    private BaseRecyclerAdapter<MainIssueBean>adapter;
    private List<MainIssueBean>dataList;
    private String strDate;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_issue;
    }

    @Override
    protected void iniInjector() {
    }

    @Override
    protected void initViewsAndEvents(Bundle savedInstanceState) {
        initData();
        initViews();
    }

    private void initData() {
        dataList = new ArrayList<>();
        strDate = DateUtil.getCurrentTime("yyyy-MM");
        String year = strDate.split("-")[0];
        String month = strDate.split("-")[1];
        for (int index = 0; index < 24; index++) {
            MainIssueBean bean = new MainIssueBean();
            bean.setmIssueYear(year);
            bean.setmIssueMonth(month);
            if ((Integer.parseInt(month) - 1) > 0){
                month = String.valueOf((Integer.parseInt(month) - 1));
            }else {
                month = "12";
                year = String.valueOf(Integer.parseInt(year)-1);
            }
            dataList.add(bean);
        }
    }

    private void initViews() {

        adapter = new BaseRecyclerAdapter<MainIssueBean>(this,dataList) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_main_page_issue;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, MainIssueBean item) {
                holder.getTextView(R.id.item_main_page_month).setText(DateUtil.getMonth(item.getmIssueMonth()));
                holder.getTextView(R.id.item_main_page_year).setText(item.getmIssueYear());
            }
        };

        main_page_issue_rv.setItemAnimator(new SlideScaleInOutRightItemAnimator(main_page_issue_rv));
        main_page_issue_rv.setLayoutManager(new LinearLayoutManager(this));
        main_page_issue_rv.setHasFixedSize(true);
        main_page_issue_rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(adapter, main_page_issue_rv);
        main_page_issue_rv.setAdapter(animatorAdapter);

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                ToastUtils.showToast("点击了" + pos);
            }
        });
    }



    @OnClick(R.id.main_page_return)
    public void mainpageOnClick(){
        finish();
    }

    @Override
    public int getStatusColor() {
        return R.color.black;
    }
}
