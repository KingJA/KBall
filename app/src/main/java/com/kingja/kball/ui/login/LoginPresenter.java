package com.kingja.kball.ui.login;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.model.entiy.Login;
import com.kingja.kball.util.ToastUtil;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoginPresenter implements LoginContract.Presenter {
    private Api mApi;
    private LoginContract.View mView;
    private Subscription mRegisterSubscription;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    private Subscription mLoginSubscription;

    @Inject
    public LoginPresenter(Api mApi) {
        this.mApi = mApi;
    }

    @Override
    public void login(String userName, String password) {
        mView.showLoading();
        mLoginSubscription = mApi.login(userName, password).subscribe(new Subscriber<HttpResult<Login>>() {
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
                Logger.e(loginHttpResult.getMessage());
                if (loginHttpResult.getCode() == 0) {
                    mView.onLoginSuccess();
                } else {
                    ToastUtil.showText(loginHttpResult.getMessage());
                }

            }
        });
        mSubscriptions.add(mLoginSubscription);
    }

    @Override
    public void register(String userName, String password) {
        mView.showLoading();
        mRegisterSubscription = mApi.register(userName, password).subscribe(new Subscriber<HttpResult<Object>>() {
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
                Logger.e(objectHttpResult.getMessage());
                mView.hideLoading();
                if (objectHttpResult.getCode() == 0) {
                    mView.onRegisterSuccess();
                } else {
                    ToastUtil.showText(objectHttpResult.getMessage());
                }
            }
        });
        mSubscriptions.add(mRegisterSubscription);
    }

    @Override
    public void attachView(@NonNull LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        if (mSubscriptions != null) {
            mSubscriptions.clear();
        }
    }
}
