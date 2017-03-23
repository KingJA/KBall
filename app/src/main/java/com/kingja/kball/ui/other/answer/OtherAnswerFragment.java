package com.kingja.kball.ui.other.answer;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.kingja.kball.R;
import com.kingja.kball.adapter.MyAnswerAdapter;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.MyAnswer;
import com.kingja.kball.util.SharedPreferencesManager;
import com.kingja.kball.util.ToastUtil;
import com.kingja.kball.widget.PullToBottomRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description：TODO
 * Create Time：2017/3/23 15:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherAnswerFragment extends BaseFragment implements PullToBottomRecyclerView.OnPullToBottomListener, OtherAnswerContract.View {
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.rv)
    PullToBottomRecyclerView rv;
    @Inject
    OtherAnswersPresenter mOtherAnswersPresenter;
    @Inject
    SharedPreferencesManager mSpManager;
    @BindView(R.id.pb)
    ProgressBar pb;
    private List<MyAnswer> myAnswers = new ArrayList<>();
    private MyAnswerAdapter mMyAnswerAdapter;
    private boolean hasMore;
    private int currentPage;
    private long otherAccountId;

    public static OtherAnswerFragment newInstance(long otherAccountId) {
        OtherAnswerFragment mOtherAnswerFragment=new OtherAnswerFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("OTHER_ACCOUNT_ID", otherAccountId);
        mOtherAnswerFragment.setArguments(bundle);
        return mOtherAnswerFragment;
    }
    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerOtherAnswerCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

    }

    @Override
    protected void initViewAndListener() {
        otherAccountId = getArguments().getLong("OTHER_ACCOUNT_ID", 0);
        mOtherAnswersPresenter.attachView(this);
        mMyAnswerAdapter = new MyAnswerAdapter(getActivity(), myAnswers);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(mMyAnswerAdapter);
        rv.setOnPullToBottomListener(this);
    }

    @Override
    protected void initNet() {
        mOtherAnswersPresenter.getOtherAnswers(mSpManager.getToken(), otherAccountId, 0, Constants.PAGE_SIZE);
    }

    @Override
    protected int getContentId() {
        return R.layout.single_pullbottom_rv_nosrl;
    }


    @Override
    public void onPullToBottom() {
        if (hasMore) {
            currentPage++;
            mOtherAnswersPresenter.getOtherAnswers(mSpManager.getToken(), 0, currentPage, Constants.PAGE_SIZE);
        } else {
            ToastUtil.showText("到底啦");
        }
    }

    @Override
    public void showOtherAnswers(List<MyAnswer> list, boolean hasMore) {
        this.hasMore = hasMore;
        llEmpty.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
        mMyAnswerAdapter.setData(list);
    }

    @Override
    public void showMoreOtherAnswers(List<MyAnswer> list, boolean hasMore) {
        this.hasMore = hasMore;
        mMyAnswerAdapter.addData(list);
    }

    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
    }

}
