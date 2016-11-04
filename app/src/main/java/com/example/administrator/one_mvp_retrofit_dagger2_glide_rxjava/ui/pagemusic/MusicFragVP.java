package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerViewHolder;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.DividerItemDecoration;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.TLog;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MusicFragVP extends BaseFragment implements MusicFragVPMvpView<MusicFragVPDataBean> {

    @Inject
    MusicFragVPresenter mPresenter;

    @Bind(R.id.music_page_frame)
    FrameLayout music_page_frame;
    @Bind(R.id.music_listView)
    RecyclerView music_listView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private MusicFragVPAdapter<MusicFragVPContentBean.DataBean0.DataBean>adapter;
    private List<MusicFragVPContentBean.DataBean0.DataBean> dataBeanList;
    private MusicFragVPDataBean dataBean;

    public MusicFragVP() {
    }

    public static MusicFragVP newInstance(String param1, String param2) {
        MusicFragVP fragment = new MusicFragVP();
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
        switchLayoutId();
    }

    private void switchLayoutId() {
        switch (mParam1){
            case Const.PAGE_MAIN_IS_FIRST:
                FragmentHelper.showLayoutId(music_page_frame,0);
                break;
            case Const.PAGE_MAIN_OTHER:
                FragmentHelper.showLayoutId(music_page_frame,1);
                initListener();
                mPresenter.getMusicDetail(mParam2);
                break;
            case Const.PAGE_MAIN_IS_LAST:
                FragmentHelper.showLayoutId(music_page_frame,2);
                break;
        }
    }

    private void initListener() {
        dataBeanList = new ArrayList<>();
        dataBeanList.add(new MusicFragVPContentBean.DataBean0.DataBean());//头视图占位置
        adapter = new MusicFragVPAdapter<MusicFragVPContentBean.DataBean0.DataBean>(getActivity(),dataBeanList) {
            @Override
            public int getItemLayoutId(int viewType) {
                if (viewType == Const.MUSIC_HEAD_ITEM){
                    return R.layout.item_music_first;
                }else {
                    return 0;
                }
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, MusicFragVPContentBean.DataBean0.DataBean item) {
                switch (getItemViewType(position)){
                    case Const.MUSIC_HEAD_ITEM:
                        holder.getTextView(R.id.text_music_pro_name).setText(dataBean.getData().getAuthor().getUser_name());
                        holder.getTextView(R.id.music_head_contry).setText(dataBean.getData().getAuthor().getDesc());
                        holder.getTextView(R.id.music_head_date_time).setText(dataBean.getData().getMaketime().substring(10));
                        holder.getTextView(R.id.text_music_title).setText(dataBean.getData().getTitle());

                        break;
                    case Const.MUSIC_CONTENT_ITEM:
                        break;
                }
            }
        };
        music_listView.setItemAnimator(new DefaultItemAnimator());
        music_listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        music_listView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        music_listView.setAdapter(adapter);
    }

    @Override
    protected void iniInjector() {
        ((BaseActivity)getActivity()).activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_music_frag_v;
    }

    @Override
    public void showData(MusicFragVPDataBean data) {
        dataBean = data;
        adapter.notifyDataSetChanged();
    }
}
