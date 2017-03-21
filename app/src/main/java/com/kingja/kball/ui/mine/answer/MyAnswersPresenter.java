package com.kingja.kball.ui.mine.answer;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.MyAnswer;
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
public class MyAnswersPresenter implements MyAnswersContract.Presenter {
    private Api api;
    private MyAnswersContract.View view;

    @Inject
    public MyAnswersPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getMyAnswers(String token, final int pageIndex, final int pageSize) {
        view.showLoading();
        api.getMyAnswers(token, pageIndex, pageSize).subscribe(new ResultObserver<List<MyAnswer>>(view) {
            @Override
            protected void onSuccess(List<MyAnswer> questions) {
                if (pageIndex == 0) {
                    view.showMyAnswers(questions, questions.size() == pageSize);
                } else {
                    view.showMoreMyAnswers(questions, questions.size() == pageSize);
                }
            }
        });
    }


    @Override
    public void attachView(@NonNull MyAnswersContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
