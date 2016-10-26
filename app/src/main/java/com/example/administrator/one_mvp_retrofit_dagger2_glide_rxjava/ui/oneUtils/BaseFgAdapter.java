package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.oneUtils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.base.baseMvp.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26.
 */

public class BaseFgAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> list;

    public BaseFgAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
