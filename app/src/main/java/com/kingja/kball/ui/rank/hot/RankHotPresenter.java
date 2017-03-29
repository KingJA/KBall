package com.kingja.kball.ui.rank.hot;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.ui.mine.collection.MyCollectionsContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/21 11:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RankHotPresenter implements RankHotContract.Presenter {
    private Api api;
    private RankHotContract.View view;

    @Inject
    public RankHotPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getHotQuestions(String token, final int pageIndex, final int pageSize) {
        view.showLoading();
        api.getHotQuestions(token, pageIndex, pageSize).subscribe(new ResultObserver<List<Question>>(view) {
            @Override
            protected void onSuccess(List<Question> questions) {
                if (pageIndex == 0) {
                    view.showHotQuestions(questions, questions.size() == pageSize);
                } else {
                    view.showMoreHotQuestions(questions, questions.size() == pageSize);
                }
            }
        });
    }

    @Override
    public void attachView(@NonNull RankHotContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
