package com.kingja.kball.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.kingja.kball.R;
import com.kingja.kball.fragment.HomeFragment;
import com.kingja.kball.ui.rank.RankFragment;
import com.kingja.kball.ui.store.StoreFragment;
import com.kingja.kball.ui.mine.MineFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2017/3/2 15:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FragmentUtil {
    private static Map<Integer, Fragment> fragmentMap = new HashMap<>();

    public static Fragment switchFragment(FragmentManager fragmentManager, Fragment currentFragment, Fragment newFragment) {
        FragmentTransaction mTransaction = fragmentManager.beginTransaction();
        if (!newFragment.isAdded()) {
            mTransaction.hide(currentFragment).add(R.id.fl_main, newFragment).commit();
        } else {
            mTransaction.hide(currentFragment).show(newFragment).commit();
        }
        return newFragment;
    }


    public static Fragment getFragment(int position) {
        Fragment fragment = fragmentMap.get(position);
        if (fragment != null) {
            return fragment;
        } else {
            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new RankFragment();
                    break;
                case 2:
                    fragment = new StoreFragment();
                    break;
                case 3:
                    fragment = new MineFragment();
                    break;
            }
            fragmentMap.put(position, fragment);
            return fragment;
        }
    }
}
