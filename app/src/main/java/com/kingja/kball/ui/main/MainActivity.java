package com.kingja.kball.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.fragment.HomeFragment;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.util.FragmentUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
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
    private Fragment mCurrentFragment;
    private int nCurrentPosition = -1;
    private int mSelectedPosition = -1;


    @Override
    protected void initViewAndListener() {
        mCurrentFragment = FragmentUtil.getFragment(0);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, mCurrentFragment).commit();
    }

    @Override
    protected void initNet() {

    }

    @Override
    public void initVariable() {

    }

    @Override
    public int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @OnClick({R.id.ll_home, R.id.ll_top, R.id.ll_store, R.id.ll_mine})
    public void onSwitch(View view) {

        switch (view.getId()) {
            case R.id.ll_home:
                selectTab(0);
                break;
            case R.id.ll_top:
                selectTab(1);
                break;
            case R.id.ll_store:
                selectTab(2);
                break;
            case R.id.ll_mine:
                selectTab(3);
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

    private void selectTab(int position) {
        mSelectedPosition = position;
        if (mSelectedPosition == nCurrentPosition) {
            return;
        }
        mCurrentFragment = FragmentUtil.switchFragment(this, mCurrentFragment, FragmentUtil.getFragment(position));
        nCurrentPosition = mSelectedPosition;
        setStatus(position);
    }

    //防止Fragment重生重叠
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    }

}
