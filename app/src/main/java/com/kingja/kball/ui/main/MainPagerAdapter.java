package com.kingja.kball.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/715:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] fragments;
    private List<String> titles;

    public MainPagerAdapter(FragmentManager fm, Fragment[] fragments, List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
