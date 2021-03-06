package com.kingja.kball.ui.login;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.model.entiy.Account;
import com.kingja.kball.model.entiy.Login;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface LoginContract {
    interface View extends BaseView {
        void onLoginSuccess(Account account);

        void onRegisterSuccess();

    }

    interface Presenter extends BasePresenter<View> {
        void login(String userName, String password);

        void register(String userName, String password);

    }
}
