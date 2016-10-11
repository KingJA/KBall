package com.kingja.kball.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kingja.kball.Api;
import com.kingja.kball.entiy.HttpResult;
import com.kingja.kball.entiy.Login;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoginPresenter implements LoginContract.Presenter {
    private Api mApi;
    private LoginContract.View mView;

    @Inject
    public LoginPresenter(Api mApi) {
        this.mApi = mApi;
    }

    @Override
    public void login(String userName, String password) {
        mView.showLoading();
        mApi.login(userName,password).subscribe(new Subscriber<HttpResult<Login>>() {
            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.hideLoading();
            }

            @Override
            public void onNext(HttpResult<Login> loginHttpResult) {
                mView.hideLoading();
                Log.e("LoginPresenter", "onNext: "+loginHttpResult.getMessage() );
            }
        });
    }

    @Override
    public void register(String userName, String password) {
        mView.showLoading();
        mApi.register(userName,password).subscribe(new Subscriber<HttpResult<Object>>() {
            @Override
            public void onCompleted() {
                mView.hideLoading();
            }

            @Override
            public void onError(Throwable e) {
                mView.hideLoading();
            }

            @Override
            public void onNext(HttpResult<Object> objectHttpResult) {
                mView.hideLoading();
                Log.e("LoginPresenter", "onNext: "+objectHttpResult.getMessage() );
            }
        });
    }

    @Override
    public void attachView(@NonNull LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }
}
