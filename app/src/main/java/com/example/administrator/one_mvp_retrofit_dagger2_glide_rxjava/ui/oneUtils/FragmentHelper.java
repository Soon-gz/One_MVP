package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils;



import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemain.MainPageFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.MovieFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic.MusicFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading.ReadingFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Shuwen on 2016/10/25.
 * Fragment相关处理逻辑
 */

public class FragmentHelper {

    private static List<BaseFragment>fragments;
    private static FragmentManager mFragmentManager;

    public static void initFragments(FragmentManager fragmentManager){
        mFragmentManager = fragmentManager;
        fragments =  new ArrayList<>();
        fragments.add(new MainPageFragment());//首页
        fragments.add(new ReadingFragment()); //阅读
        fragments.add(new MusicFragment());   //音乐
        fragments.add(new MovieFragment());   //电影
    }

    //初始化
    public static void initMainFragments(){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        for (int index = 0; index < fragments.size(); index++) {
            fragmentTransaction.add(R.id.main_layout,fragments.get(index));
            if (index == Const.MAIN_PAGE_FRAGMENT){
                fragmentTransaction.show(fragments.get(Const.MAIN_PAGE_FRAGMENT));
            }else {
                fragmentTransaction.hide(fragments.get(index));
            }
        }
        fragmentTransaction.commit();
    }

    //显示相应的Fragment
    public static void showOneFragment(int mainPage){
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        for (int index = 0; index < fragments.size(); index++) {
            if (index == mainPage){
                fragmentTransaction.show(fragments.get(mainPage));
            }else {
                fragmentTransaction.hide(fragments.get(index));
            }
        }
        fragmentTransaction.commit();
    }
    //显示布局
    public static void showLayoutId(FrameLayout frameLayout,int layoutId){
        for (int index = 0; index < frameLayout.getChildCount(); index++) {
            if (index == layoutId){
                frameLayout.getChildAt(index).setVisibility(View.VISIBLE);
            }else {
                frameLayout.getChildAt(index).setVisibility(View.GONE);
            }
        }
    }
    //设置圆形头像
    public static void setCircleImage(CircleImageView circleImage, String imageUrl, Activity activity){
        Glide.with(activity).load(imageUrl).dontAnimate().thumbnail(0.1f).placeholder(R.drawable.head).into(circleImage);
    }
    //设置图片
    public static void setImage(ImageView circleImage, String imageUrl, Activity activity){
        Glide.with(activity).load(imageUrl).dontAnimate().thumbnail(0.1f).placeholder(R.drawable.head).into(circleImage);
    }
}
