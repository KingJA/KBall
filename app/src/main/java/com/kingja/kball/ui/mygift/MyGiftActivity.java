package com.kingja.kball.ui.mygift;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.adapter.MyGiftAdapter;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseTitleActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.Gift;
import com.kingja.kball.util.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/20 14:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyGiftActivity extends BaseTitleActivity implements MyGiftContract.View ,SwipeRefreshLayout.OnRefreshListener{

    private TextView mTvMyGiftTotleCost;
    private TextView mTvMyGiftRank;
    private LinearLayout mLlRoot;
    private SwipeRefreshLayout mSrl;
    private RecyclerView mRv;
    private LinearLayout mLlEmpty;
    private List<Gift> gifts = new ArrayList<>();
    @Inject
    MyGiftPresenter mMyGiftPresenter;
    @Inject
    SharedPreferencesManager spManager;
    private MyGiftAdapter mMyGiftAdapter;

    @Override
    public void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerMyGiftCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "我的礼物";
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_my_gift;
    }

    @Override
    protected void initView() {
        mMyGiftPresenter.attachView(this);
        mSrl = (SwipeRefreshLayout) findViewById(R.id.srl);
        mSrl.setOnRefreshListener(this);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mTvMyGiftTotleCost = (TextView) findViewById(R.id.tv_myGift_totleCost);
        mTvMyGiftRank = (TextView) findViewById(R.id.tv_myGift_rank);
        mLlEmpty = (LinearLayout) findViewById(R.id.ll_empty);

        mMyGiftAdapter = new MyGiftAdapter(this, gifts);
        GridLayoutManager mgr = new GridLayoutManager(this, Constants.GRIDVIEW_GIFT_COUNT);
        mRv.setLayoutManager(mgr);
        mRv.setHasFixedSize(true);
        mRv.setAdapter(mMyGiftAdapter);
    }


    @Override
    protected void initNet() {
        mMyGiftPresenter.getMyGifts(spManager.getToken());
    }

    @Override
    public void showGifts(List<Gift> list) {
        setTotalCost(list);
        mMyGiftAdapter.setData(list);
    }

    private void setTotalCost(List<Gift> list) {
        int totalCost=0;
        for (Gift gift : list) {
            totalCost+=gift.getGiftCost()*gift.getGiftCount();
        }
        mTvMyGiftTotleCost.setText(totalCost+"");
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
    public void onRefresh() {
        mSrl.setRefreshing(false);
    }
}
