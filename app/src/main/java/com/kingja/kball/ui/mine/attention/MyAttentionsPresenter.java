package com.kingja.kball.ui.mine.attention;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.MyAttention;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/21 11:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyAttentionsPresenter implements MyAttentionsContract.Presenter {
    private Api api;
    private MyAttentionsContract.View view;

    @Inject
    public MyAttentionsPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getMyAttentions(String token, final int pageIndex, final int pageSize) {
        view.showLoading();
        api.getMyAttentions(token, pageIndex, pageSize).subscribe(new ResultObserver<List<MyAttention>>(view) {
            @Override
            protected void onSuccess(List<MyAttention> questions) {
                if (pageIndex == 0) {
                    view.showMyAttentions(questions, questions.size() == pageSize);
                } else {
                    view.showMoreMyAttentions(questions, questions.size() == pageSize);
                }
            }
        });
    }

    @Override
    public void attachView(@NonNull MyAttentionsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
