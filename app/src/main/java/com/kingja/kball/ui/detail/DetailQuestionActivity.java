package com.kingja.kball.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.adapter.AnswerAdapter;
import com.kingja.kball.adapter.DividerItemDecoration;
import com.kingja.kball.adapter.SingleImgAdapter;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.imgaeloader.ImageLoader;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.model.entiy.SingleInt;
import com.kingja.kball.rxbus.RefreshAnswerEvent;
import com.kingja.kball.ui.answer.AnswerActivity;
import com.kingja.kball.util.SharedPreferencesManager;
import com.kingja.kball.util.ToastUtil;
import com.kingja.kball.widget.ListeneredScrollView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：TODO
 * Create Time：2017/3/9 10:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailQuestionActivity extends BaseActivity implements DetailQuestionContract.View {
    @BindView(R.id.tv_detail_title)
    TextView tvDetailTitle;
    @BindView(R.id.civ_detail_head)
    ImageView civDetailHead;
    @BindView(R.id.tv_detail_level)
    TextView tvDetailLevel;
    @BindView(R.id.iv_detail_level)
    ImageView ivDetailLevel;
    @BindView(R.id.tv_detail_name)
    TextView tvDetailName;
    @BindView(R.id.tv_detail_content)
    TextView tvDetailContent;
    @BindView(R.id.rv_detail_imgs)
    RecyclerView rvDetailImgs;
    @BindView(R.id.tv_detail_answerCount)
    TextView tvDetailAnswerCount;
    @BindView(R.id.rv_detail_answers)
    RecyclerView rvDetailAnswers;
    @BindView(R.id.tv_detail_answer)
    TextView tvDetailAnswer;
    @BindView(R.id.ll_detail_collect)
    LinearLayout llDetailCollect;
    @BindView(R.id.ll_detail_share)
    LinearLayout llDetailShare;
    @BindView(R.id.ll_detail_root)
    LinearLayout llDetailRoot;
    @BindView(R.id.ll_detail_question)
    LinearLayout llDetailQuestion;
    @BindView(R.id.sv_detail_root)
    ListeneredScrollView svDetailRoot;
    @BindView(R.id.rl_detail_goAnswer)
    RelativeLayout rlDetailGoAnswer;

    @BindView(R.id.ll_sofa)
    LinearLayout llSofa;
    @BindView(R.id.iv_detail_collect)
    ImageView ivDetailCollect;
    private Question mQuestion;

    @Inject
    ImageLoader imageLoader;
    @Inject
    DetailQuestionPresenter detailQuestionPresenter;
    @Inject
    SharedPreferencesManager sharedPreferencesManager;
    private int currentPage;
    private boolean hasMore;
    private AnswerAdapter mAnswerAdapter;
    private int isCollected;


    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        mQuestion = (Question) getIntent().getSerializableExtra(Constants.EXTRA_QUESTION);
    }

    @Override
    public int getContentId() {
        return R.layout.activity_detail_question;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerDetailQuestionCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }


    @Override
    protected void initViewAndListener() {
        // Init answer imgs RecyclerView,gv type
        detailQuestionPresenter.attachView(this);
        if (mQuestion.getAnswerCount() > 0) {
            tvDetailAnswerCount.setVisibility(View.VISIBLE);
            tvDetailAnswerCount.setText(mQuestion.getAnswerCount() + "");
        }
        llSofa.setVisibility(mQuestion.getAnswerCount() > 0 ? View.GONE : View.VISIBLE);
        tvDetailTitle.setText(mQuestion.getTitle());
        tvDetailContent.setText(mQuestion.getContent());
        imageLoader.loadImage(this, mQuestion.getAvatar(), 0, civDetailHead);
        tvDetailLevel.setText(mQuestion.getRankInfo().getTitle());
        isCollected=mQuestion.getIsCollected();
        ivDetailCollect.setColorFilter(isCollected ==1?getResources().getColor(R.color.red):getResources().getColor(R.color.c));


        // Init answers RecyclerView,lv type
        List<String> imgsList = Arrays.asList(mQuestion.getImgUrls().split("#"));
        SingleImgAdapter mSingleImgAdapter = new SingleImgAdapter(this, imgsList);
        GridLayoutManager mgr = new GridLayoutManager(this, Constants.GRIDVIEW_COUNT);
        rvDetailImgs.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL_LIST));
        rvDetailImgs.setLayoutManager(mgr);
        rvDetailImgs.setHasFixedSize(true);
        rvDetailImgs.setAdapter(mSingleImgAdapter);
        //init answer
        mAnswerAdapter = new AnswerAdapter(this, new ArrayList<Answer>());
        rvDetailAnswers.setLayoutManager(new LinearLayoutManager(this));
        rvDetailAnswers.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        rvDetailAnswers.setHasFixedSize(true);
        rvDetailAnswers.setAdapter(mAnswerAdapter);

        svDetailRoot.setOnScrollBottomListener(new ListeneredScrollView.OnScrollBottomListener() {
            @Override
            public void onScrollBottom() {

                if (!getProgressShow() && hasMore) {
                    Log.e(TAG, "onScrollBottom: " + "加载更多");
                    currentPage++;
                    detailQuestionPresenter.getAnswers(sharedPreferencesManager.getToken(), mQuestion.getQuestionId(), currentPage * Constants.PAGE_SIZE, Constants.PAGE_SIZE);
                }
            }
        });

        mAnswerAdapter.setOnPraiseListener(new AnswerAdapter.OnPraiseListener() {
            @Override
            public void onPraise(ImageView iv, TextView tv) {

            }
        });
    }

    @Override
    protected void initNet() {
        detailQuestionPresenter.getAnswers(sharedPreferencesManager.getToken(), mQuestion.getQuestionId(), currentPage * Constants.PAGE_SIZE, Constants.PAGE_SIZE);
    }

    public static void goActivity(Context context, Question question) {
        Intent intent = new Intent(context, DetailQuestionActivity.class);
        intent.putExtra(Constants.EXTRA_QUESTION, question);
        context.startActivity(intent);
    }


    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

    @OnClick({R.id.rl_detail_goAnswer, R.id.ll_detail_collect, R.id.ll_detail_share, R.id.tv_detail_answer})
    public void onSwitch(View view) {

        switch (view.getId()) {
            case R.id.rl_detail_goAnswer:

                if (llDetailRoot.getMeasuredHeight() > svDetailRoot.getHeight()) {
                    if ((llDetailRoot.getMeasuredHeight() - svDetailRoot.getHeight()) > llDetailQuestion.getMeasuredHeight()) {
                        svDetailRoot.scrollTo(0, llDetailQuestion.getMeasuredHeight());
                    } else {
                        svDetailRoot.scrollTo(0, (llDetailRoot.getMeasuredHeight() - svDetailRoot.getHeight()));
                    }

                } else {

                }

                break;
            case R.id.ll_detail_collect:
                detailQuestionPresenter.collect(sharedPreferencesManager.getToken(),mQuestion.getQuestionId(),isCollected);
                break;
            case R.id.ll_detail_share:
                break;
            case R.id.tv_detail_answer:
                AnswerActivity.goActivity(this, mQuestion.getQuestionId());
                break;
        }

    }


    @Override
    public void showAnswers(List<Answer> list, boolean hasMore) {
        this.hasMore = hasMore;
        mAnswerAdapter.setData(list);

    }

    @Override
    public void showMoreAnswers(List<Answer> list, boolean hasMore) {
        this.hasMore = hasMore;
        mAnswerAdapter.addData(list);
    }

    @Override
    public void showCollected(int isCollected) {
        this.isCollected=isCollected;
       ToastUtil.showText(isCollected==1?"收藏成功":"取消收藏");
        ivDetailCollect.setColorFilter(isCollected ==1?getResources().getColor(R.color.red):getResources().getColor(R.color.c));
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshAnswerEvent(RefreshAnswerEvent event) {
        Log.e(TAG, "刷新: ");
        detailQuestionPresenter.getAnswers(sharedPreferencesManager.getToken(), mQuestion.getQuestionId(), 0, Constants.PAGE_SIZE);
    }

}
