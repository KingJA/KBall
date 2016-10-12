package com.kingja.kball.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kingja.kball.base.BaseFragment;

/**
 * Description：TODO
 * Create Time：2016/10/715:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private BaseFragment[] fragments;

    public MainPagerAdapter(FragmentManager fm, BaseFragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
