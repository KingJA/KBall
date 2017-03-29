package com.kingja.kball.ui.rank;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.ui.main.AllQuestionFragment;
import com.kingja.kball.ui.main.MainPagerAdapter;
import com.kingja.kball.ui.main.SolvedQuestionFragment;
import com.kingja.kball.ui.mine.MinePresenter;
import com.kingja.kball.ui.rank.hot.RankHotFragment;
import com.kingja.kball.ui.rank.hot.RankHotPresenter;
import com.kingja.kball.util.SharedPreferencesManager;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description：TODO
 * Create Time：2016/10/715:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RankFragment extends BaseFragment {
    @BindView(R.id.tl_rank)
    TabLayout tlRank;
    @BindView(R.id.vp_rank)
    ViewPager vpRank;
    private Fragment mFragmentArr[] = new Fragment[2];
    private String[] items;



    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {
        items = getResources().getStringArray(R.array.rank_items);
        tlRank.setTabMode(TabLayout.MODE_FIXED);
        tlRank.addTab(tlRank.newTab().setText(items[0]));
        tlRank.addTab(tlRank.newTab().setText(items[1]));

        mFragmentArr[0] = new RankHotFragment();
        mFragmentArr[1] = new RankHotFragment();

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getFragmentManager(), mFragmentArr, items);
        vpRank.setAdapter(mainPagerAdapter);
        tlRank.setupWithViewPager(vpRank);
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_rank;
    }

}
