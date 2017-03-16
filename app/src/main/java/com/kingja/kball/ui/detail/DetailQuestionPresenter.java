package com.kingja.kball.ui.detail;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.SingleInt;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/10 15:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailQuestionPresenter implements DetailQuestionContract.Presenter {
    private Api api;
    private DetailQuestionContract.View view;

    @Inject
    public DetailQuestionPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getAnswers(String token, long questionId, final int pageIndex, final int pageSize) {
        view.showLoading();
        api.getAnswers(token, questionId, pageIndex, pageSize).subscribe(new ResultObserver<List<Answer>>(view) {
            @Override
            protected void onSuccess(List<Answer> list) {
                if (pageIndex == 0) {
                    view.showAnswers(list, list.size() == pageSize);
                } else {
                    view.showMoreAnswers(list, list.size() == pageSize);
                }
            }
        });
    }

    @Override
    public void collect(String token, long questionId,int ifCollect) {
        view.showLoading();
        api.collect(token, questionId,ifCollect).subscribe(new ResultObserver<SingleInt>(view) {
            @Override
            protected void onSuccess(SingleInt singleInt) {
                view.showCollected(singleInt.getResultInt());
            }
        });
    }


    @Override
    public void attachView(@NonNull DetailQuestionContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
