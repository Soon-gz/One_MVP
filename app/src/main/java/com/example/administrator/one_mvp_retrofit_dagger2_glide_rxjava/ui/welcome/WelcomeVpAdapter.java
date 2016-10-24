package com.example.administrator.one_mvp_retrofit_dagger2_glide_rxjava.ui.welcome;

import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Shuwen on 2016/10/18.
 */

public class WelcomeVpAdapter extends FragmentStatePagerAdapter {
    private List<WelcomeFragment>fragments;

    public WelcomeVpAdapter(FragmentManager fm, List<WelcomeFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
