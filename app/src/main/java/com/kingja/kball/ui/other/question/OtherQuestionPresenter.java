package com.kingja.kball.ui.other.question;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.ui.mine.ask.MyQuestionsContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/21 11:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherQuestionPresenter implements OtherQuestionContract.Presenter {
    private Api api;
    private OtherQuestionContract.View view;

    @Inject
    public OtherQuestionPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getOtherQuestions(String token, long otherAccountId, final int pageIndex, final int pageSize) {
        view.showLoading();
        api.getOtherQuestions(token, otherAccountId, pageIndex, pageSize).subscribe(new ResultObserver<List<Question>>(view) {
            @Override
            protected void onSuccess(List<Question> questions) {
                if (pageIndex == 0) {
                    view.showOtherQuestions(questions, questions.size() == pageSize);
                } else {
                    view.showMoreOtherQuestions(questions, questions.size() == pageSize);
                }
            }
        });
    }

    @Override
    public void attachView(@NonNull OtherQuestionContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
