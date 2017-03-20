package com.kingja.kball.ui.store;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Gift;
import com.kingja.kball.model.entiy.HttpResult;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2017/3/18 16:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StorePresenter implements StoreContract.Presenter {
    private Api api;
    private StoreContract.View view;

    @Inject
    public StorePresenter(Api api) {
        this.api = api;
    }

    @Override
    public void getGifts(String token) {
        api.getGifts(token).subscribe(new ResultObserver<List<Gift>>(view) {
            @Override
            protected void onSuccess(List<Gift> gifts) {
                view.setGift(gifts);
            }
        });
    }

    @Override
    public void buyGift(String token,long giftId, int count, int cost) {
        api.buyGift(token,giftId, count, cost).subscribe(new ResultObserver<Object>(view) {
            @Override
            protected void onSuccess(Object o) {
                view.showBuyGiftSuccess();
            }
        });
    }

    @Override
    public void attachView(@NonNull StoreContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
