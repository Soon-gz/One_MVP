package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.movieDetail;

import android.content.Context;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils.FragmentHelper;

/**
 * Created by Administrator on 2016/11/16.
 * 为MovieDetail UI逻辑服务
 */

public class MovieDetailUI {

    public static void initRadioGroup(RadioGroup radioGroup, final FrameLayout frameLayout){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.item_movie_detail_grossbtn:
                        FragmentHelper.showLayoutId(frameLayout,0);
                        break;
                    case R.id.item_movie_detail_stillbtn:
                        FragmentHelper.showLayoutId(frameLayout,1);
                        break;
                    case R.id.item_movie_detail_plotbtn:
                        FragmentHelper.showLayoutId(frameLayout,2);
                        break;
                }
            }
        });
    }

    public static void initHorizontalScollView(HorizontalScrollView horizontalScrollView, MovieDetailDataBean movieDetailDataBean, Context context){
        if (movieDetailDataBean.getData().getPhoto().size() > 0){
            LinearLayout linearLayout = new LinearLayout(context);
            for (int index = 0; index < movieDetailDataBean.getData().getPhoto().size(); index++) {
                ImageView imageView = new ImageView(context);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                imageView.setPadding(10,10,10,10);
                TextView textView = new TextView(context);
                textView.setLayoutParams(new ViewGroup.LayoutParams(10, ViewGroup.LayoutParams.MATCH_PARENT));
                textView.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
                Glide.with(context).load(movieDetailDataBean.getData().getPhoto().get(index)).placeholder(R.drawable.default_indi_bg).dontAnimate().thumbnail(0.1f).into(imageView);
                linearLayout.addView(imageView);
                linearLayout.addView(textView);
            }
            horizontalScrollView.addView(linearLayout);
        }
    }
}
