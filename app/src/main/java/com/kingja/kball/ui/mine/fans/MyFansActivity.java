package com.kingja.kball.ui.mine.fans;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

import com.kingja.kball.R;
import com.kingja.kball.adapter.MyAttentionAdapter;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseTitleActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.MyAttention;
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
public class MyFansActivity extends BaseTitleActivity implements MyFansContract.View,PullToBottomRecyclerView.OnPullToBottomListener,SwipeRefreshLayout.OnRefreshListener{
    @Inject
    MyFansPresenter mMyFansPresenter;
    @Inject
    SharedPreferencesManager mSpManager;
    private LinearLayout mLlRoot;
    private SwipeRefreshLayout mSrl;
    private PullToBottomRecyclerView mRv;
    private LinearLayout mLlEmpty;
    private List<MyAttention> myAttentions = new ArrayList<>();
    private MyAttentionAdapter mMyAttentionAdapter;
    private boolean hasMore;
    private int currentPage;

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerMyFansCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "我的粉丝";
    }

    @Override
    protected int getContentView() {
        return R.layout.single_pullbottom_rv;
    }

    @Override
    protected void initView() {
        mMyFansPresenter.attachView(this);

        mLlRoot = (LinearLayout) findViewById(R.id.ll_root);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mRv = (PullToBottomRecyclerView) findViewById(R.id.rv);
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);
        mRv.setOnPullToBottomListener(this);
        mSrl.setOnRefreshListener(this);

        mMyAttentionAdapter = new MyAttentionAdapter(this, myAttentions);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mRv.setHasFixedSize(true);
        mRv.setAdapter(mMyAttentionAdapter);
    }

    @Override
    protected void initNet() {
        mMyFansPresenter.getMyFans(mSpManager.getToken(),0, Constants.PAGE_SIZE);
    }

    @Override
    public void showMyFans(List<MyAttention> questions, boolean hasMore) {
        this.hasMore = hasMore;
        mLlEmpty.setVisibility(questions.size()>0? View.GONE:View.VISIBLE);
        mMyAttentionAdapter.setData(questions);
    }

    @Override
    public void showMoreMyFans(List<MyAttention> questions, boolean hasMore) {
        this.hasMore = hasMore;
        mMyAttentionAdapter.addData(questions);
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
            mMyFansPresenter.getMyFans(mSpManager.getToken(),currentPage*Constants.PAGE_SIZE, Constants.PAGE_SIZE);
        }else{
            ToastUtil.showText("到底啦");
        }
    }

    @Override
    public void onRefresh() {
        initNet();
    }
}
