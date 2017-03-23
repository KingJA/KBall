package com.kingja.kball.ui.other;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.ui.main.MainPagerAdapter;
import com.kingja.kball.ui.other.answer.OtherAnswerFragment;
import com.kingja.kball.ui.other.question.OtherQuestionFragment;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description：TODO
 * Create Time：2017/3/23 11:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherActivity extends BaseActivity {
    @BindView(R.id.tl_home)
    TabLayout tlHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;
    private List<String> mTabList = Arrays.asList("回答", "提问", "勋章墙");
    private Fragment mFragmentArr[] = new Fragment[3];
    private long otherAccountId;

    @Override
    public void initVariable() {
        otherAccountId = getIntent().getLongExtra("OTHER_ACCOUNT_ID", 0);
    }

    @Override
    public int getContentId() {
        return R.layout.activity_other;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {
        tlHome.setTabMode(TabLayout.MODE_FIXED);
        tlHome.addTab(tlHome.newTab().setText(mTabList.get(0)));
        tlHome.addTab(tlHome.newTab().setText(mTabList.get(1)));
        tlHome.addTab(tlHome.newTab().setText(mTabList.get(2)));

        mFragmentArr[0] = OtherAnswerFragment.newInstance(otherAccountId);
        mFragmentArr[1] = OtherAnswerFragment.newInstance(otherAccountId);
        mFragmentArr[2] = OtherAnswerFragment.newInstance(otherAccountId);

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mFragmentArr, mTabList);
        vpHome.setAdapter(mainPagerAdapter);
        vpHome.setOffscreenPageLimit(2);
        tlHome.setupWithViewPager(vpHome);
    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Context context, long otherAccountId) {
        Intent intent = new Intent(context, OtherActivity.class);
        intent.putExtra("OTHER_ACCOUNT_ID",otherAccountId);
        context.startActivity(intent);
    }
}
