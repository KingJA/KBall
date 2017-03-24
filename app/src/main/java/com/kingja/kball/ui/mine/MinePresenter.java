package com.kingja.kball.ui.mine;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Account;

import javax.inject.Inject;

import okhttp3.MultipartBody;

/**
 * Description：TODO
 * Create Time：2016/10/12 15:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MinePresenter   implements MineContract.Presenter {
    private Api mApi;
    private MineContract.View mView;

    @Inject
    public MinePresenter(Api mApi) {
        this.mApi = mApi;
    }

    @Override
    public void attachView(@NonNull MineContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
    }

    @Override
    public void getUserInfo(String token) {
        mView.showLoading();
        mApi.getUserInfo(token).subscribe(new ResultObserver<Account>(mView) {
            @Override
            protected void onSuccess(Account account) {
                mView.shoUserInfo(account);
            }
        });
    }
}
