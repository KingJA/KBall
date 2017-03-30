package com.kingja.kball.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.kingja.kball.imgaeloader.IImageLoader;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.Question;
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
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

/**
 * Description：TODO
 * Create Time：2017/3/9 10:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailQuestionActivity extends BaseActivity implements DetailQuestionContract.View, AnswerAdapter.OnBestAnswerListener {
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
    @BindView(R.id.tv_detail_attention)
    TextView tvDetailAttention;
    @BindView(R.id.tv_detail_hasSolved)
    TextView tvDetailHasSolved;
    private Question mQuestion;

    @Inject
    IImageLoader IImageLoader;
    @Inject
    DetailQuestionPresenter detailQuestionPresenter;
    @Inject
    SharedPreferencesManager mSpManager;
    private int currentPage;
    private boolean hasMore;
    private AnswerAdapter mAnswerAdapter;
    private int isCollected;
    private int isAttentioned;
    private boolean scrollToAnswer;
    private int lastScrollY;
    private int bestPosition;


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
        tvDetailHasSolved.setVisibility(mQuestion.getSolved() == 1 ? View.VISIBLE : View.GONE);
        if (mSpManager.getAccountId() != mQuestion.getAccountId()) {
            tvDetailAttention.setVisibility(View.VISIBLE);
            isAttentioned = mQuestion.getIsAttentioned();
            tvDetailAttention.setText(isAttentioned == 1 ? "已关注" : "关注");
        }


        if (mQuestion.getAnswerCount() > 0) {
            tvDetailAnswerCount.setVisibility(View.VISIBLE);
            tvDetailAnswerCount.setText(mQuestion.getAnswerCount() + "");
        }
        tvDetailName.setText(mQuestion.getName());
        tvDetailLevel.setText(mQuestion.getRankInfo().getTitle());


        llSofa.setVisibility(mQuestion.getAnswerCount() > 0 ? View.GONE : View.VISIBLE);
        tvDetailTitle.setText(mQuestion.getTitle());
        tvDetailContent.setText(mQuestion.getContent());
        IImageLoader.loadImage(this, mQuestion.getAvatar(), 0, civDetailHead);
        tvDetailLevel.setText(mQuestion.getRankInfo().getTitle());
        isCollected = mQuestion.getIsCollected();
        ivDetailCollect.setColorFilter(isCollected == 1 ? getResources().getColor(R.color.red) : getResources().getColor(R.color.c));


        // Init answers RecyclerView,lv type
        List<String> imgsList = Arrays.asList(mQuestion.getImgUrls().split("#"));
        if (!TextUtils.isEmpty(mQuestion.getImgUrls())&&imgsList.size() > 0) {
            SingleImgAdapter mSingleImgAdapter = new SingleImgAdapter(this, imgsList);
            GridLayoutManager mgr = new GridLayoutManager(this, Constants.GRIDVIEW_IMG_COUNT);
            rvDetailImgs.addItemDecoration(new DividerItemDecoration(this,
                    DividerItemDecoration.HORIZONTAL_LIST));
            rvDetailImgs.setLayoutManager(mgr);
            rvDetailImgs.setHasFixedSize(true);
            rvDetailImgs.setAdapter(mSingleImgAdapter);
        }

        //init answer
        mAnswerAdapter = new AnswerAdapter(this, new ArrayList<Answer>(), mSpManager.getAccountId() == mQuestion.getAccountId() && mQuestion.getSolved() == 0);
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
                    detailQuestionPresenter.getAnswers(mSpManager.getToken(), mQuestion.getQuestionId(), currentPage * Constants.PAGE_SIZE, Constants.PAGE_SIZE);
                }
            }
        });

        mAnswerAdapter.setOnPraiseListener(new AnswerAdapter.OnPraiseListener() {
            @Override
            public void onPraise(long answerId, int position) {
                detailQuestionPresenter.praise(mSpManager.getToken(), answerId);
                praisedPosition = position;
            }
        });
        mAnswerAdapter.setOnBestAnswerListener(this);
    }

    private int praisedPosition;

    @Override
    protected void initNet() {
        detailQuestionPresenter.getAnswers(mSpManager.getToken(), mQuestion.getQuestionId(), currentPage * Constants.PAGE_SIZE, Constants.PAGE_SIZE);
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

    @OnClick({R.id.rl_detail_goAnswer, R.id.ll_detail_collect, R.id.ll_detail_share, R.id.tv_detail_answer, R.id.tv_detail_attention})
    public void onSwitch(View view) {

        switch (view.getId()) {
            case R.id.rl_detail_goAnswer:
                Log.e(TAG, "getScrollY: " + svDetailRoot.getScrollY());
                if (scrollAble()) {
                    if (svDetailRoot.getScrollY() < getSupportScrollY()) {//滚动切还没滚动到期望位置
                        lastScrollY = svDetailRoot.getScrollY();
                        svDetailRoot.smoothScrollTo(0, getSupportScrollY());

                    } else {
                        svDetailRoot.smoothScrollTo(0, lastScrollY);//滚动到上次位置
                    }
                }

                break;
            case R.id.ll_detail_collect:
                detailQuestionPresenter.collect(mSpManager.getToken(), mQuestion.getQuestionId(), isCollected);
                break;
            case R.id.ll_detail_share:
//                ShareDialog shareDialog = new ShareDialog(this);
//                shareDialog.show();
//                ShapePopup shapePopup = new ShapePopup(llDetailRoot,this);
//                shapePopup.showPopupWindowDown();

                SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
                sp.setText("测试分享的文本");
//        sp.setImagePath(“/mnt/sdcard/测试分享的图片.jpg”);
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                weibo.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        Log.e(TAG, "onComplete: ");
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {
                        Log.e(TAG, "onError: ");
                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Log.e(TAG, "onCancel: ");
                    }
                }); // 设置分享事件回调
// 执行图文分享
                weibo.share(sp);
                break;
            case R.id.tv_detail_answer:
                AnswerActivity.goActivity(this, mQuestion.getQuestionId());
                break;
            case R.id.tv_detail_attention:
                detailQuestionPresenter.attention(mSpManager.getToken(), mQuestion.getAccountId(), isAttentioned);
                break;

        }

    }

    private int getSupportScrollY() {
        int supportScrollY = 0;
        if (llDetailRoot.getMeasuredHeight() > svDetailRoot.getHeight()) {//滚动条里内容太超过滚动条显示区域
            if ((llDetailRoot.getMeasuredHeight() - svDetailRoot.getHeight()) > llDetailQuestion.getMeasuredHeight()) {//如果可以滚动长度超过问题显示的长度
                supportScrollY = llDetailQuestion.getMeasuredHeight();
            } else {
                supportScrollY = (llDetailRoot.getMeasuredHeight() - svDetailRoot.getHeight());
            }
        }
        return supportScrollY;
    }

    private boolean scrollAble() {
        return llDetailRoot.getMeasuredHeight() > svDetailRoot.getHeight();
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
        this.isCollected = isCollected;
        ToastUtil.showText(isCollected == 1 ? "收藏成功" : "取消收藏");
        ivDetailCollect.setColorFilter(isCollected == 1 ? getResources().getColor(R.color.red) : getResources().getColor(R.color.c));
    }

    @Override
    public void showAttention(int isAttentioned) {
        this.isAttentioned = isAttentioned;
        ToastUtil.showText(this.isAttentioned == 1 ? "关注成功" : "取消关注");
        tvDetailAttention.setText(this.isAttentioned == 1 ? "已关注" : "关注");
    }

    @Override
    public void showPraised() {
        mAnswerAdapter.setPaise(praisedPosition);
    }

    @Override
    public void showBest() {
        ToastUtil.showText("成功设置最佳答案");
        mAnswerAdapter.setBestQuestion(bestPosition);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshAnswerEvent(RefreshAnswerEvent event) {
        Log.e(TAG, "刷新: ");
        detailQuestionPresenter.getAnswers(mSpManager.getToken(), mQuestion.getQuestionId(), 0, Constants.PAGE_SIZE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBest(long questionId, long answerId, long answerAccountId, int bestAnswer) {
        this.bestPosition = bestAnswer;
        detailQuestionPresenter.setBestQuestion(mSpManager.getToken(), questionId, answerId, answerAccountId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
