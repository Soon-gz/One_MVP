package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.R;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemain.MainPageFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemovie.MovieFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagemusic.MusicFragment;
import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.pagereading.ReadingFragment;

import java.util.ArrayList;
import java.util.List;

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
}
