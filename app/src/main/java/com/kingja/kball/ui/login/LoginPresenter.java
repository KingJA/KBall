package com.kingja.kball.ui.login;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultObserver;
import com.kingja.kball.model.entiy.Account;
import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.model.entiy.Login;
import com.kingja.kball.util.ToastUtil;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


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
        mApi.login(userName, password).subscribe(new ResultObserver<Account>(mView) {
            @Override
            protected void onSuccess(Account account) {
                mView.onLoginSuccess(account);
            }
        });
    }

    @Override
    public void register(String userName, String password) {
    }

    @Override
    public void attachView(@NonNull LoginContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
    }

}