package com.kingja.kball.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.ui.main.AllQuestionFragment;
import com.kingja.kball.ui.main.QuestionsFragment;
import com.kingja.kball.ui.main.MainPagerAdapter;
import com.kingja.kball.ui.main.SolvedQuestionFragment;
import com.kingja.kball.ui.main.UnsolvedQuestionFragment;
import com.kingja.kball.ui.publish.PublishActivity;
import com.kingja.kball.util.GoUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.fab_main)
    FloatingActionButton fabMain;
    private List<String> mTabList = Arrays.asList("全部", "已解决", "未解决");
    private Fragment mFragmentArr[] = new Fragment[3];

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {
        tlHome.setTabMode(TabLayout.MODE_FIXED);
        tlHome.addTab(tlHome.newTab().setText(mTabList.get(0)));
        tlHome.addTab(tlHome.newTab().setText(mTabList.get(1)));
        tlHome.addTab(tlHome.newTab().setText(mTabList.get(2)));

        mFragmentArr[0] = AllQuestionFragment.newInstance(-1);
        mFragmentArr[1] = SolvedQuestionFragment.newInstance(1);
        mFragmentArr[2] = UnsolvedQuestionFragment.newInstance(0);

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getFragmentManager(), mFragmentArr, mTabList);
        vpHome.setAdapter(mainPagerAdapter);
        vpHome.setOffscreenPageLimit(2);
        tlHome.setupWithViewPager(vpHome);
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_home;
    }

    @OnClick({R.id.fab_main})
    public void onSwitch(View view) {
        GoUtil.goActivity(getActivity(), PublishActivity.class);
        getActivity().overridePendingTransition(R.anim.translate_up, R.anim.scale_small);

    }
}
