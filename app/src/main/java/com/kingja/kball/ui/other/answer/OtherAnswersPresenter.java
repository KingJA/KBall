package com.kingja.kball.ui.other.answer;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.MyAnswer;
import com.kingja.kball.ui.mine.answer.MyAnswersContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/21 11:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherAnswersPresenter implements OtherAnswerContract.Presenter {
    private Api api;
    private OtherAnswerContract.View view;

    @Inject
    public OtherAnswersPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getOtherAnswers(String token,long otherAccountId, final int pageIndex, final int pageSize) {
        view.showLoading();
        api.getOtherAnswers(token,otherAccountId, pageIndex, pageSize).subscribe(new ResultObserver<List<MyAnswer>>(view) {
            @Override
            protected void onSuccess(List<MyAnswer> questions) {
                if (pageIndex == 0) {
                    view.showOtherAnswers(questions, questions.size() == pageSize);
                } else {
                    view.showMoreOtherAnswers(questions, questions.size() == pageSize);
                }
            }
        });
    }


    @Override
    public void attachView(@NonNull OtherAnswerContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
