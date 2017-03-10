package com.kingja.kball.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.adapter.DetailImgAdapter;
import com.kingja.kball.adapter.DividerItemDecoration;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.imgaeloader.ImageLoader;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.util.SharedPreferencesManager;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private Question mQuestion;

    @Inject
    ImageLoader imageLoader;
    @Inject
    DetailQuestionPresenter detailQuestionPresenter;
    @Inject
    SharedPreferencesManager sharedPreferencesManager;


    @Override
    public void initVariable() {
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
        detailQuestionPresenter.attachView(this);
        tvDetailTitle.setText(mQuestion.getTitle());
        tvDetailContent.setText(mQuestion.getContent());
        imageLoader.loadImage(this, mQuestion.getAvatar(), 0, civDetailHead);
        tvDetailLevel.setText(mQuestion.getRankInfo().getTitle());
        List<String> imgsList = Arrays.asList(mQuestion.getImgUrls().split("#"));
        DetailImgAdapter mDetailImgAdapter = new DetailImgAdapter(this, imgsList);
        GridLayoutManager mgr = new GridLayoutManager(this, Constants.GRIDVIEW_COUNT);
        rvDetailImgs.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL_LIST));
        rvDetailImgs.setLayoutManager(mgr);
        rvDetailImgs.setHasFixedSize(true);
        rvDetailImgs.setAdapter(mDetailImgAdapter);

    }

    @Override
    protected void initNet() {
        detailQuestionPresenter.getAnswers(sharedPreferencesManager.getToken(), mQuestion.getQuestionId());
    }

    public static void goActivity(Context context, Question question) {
        Intent intent = new Intent(context, DetailQuestionActivity.class);
        intent.putExtra(Constants.EXTRA_QUESTION, question);
        context.startActivity(intent);
    }


    @Override
    public void showAnswers(List<Answer> list) {
    }

    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

}
