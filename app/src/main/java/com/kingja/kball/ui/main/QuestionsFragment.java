package com.kingja.kball.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.kingja.kball.R;
import com.kingja.kball.adapter.BaseRvAdaper;
import com.kingja.kball.adapter.DividerItemDecoration;
import com.kingja.kball.adapter.QuestionAdapter;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.Api;
import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.ui.detail.DetailQuestionActivity;
import com.kingja.kball.util.AppUtil;
import com.kingja.kball.util.SharedPreferencesManager;
import com.kingja.kball.util.ToastUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

/**
 * Description：TODO
 * Create Time：2016/10/715:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class QuestionsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    @Inject
    Api api;
    @Inject
    SharedPreferencesManager mSharedPreferencesManager;

    private List<Question> questionList = new ArrayList<>();
    private QuestionAdapter mQuestionAdapter;
    private int solved;
    private int pageIndex;
    private boolean hasMore;

    public static QuestionsFragment newInstance(int solved) {
        QuestionsFragment mQuestionsFragment = new QuestionsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("SOLVED", solved);
        mQuestionsFragment.setArguments(bundle);
        return mQuestionsFragment;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerQuestionsCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initViewAndListener() {
        mQuestionAdapter = new QuestionAdapter(getActivity(), questionList);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        rv.setHasFixedSize(true);
        rv.setAdapter(mQuestionAdapter);
        rv.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        if (isBottom(rv)) {
                            if (srl.isRefreshing()) {
                                return;
                            }
                            if (hasMore) {
                                loadNet(++pageIndex);
                            } else {
                                ToastUtil.showText("已经没有更多数据");
                            }
                        }
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        srl.setOnRefreshListener(this);
        srl.setColorSchemeResources(R.color.black);
        srl.setProgressViewOffset(false, 0, AppUtil.dp2px(24));
        solved = getArguments().getInt("SOLVED", -1);
        mQuestionAdapter.setOnItemClickListener(new BaseRvAdaper.OnItemClickListener() {
            @Override
            public void onItemClick(Object object, int position) {
                Question question= (Question) object;
                DetailQuestionActivity.goActivity(getActivity(),question);
            }
        });
    }

    @Override
    protected void initNet() {
        loadNet(pageIndex);
    }

    private void loadNet(final int pageIndex) {
        srl.setRefreshing(true);
        api.getQuestions(mSharedPreferencesManager.getToken(), pageIndex * Constants.PAGE_SIZE, Constants.PAGE_SIZE, solved).subscribe(new Consumer<HttpResult<List<Question>>>() {
            @Override
            public void accept(HttpResult<List<Question>> result) throws Exception {
                srl.setRefreshing(false);
                if (result.getResultCode() == 0) {
                    questionList = result.getResultData();
                    llEmpty.setVisibility(questionList.size() > 0 ? View.GONE : View.VISIBLE);
                    if (pageIndex == 0) {
                        mQuestionAdapter.reset();
                    }
                    hasMore = questionList.size() == Constants.PAGE_SIZE;
                    mQuestionAdapter.addData(questionList);
                } else {
                    Logger.e(result.getResultText());
                }
            }
        });
    }

    @Override
    protected int getContentId() {
        return R.layout.single_rv;
    }

    private boolean isBottom(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = recyclerView.getScrollState();
        if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == recyclerView.SCROLL_STATE_IDLE) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onRefresh() {
        pageIndex = 0;
        loadNet(pageIndex);
        ToastUtil.showText("刷新数据");
    }
}
