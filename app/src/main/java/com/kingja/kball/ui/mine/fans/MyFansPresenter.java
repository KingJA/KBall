package com.kingja.kball.ui.mine.fans;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.MyAttention;
import com.kingja.kball.ui.mine.attention.MyAttentionsContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/21 11:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyFansPresenter implements MyFansContract.Presenter {
    private Api api;
    private MyFansContract.View view;

    @Inject
    public MyFansPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getMyFans(String token, final int pageIndex, final int pageSize) {
        view.showLoading();
        api.getMyFans(token, pageIndex, pageSize).subscribe(new ResultObserver<List<MyAttention>>(view) {
            @Override
            protected void onSuccess(List<MyAttention> questions) {
                if (pageIndex == 0) {
                    view.showMyFans(questions, questions.size() == pageSize);
                } else {
                    view.showMoreMyFans(questions, questions.size() == pageSize);
                }
            }
        });
    }

    @Override
    public void attachView(@NonNull MyFansContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
