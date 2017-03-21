package com.kingja.kball.ui.mine.answer;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.kingja.kball.R;
import com.kingja.kball.adapter.BaseRvAdaper;
import com.kingja.kball.adapter.MyAnswerAdapter;
import com.kingja.kball.adapter.MyQuestionAdapter;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseTitleActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.MyAnswer;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.ui.detail.DetailQuestionActivity;
import com.kingja.kball.ui.mine.ask.DaggerMyQuestionsCompnent;
import com.kingja.kball.util.SharedPreferencesManager;
import com.kingja.kball.util.ToastUtil;
import com.kingja.kball.widget.PullToBottomRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/21 10:46
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyAnswersActivity extends BaseTitleActivity implements MyAnswersContract.View,PullToBottomRecyclerView.OnPullToBottomListener,SwipeRefreshLayout.OnRefreshListener{
    @Inject
    MyAnswersPresenter mMyAnswersPresenter;
    @Inject
    SharedPreferencesManager mSpManager;
    private LinearLayout mLlRoot;
    private SwipeRefreshLayout mSrl;
    private PullToBottomRecyclerView mRv;
    private LinearLayout mLlEmpty;
    private List<MyAnswer> myAnswers = new ArrayList<>();
    private MyAnswerAdapter mMyAnswerAdapter;
    private boolean hasMore;
    private int currentPage;

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerMyAnswersCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "我的提问";
    }

    @Override
    protected int getContentView() {
        return R.layout.single_pullbottom_rv;
    }

    @Override
    protected void initView() {
        mMyAnswersPresenter.attachView(this);

        mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mRv = (PullToBottomRecyclerView) findViewById(R.id.rv);
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        mRv.setOnPullToBottomListener(this);
        mSrl.setOnRefreshListener(this);


        mMyAnswerAdapter = new MyAnswerAdapter(this, myAnswers);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setHasFixedSize(true);
        mRv.setAdapter(mMyAnswerAdapter);
        mMyAnswerAdapter.setOnItemClickListener(new BaseRvAdaper.OnItemClickListener<MyAnswer>() {
            @Override
            public void onItemClick(MyAnswer myAnswer, int position) {
                DetailQuestionActivity.goActivity(MyAnswersActivity.this,myAnswer);
            }
        });
    }

    @Override
    protected void initNet() {
        mMyAnswersPresenter.getMyAnswers(mSpManager.getToken(),0, Constants.PAGE_SIZE);
    }

    @Override
    public void showMyAnswers(List<MyAnswer> questions, boolean hasMore) {
        this.hasMore = hasMore;
        mLlEmpty.setVisibility(questions.size()>0? View.GONE:View.VISIBLE);
        mMyAnswerAdapter.setData(questions);
    }

    @Override
    public void showMoreMyAnswers(List<MyAnswer> questions, boolean hasMore) {
        this.hasMore = hasMore;
        mMyAnswerAdapter.addData(questions);
    }

    @Override
    public void showLoading() {
        mSrl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSrl.setRefreshing(false);
    }

    @Override
    public void onPullToBottom() {
        if (mSrl.isRefreshing()) {
            return;
        }
        if (hasMore ) {
            currentPage++;
            mMyAnswersPresenter.getMyAnswers(mSpManager.getToken(),currentPage*Constants.PAGE_SIZE, Constants.PAGE_SIZE);
        }else{
            ToastUtil.showText("到底啦");
        }
    }

    @Override
    public void onRefresh() {
        initNet();
    }
}
