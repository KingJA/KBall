package com.kingja.kball.ui.mygift;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Gift;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/20 15:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyGiftPresenter implements MyGiftContract.Presenter {
    private Api api;
    private MyGiftContract.View view;

    @Inject
    public MyGiftPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getMyGifts(String token) {
        view.showLoading();
        api.getMyGifts(token).subscribe(new ResultObserver<List<Gift>>(view) {
            @Override
            protected void onSuccess(List<Gift> gifts) {
                view.showGifts(gifts);
            }
        });
    }

    @Override
    public void attachView(@NonNull MyGiftContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
