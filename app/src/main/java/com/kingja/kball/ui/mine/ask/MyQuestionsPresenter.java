package com.kingja.kball.ui.mine.ask;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.ui.detail.DetailQuestionContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/21 11:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyQuestionsPresenter implements MyQuestionsContract.Presenter {
    private Api api;
    private MyQuestionsContract.View view;

    @Inject
    public MyQuestionsPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getMyQuestions(String token, final int pageIndex, final int pageSize) {
        view.showLoading();
        api.getMyQuestions(token, pageIndex, pageSize).subscribe(new ResultObserver<List<Question>>(view) {
            @Override
            protected void onSuccess(List<Question> questions) {
                if (pageIndex == 0) {
                    view.showMyQuestions(questions, questions.size() == pageSize);
                } else {
                    view.showMoreMyQuestions(questions, questions.size() == pageSize);
                }
            }
        });
    }

    @Override
    public void attachView(@NonNull MyQuestionsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
