package com.kingja.kball;

import android.support.v4.view.ViewPager;
import android.widget.RadioButton;

import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.fragment.BaseFragment;
import com.kingja.kball.fragment.HomeFragment;
import com.kingja.kball.fragment.MineFragment;
import com.kingja.kball.fragment.RankFragment;
import com.kingja.kball.fragment.TeamFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnCheckedChanged;

public class MainActivity extends BaseActivity {
    static final int DEFAULT_PAGE_INDEX = 0;
    @BindView(R.id.vp_main_content)
    ViewPager vpMainContent;
    @BindViews({R.id.radio_main_home, R.id.radio_main_rank, R.id.radio_main_team, R.id.radio_main_mine})
    List<RadioButton> radioButtons;

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViewAndListener() {
        BaseFragment[] fragments = new BaseFragment[4];
        fragments[0] = new HomeFragment();
        fragments[1] = new RankFragment();
        fragments[2] = new TeamFragment();
        fragments[3] = new MineFragment();
        MainPagerAdapter mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        vpMainContent.setAdapter(mMainPagerAdapter);
        vpMainContent.setOffscreenPageLimit(mMainPagerAdapter.getCount() - 1);
        vpMainContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Empty
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Empty
            }

            @Override
            public void onPageSelected(int position) {
                radioButtons.get(position).setChecked(true);
            }
        });
        radioButtons.get(DEFAULT_PAGE_INDEX).setChecked(true);
    }

    @Override
    public void initVariable() {

    }

    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @OnCheckedChanged({R.id.radio_main_home, R.id.radio_main_rank, R.id.radio_main_team, R.id.radio_main_mine})
    public void onRadioButtonChecked(RadioButton button, boolean isChecked) {
        if (isChecked) {
            vpMainContent.setCurrentItem(radioButtons.indexOf(button));
        }
    }
}
