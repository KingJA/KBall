package com.kingja.kball.ui.store;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.kingja.kball.R;
import com.kingja.kball.adapter.GiftAdapter;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.Gift;
import com.kingja.kball.ui.detail.DaggerDetailQuestionCompnent;
import com.kingja.kball.util.AppUtil;
import com.kingja.kball.util.SharedPreferencesManager;
import com.kingja.kball.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description：TODO
 * Create Time：2016/10/715:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StoreFragment extends BaseFragment implements StoreContract.View ,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.iv_store_banner)
    ImageView ivStoreBanner;
    @BindView(R.id.rv_store)
    RecyclerView rvStore;
    @BindView(R.id.srl_store)
    SwipeRefreshLayout srlStore;
    @Inject
    StorePresenter mStorePresenter;
    @Inject
    SharedPreferencesManager mSharedPreferencesManager;
    private GiftAdapter mGiftAdapter;
    private List<Gift> mGifts = new ArrayList<>();

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerStoreCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initViewAndListener() {
        mStorePresenter.attachView(this);

        mGiftAdapter = new GiftAdapter(getActivity(), mGifts);
        rvStore.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvStore.setHasFixedSize(true);
        rvStore.setAdapter(mGiftAdapter);
        srlStore.setColorSchemeResources(R.color.black);
        srlStore.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
        srlStore.setOnRefreshListener(this);
    }

    @Override
    protected void initNet() {
        mStorePresenter.getGifts(mSharedPreferencesManager.getToken());
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_store;
    }

    @Override
    public void setGift(List<Gift> list) {
        Log.e(TAG, "setGift: "+list.size() );
        mGiftAdapter.setData(list);
    }

    @Override
    public void showLoading() {
        srlStore.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srlStore.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        srlStore.setRefreshing(false);
    }
}
