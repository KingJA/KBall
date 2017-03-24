package com.kingja.kball.ui.other.question;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.kingja.kball.R;
import com.kingja.kball.adapter.BaseRvAdaper;
import com.kingja.kball.adapter.MyQuestionAdapter;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.ui.detail.DetailQuestionActivity;
import com.kingja.kball.ui.mine.ask.MyQuestionsPresenter;
import com.kingja.kball.ui.other.answer.OtherAnswerFragment;
import com.kingja.kball.util.SharedPreferencesManager;
import com.kingja.kball.util.SpManager;
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
public class OtherQuestionFragment extends BaseFragment implements BaseView,OtherQuestionContract.View,PullToBottomRecyclerView.OnPullToBottomListener,BaseRvAdaper.OnItemClickListener<Question>{
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.rv)
    PullToBottomRecyclerView rv;
    @BindView(R.id.pb)
    ProgressBar pb;
    @Inject
    SharedPreferencesManager mSpManager;
    @Inject
    OtherQuestionPresenter mOtherQuestionPresenter;
    private boolean hasMore;
    private int currentPage;
    private List<Question> questions = new ArrayList<>();
    private MyQuestionAdapter mOtherQuestionAdapter;
    private long otherAccountId;

    public static OtherQuestionFragment newInstance(long otherAccountId) {
        OtherQuestionFragment mOtherQuestionFragment=new OtherQuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("OTHER_ACCOUNT_ID", otherAccountId);
        mOtherQuestionFragment.setArguments(bundle);
        return mOtherQuestionFragment;
    }
    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerOtherQuestionCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

    }

    @Override
    protected void initViewAndListener() {
        otherAccountId = getArguments().getLong("OTHER_ACCOUNT_ID", 0);
        mOtherQuestionPresenter.attachView(this);
        mOtherQuestionAdapter = new MyQuestionAdapter(getActivity(), questions);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(mOtherQuestionAdapter);
        rv.setOnPullToBottomListener(this);
        mOtherQuestionAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initNet() {
        mOtherQuestionPresenter.getOtherQuestions(mSpManager.getToken(),otherAccountId,0,Constants.PAGE_SIZE);
    }

    @Override
    protected int getContentId() {
        return R.layout.single_pullbottom_rv_nosrl;
    }


    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
    }

    @Override
    public void showOtherQuestions(List<Question> questions, boolean hasMore) {
        this.hasMore = hasMore;
        llEmpty.setVisibility(questions.size()>0? View.GONE:View.VISIBLE);
        mOtherQuestionAdapter.setData(questions);
    }

    @Override
    public void showMoreOtherQuestions(List<Question> questions, boolean hasMore) {
        this.hasMore = hasMore;
        mOtherQuestionAdapter.addData(questions);
    }

    @Override
    public void onPullToBottom() {
        if (hasMore) {
            currentPage++;
            mOtherQuestionPresenter.getOtherQuestions(mSpManager.getToken(),otherAccountId,currentPage,Constants.PAGE_SIZE);
        } else {
            ToastUtil.showText("到底啦");
        }
    }

    @Override
    public void onItemClick(Question question, int position) {
        DetailQuestionActivity.goActivity(getActivity(), question);
    }
}
