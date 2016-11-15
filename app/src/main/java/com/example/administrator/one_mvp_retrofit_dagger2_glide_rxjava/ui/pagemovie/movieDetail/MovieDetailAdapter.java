package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public abstract class MovieDetailAdapter<T> extends BaseRecyclerAdapter<T> {

    public MovieDetailAdapter(Context ctx, List<T> list) {
        super(ctx, list);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return Const.MOVIE_HEAD_ITEM;
        }else {
            return Const.Movie_CONTENT_ITEM;
        }
    }
}
