package com.kingja.kball.ui.other;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.OtherUser;
import com.kingja.kball.model.entiy.SingleInt;
import com.kingja.kball.ui.detail.DetailQuestionContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/10 15:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherPresenter implements OtherContract.Presenter {
    private Api api;
    private OtherContract.View view;

    @Inject
    public OtherPresenter(Api api) {
        this.api = api;
    }


    @Override
    public void attachView(@NonNull OtherContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getOtherUserInfo(String token, long otherAccountId) {
        view.showLoading();
        api.getOtherUserInfo(token,otherAccountId).subscribe(new ResultObserver<OtherUser>(view) {
            @Override
            protected void onSuccess(OtherUser otherUser) {
                view.showOtherUserInfo(otherUser);
            }
        });
    }

    @Override
    public void attention(String token, long otherAccountId, int ifAttention) {
        view.showLoading();
        api.attention(token,otherAccountId,ifAttention).subscribe(new ResultObserver<SingleInt>(view) {
            @Override
            protected void onSuccess(SingleInt singleInt) {
                view.showAttention(singleInt.getResultInt());
            }
        });
    }
}
