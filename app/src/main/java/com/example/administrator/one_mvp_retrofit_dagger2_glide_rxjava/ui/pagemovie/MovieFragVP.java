package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.BaseEventBus;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseActivity;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.IBaseView;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerViewHolder;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.recycleritemanimator.AlphaAnimatorAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.recycleritemanimator.SlideScaleInOutRightItemAnimator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import de.greenrobot.event.EventBus;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieFragVP#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieFragVP extends BaseFragment implements IBaseView<MovieFragVPBean> {

    @Inject
    MovieFragVPresenter mPresenter;
    @Bind(R.id.fragment_movie_framelayout)
    FrameLayout fragment_movie_framelayout;
    @Bind(R.id.movie_home_recyclerview)
    RecyclerView movie_home_recyclerview;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private BaseRecyclerAdapter<MovieFragVPBean.DataBean>adapter;
    private List<MovieFragVPBean.DataBean>dataList;
    private String movieId = "0";
    private Typeface typeface;
    private boolean isbOttom = false;


    public MovieFragVP() {
    }

    public static MovieFragVP newInstance(String param1, String param2) {
        MovieFragVP fragment = new MovieFragVP();
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
        switchFrameLayout();
    }

    private void switchFrameLayout() {
        switch (mParam1){
            case Const.PAGE_MAIN_IS_FIRST:
                FragmentHelper.showLayoutId(fragment_movie_framelayout,0);
                break;
            case Const.PAGE_MAIN_OTHER:
                FragmentHelper.showLayoutId(fragment_movie_framelayout,1);
                initDataListener();
                mPresenter.getMovieList(movieId,true);
                break;
        }
    }

    @Override
    public void onEventMainThread(BaseEventBus event) {
        super.onEventMainThread(event);
        if (Const.REFRESH.equals(event.msg)){
            movieId = "0";
            dataList.clear();
            mPresenter.getMovieList(movieId,true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initDataListener() {

        EventBus.getDefault().register(this);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "Ruthie.ttf");
        dataList = new ArrayList<>();
        adapter = new BaseRecyclerAdapter<MovieFragVPBean.DataBean>(getActivity(),dataList) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item_movie;
            }

            @Override
            public void bindData(BaseRecyclerViewHolder holder, int position, MovieFragVPBean.DataBean item) {
                Glide.with(getActivity()).load(item.getCover()).placeholder(R.drawable.movie_placeholder_0).thumbnail(0.1f).dontAnimate().into(holder.getImageView(R.id.item_movie_cover));
                holder.getTextView(R.id.item_movie_score).setTypeface(typeface);
                holder.getTextView(R.id.item_movie_score).setText(item.getScore());
            }
        };
        movie_home_recyclerview.setHasFixedSize(true);
        movie_home_recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        movie_home_recyclerview.setItemAnimator(new SlideScaleInOutRightItemAnimator(movie_home_recyclerview));
        AlphaAnimatorAdapter animatorAdapter = new AlphaAnimatorAdapter(adapter,movie_home_recyclerview);
        movie_home_recyclerview.setAdapter(animatorAdapter);
        movie_home_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isbOttom && newState == RecyclerView.SCROLL_STATE_IDLE){
                    movieId = dataList.get(dataList.size() - 1).getId();
                    mPresenter.getMovieList(movieId,false);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                isbOttom = ((layoutManager.findLastVisibleItemPosition() + 1) == adapter.getItemCount());
            }
        });
    }

    @Override
    protected void iniInjector() {
        ((BaseActivity)getActivity()).activityComponent().inject(this);
        mPresenter.attachView(this);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_movie_frag_v;
    }

    @Override
    public void showData(MovieFragVPBean data) {
        dataList.addAll(data.getData());
        adapter.notifyDataSetChanged();
    }
}
