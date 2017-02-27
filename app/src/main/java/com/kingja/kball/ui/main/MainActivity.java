package com.kingja.kball.ui.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.fragment.HomeFragment;
import com.kingja.kball.fragment.RankFragment;
import com.kingja.kball.fragment.TeamFragment;
import com.kingja.kball.ui.mine.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    static final int DEFAULT_PAGE_INDEX = 0;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.aiv_home)
    AppCompatImageView aivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.aiv_top)
    AppCompatImageView aivTop;
    @BindView(R.id.tv_top)
    TextView tvTop;
    @BindView(R.id.ll_top)
    LinearLayout llTop;
    @BindView(R.id.aiv_store)
    AppCompatImageView aivStore;
    @BindView(R.id.tv_store)
    TextView tvStore;
    @BindView(R.id.ll_store)
    LinearLayout llStore;
    @BindView(R.id.aiv_mine)
    AppCompatImageView aivMine;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;

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
        vpMain.setAdapter(mMainPagerAdapter);
        vpMain.setOffscreenPageLimit(mMainPagerAdapter.getCount() - 1);
        vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                setStatus(position);
            }
        });
    }

    @Override
    public void initVariable() {

    }

    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @OnClick({R.id.ll_home, R.id.ll_top, R.id.ll_store, R.id.ll_mine})
    public void onSwitch(View view) {

        switch (view.getId()) {
            case R.id.ll_home:
                setStatus(0);
                break;
            case R.id.ll_top:
                setStatus(1);
                break;
            case R.id.ll_store:
                setStatus(2);
                break;
            case R.id.ll_mine:
                setStatus(3);
                break;
        }

    }

    private void setStatus(int index) {
        resetBottom();
        switch (index) {
            case 0:
                aivHome.setColorFilter(getResources().getColor(R.color.red));
                tvHome.setTextColor(getResources().getColor(R.color.red));
                break;
            case 1:
                aivTop.setColorFilter(getResources().getColor(R.color.red));
                tvTop.setTextColor(getResources().getColor(R.color.red));
                break;
            case 2:
                aivStore.setColorFilter(getResources().getColor(R.color.red));
                tvStore.setTextColor(getResources().getColor(R.color.red));
                break;
            case 3:
                aivMine.setColorFilter(getResources().getColor(R.color.red));
                tvMine.setTextColor(getResources().getColor(R.color.red));
                break;
        }
        vpMain.setCurrentItem(index);
    }

    private void resetBottom() {
        aivHome.setColorFilter(getResources().getColor(R.color.font_3));
        aivTop.setColorFilter(getResources().getColor(R.color.font_3));
        aivStore.setColorFilter(getResources().getColor(R.color.font_3));
        aivMine.setColorFilter(getResources().getColor(R.color.font_3));
        tvHome.setTextColor(getResources().getColor(R.color.font_3));
        tvTop.setTextColor(getResources().getColor(R.color.font_3));
        tvStore.setTextColor(getResources().getColor(R.color.font_3));
        tvMine.setTextColor(getResources().getColor(R.color.font_3));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
