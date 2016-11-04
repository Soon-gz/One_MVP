package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic;

import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseUtils.BaseRecyclerAdapter;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.Const;

import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public abstract class MusicFragVPAdapter<T> extends BaseRecyclerAdapter<T> {

    public MusicFragVPAdapter(Context ctx, List<T> list) {
        super(ctx, list);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return Const.MUSIC_HEAD_ITEM;
        }else {
            return Const.MUSIC_CONTENT_ITEM;
        }
    }
}
