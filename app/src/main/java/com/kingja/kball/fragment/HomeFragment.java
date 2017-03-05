package com.kingja.kball.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.ui.main.AllFragment;
import com.kingja.kball.ui.main.MainPagerAdapter;
import com.kingja.kball.ui.main.SolvedFragment;
import com.kingja.kball.ui.main.UnsolvedFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Description：TODO
 * Create Time：2016/10/715:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.tl_home)
    TabLayout tlHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    //

    private List<String> mTabList = Arrays.asList("全部", "已解决", "未解决");
    private Fragment mFragmentArr[]=new Fragment[3];

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {
        tlHome.setTabMode(TabLayout.MODE_FIXED);
        tlHome.addTab(tlHome.newTab().setText(mTabList.get(0)));
        tlHome.addTab(tlHome.newTab().setText(mTabList.get(1)));
        tlHome.addTab(tlHome.newTab().setText(mTabList.get(2)));

        mFragmentArr[0]=new AllFragment();
        mFragmentArr[1]=new SolvedFragment();
        mFragmentArr[2]=new UnsolvedFragment();

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getFragmentManager(), mFragmentArr,mTabList);
        vpHome.setAdapter(mainPagerAdapter);
        tlHome.setupWithViewPager(vpHome);
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_home;
    }

}
