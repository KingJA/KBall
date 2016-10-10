package com.kingja.kball.login;

import android.support.annotation.NonNull;

import com.kingja.kball.Api;
import com.kingja.kball.entiy.HttpResult;
import com.kingja.kball.entiy.Login;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LoginPresenter implements LoginContract.Presenter {
    private Api api;
    private LoginContract.View view;

    @Inject
    public LoginPresenter(Api api) {
        this.api = api;
    }

    @Override
    public void login(String userName, String password) {
        api.login(userName,password).subscribe(new Action1<HttpResult<Login>>() {
            @Override
            public void call(HttpResult<Login> loginHttpResult) {

            }

        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    @Override
    public void register(String userName, String password) {

    }

    @Override
    public void attachView(@NonNull LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {

    }
}
