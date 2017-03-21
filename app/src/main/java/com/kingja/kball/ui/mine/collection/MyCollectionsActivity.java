package com.kingja.kball.ui.mine.collection;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.kingja.kball.R;
import com.kingja.kball.adapter.MyCollectionAdapter;
import com.kingja.kball.adapter.MyQuestionAdapter;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseTitleActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.ui.mine.ask.DaggerMyQuestionsCompnent;
import com.kingja.kball.ui.mine.ask.MyQuestionsContract;
import com.kingja.kball.ui.mine.ask.MyQuestionsPresenter;
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
public class MyCollectionsActivity extends BaseTitleActivity implements MyCollectionsContract.View,PullToBottomRecyclerView.OnPullToBottomListener,SwipeRefreshLayout.OnRefreshListener{
    @Inject
    MyCollectionsPresenter mMyCollectionsPresenter;
    @Inject
    SharedPreferencesManager mSpManager;
    private LinearLayout mLlRoot;
    private SwipeRefreshLayout mSrl;
    private PullToBottomRecyclerView mRv;
    private LinearLayout mLlEmpty;
    private List<Question> questions = new ArrayList<>();
    private MyCollectionAdapter mMyCollectionAdapter;
    private boolean hasMore;
    private int currentPage;

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerMyCollectionsCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "我的收藏";
    }

    @Override
    protected int getContentView() {
        return R.layout.single_pullbottom_rv;
    }

    @Override
    protected void initView() {
        mMyCollectionsPresenter.attachView(this);

        mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mRv = (PullToBottomRecyclerView) findViewById(R.id.rv);
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        mRv.setOnPullToBottomListener(this);
        mSrl.setOnRefreshListener(this);

        mMyCollectionAdapter = new MyCollectionAdapter(this, questions);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setHasFixedSize(true);
        mRv.setAdapter(mMyCollectionAdapter);
    }

    @Override
    protected void initNet() {
        mMyCollectionsPresenter.getMyCollections(mSpManager.getToken(),0, Constants.PAGE_SIZE);
    }

    @Override
    public void showMyCollections(List<Question> questions, boolean hasMore) {
        this.hasMore = hasMore;
        mLlEmpty.setVisibility(questions.size()>0? View.GONE:View.VISIBLE);
        mMyCollectionAdapter.setData(questions);
    }

    @Override
    public void showMoreMyCollections(List<Question> questions, boolean hasMore) {
        this.hasMore = hasMore;
        mMyCollectionAdapter.addData(questions);
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
            mMyCollectionsPresenter.getMyCollections(mSpManager.getToken(),currentPage*Constants.PAGE_SIZE, Constants.PAGE_SIZE);
        }else{
            ToastUtil.showText("到底啦");
        }
    }

    @Override
    public void onRefresh() {
        initNet();
    }
}
