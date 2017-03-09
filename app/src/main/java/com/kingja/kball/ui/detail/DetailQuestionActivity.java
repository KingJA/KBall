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
import com.kingja.kball.model.entiy.Question;

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
public class DetailQuestionActivity extends BaseActivity {
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
    @BindView(R.id.rv_detail)
    RecyclerView rvDetail;
    private Question mQuestion;

    @Inject
    ImageLoader imageLoader;

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
        tvDetailTitle.setText(mQuestion.getTitle());
        tvDetailContent.setText(mQuestion.getContent());
        imageLoader.loadImage(this, mQuestion.getAvatar(), 0, civDetailHead);
        tvDetailLevel.setText(mQuestion.getRankInfo().getTitle());
        List<String> imgsList = Arrays.asList(mQuestion.getImgUrls().split("#"));
        DetailImgAdapter mDetailImgAdapter = new DetailImgAdapter(this, imgsList);
        GridLayoutManager mgr = new GridLayoutManager(this, Constants.GRIDVIEW_COUNT);
        rvDetail.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL_LIST));
        rvDetail.setLayoutManager(mgr);
        rvDetail.setHasFixedSize(true);
        rvDetail.setAdapter(mDetailImgAdapter);

    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Context context, Question question) {
        Intent intent = new Intent(context, DetailQuestionActivity.class);
        intent.putExtra(Constants.EXTRA_QUESTION, question);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
