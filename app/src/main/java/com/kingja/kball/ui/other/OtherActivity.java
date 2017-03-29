package com.kingja.kball.ui.other;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.imgaeloader.IImageLoader;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.OtherUser;
import com.kingja.kball.ui.main.MainPagerAdapter;
import com.kingja.kball.ui.other.answer.OtherAnswerFragment;
import com.kingja.kball.ui.other.question.OtherQuestionFragment;
import com.kingja.kball.util.SharedPreferencesManager;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description：TODO
 * Create Time：2017/3/23 11:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherActivity extends BaseActivity implements OtherContract.View {
    @BindView(R.id.tl_home)
    TabLayout tlHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;
    @BindView(R.id.iv_other_head)
    ImageView ivOtherHead;
    @BindView(R.id.tv_other_level)
    TextView tvOtherLevel;
    @BindView(R.id.iv_level)
    ImageView ivLevel;
    @BindView(R.id.tv_other_userName)
    TextView tvOtherUserName;
    @BindView(R.id.tv_other_attention)
    TextView tvOtherAttention;
    @BindView(R.id.tv_other_attentionCount)
    TextView tvOtherAttentionCount;
    @BindView(R.id.ll_attention)
    LinearLayout llAttention;
    @BindView(R.id.tv_other_fansCount)
    TextView tvOtherFansCount;
    @BindView(R.id.ll_fans)
    LinearLayout llFans;
    @BindView(R.id.tv_other_des)
    TextView tvOtherDes;
    private Fragment mFragmentArr[] = new Fragment[2];
    private long otherAccountId;
    @Inject
    IImageLoader imageLoader;
    @Inject
    OtherPresenter mOtherPresenter;
    @Inject
    SharedPreferencesManager mSpManager;
    private int isAttention;
    private String[] items;

    @Override
    public void initVariable() {
        otherAccountId = getIntent().getLongExtra(Constants.EXTRA_OTHER_ACCOUNT_ID, 0);
        items = getResources().getStringArray(R.array.other_user_items);
    }

    @Override
    public int getContentId() {
        return R.layout.activity_other;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerOtherCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initViewAndListener() {
        mOtherPresenter.attachView(this);
        tlHome.setTabMode(TabLayout.MODE_FIXED);

        tlHome.addTab(tlHome.newTab().setText(items[0]));
        tlHome.addTab(tlHome.newTab().setText(items[1]));

        mFragmentArr[0] = OtherAnswerFragment.newInstance(otherAccountId);
        mFragmentArr[1] = OtherQuestionFragment.newInstance(otherAccountId);

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mFragmentArr, items);
        vpHome.setAdapter(mainPagerAdapter);
        vpHome.setOffscreenPageLimit(2);
        tlHome.setupWithViewPager(vpHome);
    }

    @Override
    protected void initNet() {
        mOtherPresenter.getOtherUserInfo(mSpManager.getToken(), otherAccountId);
    }

    public static void goActivity(Context context, long otherAccountId) {
        Intent intent = new Intent(context, OtherActivity.class);
        intent.putExtra(Constants.EXTRA_OTHER_ACCOUNT_ID, otherAccountId);
        context.startActivity(intent);
    }

    @Override
    public void showOtherUserInfo(OtherUser otherUser) {
        tvOtherAttentionCount.setText(String.valueOf(otherUser.getAttentionCount()) );
        tvOtherFansCount.setText(String.valueOf(otherUser.getFansCount()));
        tvOtherDes.setText(otherUser.getDes());
        tvOtherUserName.setText(otherUser.getName());
        tvOtherLevel.setText(otherUser.getRankInfo().getTitle());
        imageLoader.loadImage(this, otherUser.getAvatar(), R.drawable.head, ivOtherHead);
        isAttention = otherUser.getIsAttention();
        tvOtherAttention.setText(isAttention == 1 ?  R.string.attentioned : R.string.attention);
    }

    @Override
    public void showAttention(int isAttentioned) {
        this.isAttention = isAttentioned;
        tvOtherAttention.setText(isAttention == 1 ? R.string.attentioned : R.string.attention);
        Integer attentionCount = Integer.valueOf(tvOtherFansCount.getText().toString().trim());
        tvOtherFansCount.setText(isAttention == 1?String.valueOf(++attentionCount):String.valueOf(--attentionCount));
    }

    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

    @OnClick({R.id.tv_other_attention})
    public void onSwitch(View view) {
        switch (view.getId()) {
            case R.id.tv_other_attention:
                mOtherPresenter.attention(mSpManager.getToken(),otherAccountId,isAttention);
                break;
        }
    }
}
