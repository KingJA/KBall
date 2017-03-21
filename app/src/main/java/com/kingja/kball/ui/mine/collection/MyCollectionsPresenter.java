package com.kingja.kball.ui.mine.collection;

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
public class MyCollectionsPresenter implements MyCollectionsContract.Presenter {
    private Api api;
    private MyCollectionsContract.View view;

    @Inject
    public MyCollectionsPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getMyCollections(String token, final int pageIndex, final int pageSize) {
        view.showLoading();
        api.getMyCollections(token, pageIndex, pageSize).subscribe(new ResultObserver<List<Question>>(view) {
            @Override
            protected void onSuccess(List<Question> questions) {
                if (pageIndex == 0) {
                    view.showMyCollections(questions, questions.size() == pageSize);
                } else {
                    view.showMoreMyCollections(questions, questions.size() == pageSize);
                }
            }
        });
    }

    @Override
    public void attachView(@NonNull MyCollectionsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
