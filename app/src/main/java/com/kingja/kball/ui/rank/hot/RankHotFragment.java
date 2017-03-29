package com.kingja.kball.ui.rank.hot;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kingja.kball.R;
import com.kingja.kball.adapter.HotQuestionAdapter;
import com.kingja.kball.adapter.MyCollectionAdapter;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.util.SharedPreferencesManager;
import com.kingja.kball.util.ToastUtil;
import com.kingja.kball.widget.PullToBottomRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description：TODO
 * Create Time：2016/10/715:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RankHotFragment extends BaseFragment implements RankHotContract.View,PullToBottomRecyclerView.OnPullToBottomListener,SwipeRefreshLayout.OnRefreshListener{
    @Inject
    RankHotPresenter mRankHotPresenter;
    @Inject
    SharedPreferencesManager mSpManager;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.rv)
    PullToBottomRecyclerView rv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    private List<Question> mHotQuestions = new ArrayList<>();
    private HotQuestionAdapter mHotQuestionAdapter;
    private boolean hasMore;
    private int currentPage;

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerRankHotCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initViewAndListener() {
        mRankHotPresenter.attachView(this);

        rv.setOnPullToBottomListener(this);
        srl.setOnRefreshListener(this);
         mHotQuestionAdapter = new HotQuestionAdapter(getContext(), mHotQuestions);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(mHotQuestionAdapter);
    }

    @Override
    protected void initNet() {
        mRankHotPresenter.getHotQuestions(mSpManager.getToken(), 0, Constants.PAGE_SIZE);
    }

    @Override
    protected int getContentId() {
        return R.layout.single_pullbottom_rv;
    }

    @Override
    public void showHotQuestions(List<Question> questions, boolean hasMore) {
        this.hasMore = hasMore;
        llEmpty.setVisibility(questions.size()>0? View.GONE:View.VISIBLE);
        mHotQuestionAdapter.setData(questions);
    }

    @Override
    public void showMoreHotQuestions(List<Question> questions, boolean hasMore) {
        this.hasMore = hasMore;
        mHotQuestionAdapter.addData(questions);
    }

    @Override
    public void showLoading() {
        srl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srl.setRefreshing(false);
    }


    @Override
    public void onPullToBottom() {
        if (srl.isRefreshing()) {
            return;
        }
        if (hasMore ) {
            currentPage++;
            mRankHotPresenter.getHotQuestions(mSpManager.getToken(),currentPage*Constants.PAGE_SIZE, Constants.PAGE_SIZE);
        }else{
            ToastUtil.showText("到底啦");
        }
    }

    @Override
    public void onRefresh() {
        currentPage=0;
        mRankHotPresenter.getHotQuestions(mSpManager.getToken(), 0, Constants.PAGE_SIZE);
    }
}
