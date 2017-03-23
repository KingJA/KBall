package com.kingja.kball.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.ui.mine.answer.MyAnswersActivity;
import com.kingja.kball.ui.mine.ask.MyQuestionsActivity;
import com.kingja.kball.ui.mine.attention.MyAttentionsActivity;
import com.kingja.kball.ui.mine.collection.MyCollectionsActivity;
import com.kingja.kball.ui.mine.fans.MyFansActivity;
import com.kingja.kball.ui.mygift.MyGiftActivity;
import com.kingja.kball.ui.other.OtherActivity;
import com.kingja.kball.util.GoUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description：TODO
 * Create Time：2016/10/715:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MineFragment extends BaseFragment {
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
    @BindView(R.id.ll_attention)
    LinearLayout llAttention;
    @BindView(R.id.rl_gift)
    RelativeLayout rlGift;
    @BindView(R.id.rl_footprint)
    RelativeLayout rlFootprint;
    @BindView(R.id.tv_attentionCount)
    TextView tvAttentionCount;
    @BindView(R.id.tv_fansCount)
    TextView tvFansCount;
    @BindView(R.id.ll_fans)
    LinearLayout llFans;
    @BindView(R.id.rl_mine_setting)
    RelativeLayout rlMineSetting;
    Unbinder unbinder;

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

    @OnClick({R.id.rl_gift, R.id.rl_question, R.id.rl_answer, R.id.ll_attention, R.id.rl_collection, R.id.ll_fans, R.id.rl_mine_setting})
    public void onSwitch(View view) {
        switch (view.getId()) {
            case R.id.rl_gift:
                GoUtil.goActivity(getActivity(), MyGiftActivity.class);
                break;
            case R.id.rl_question:
                GoUtil.goActivity(getActivity(), MyQuestionsActivity.class);
                break;
            case R.id.rl_answer:
                GoUtil.goActivity(getActivity(), MyAnswersActivity.class);
                break;
            case R.id.ll_attention:
                GoUtil.goActivity(getActivity(), MyAttentionsActivity.class);
                break;
            case R.id.rl_collection:
                GoUtil.goActivity(getActivity(), MyCollectionsActivity.class);
                break;
            case R.id.ll_fans:
                GoUtil.goActivity(getActivity(), MyFansActivity.class);
                break;
            case R.id.rl_mine_setting:
                GoUtil.goActivity(getActivity(), OtherActivity.class);
                break;

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
