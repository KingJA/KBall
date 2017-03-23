package com.kingja.kball.ui.other.question;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.kingja.kball.R;
import com.kingja.kball.adapter.MyQuestionAdapter;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.util.SharedPreferencesManager;
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
public class OtherQuestionFragment extends BaseFragment implements BaseView{
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    @BindView(R.id.rv)
    PullToBottomRecyclerView rv;
    @BindView(R.id.pb)
    ProgressBar pb;
    @Inject
    Api api;
    @Inject
    SharedPreferencesManager mSpManager;

    private List<Question> questions = new ArrayList<>();
    private MyQuestionAdapter mMyQuestionAdapter;

    @Override
    protected void initComponent(AppComponent appComponent) {
//        DaggerOtherQuestionCompnent.builder()
//                .appComponent(appComponent)
//                .build()
//                .inject(this);

    }

    @Override
    protected void initViewAndListener() {
        mMyQuestionAdapter = new MyQuestionAdapter(getActivity(), questions);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(mMyQuestionAdapter);
    }

    @Override
    protected void initNet() {
        api.getMyQuestions(mSpManager.getToken(),0, Constants.PAGE_SIZE).subscribe(new ResultObserver<List<Question>>(this) {
            @Override
            protected void onSuccess(List<Question> questions) {
                mMyQuestionAdapter.setData(questions);
            }
        });
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
}
