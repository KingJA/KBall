package com.kingja.kball.ui.detail;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Answer;

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
    public void getAnswers(String token, long questionId) {
        view.showLoading();
        api.getAnswers(token, questionId).subscribe(new ResultObserver<List<Answer>>(view) {
            @Override
            protected void onSuccess(List<Answer> list) {
                view.showAnswers(list);
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
