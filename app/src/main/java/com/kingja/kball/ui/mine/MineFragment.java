package com.kingja.kball.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.ui.mygift.MyGiftActivity;
import com.kingja.kball.util.GoUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：TODO
 * Create Time：2016/10/715:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_pushMsg)
    ImageView ivPushMsg;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.iv_level)
    ImageView ivLevel;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.rl_question)
    RelativeLayout rlQuestion;
    @BindView(R.id.rl_answer)
    RelativeLayout rlAnswer;
    @BindView(R.id.rl_attention)
    RelativeLayout rlAttention;
    @BindView(R.id.rl_collection)
    RelativeLayout rlCollection;
    @BindView(R.id.rl_gift)
    RelativeLayout rlGift;
    @BindView(R.id.rl_footprint)
    RelativeLayout rlFootprint;

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_mine;
    }

    @OnClick({R.id.rl_gift})
    public void onSwitch(View view) {

        switch (view.getId()) {
            case R.id.rl_gift:
                GoUtil.goActivity(getActivity(), MyGiftActivity.class);
                break;

        }

    }
}
