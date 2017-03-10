package com.kingja.kball.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.Login;
import com.kingja.kball.ui.main.MainActivity;
import com.kingja.kball.util.GoUtil;
import com.kingja.kball.util.SharedPreferencesManager;
import com.kingja.kball.util.ToastUtil;
import com.kingja.ui.SwitchMultiButton;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements SwitchMultiButton.OnSwitchListener, LoginContract.View {

    @Inject
    LoginPresenter mLoginPresenter;
    @Inject
    SharedPreferencesManager mSharedPreferencesManager;
    @BindView(R.id.et_login_userName)
    EditText etLoginUserName;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.tv_login_confirm)
    TextView tvLoginConfirm;

    private int currentPosition;


    @Override
    public void initVariable() {
    }

    @Override
    public int getContentId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerLoginCompnent.builder()
                .appComponent(appComponent)
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    @Override
    protected void initViewAndListener() {
        mLoginPresenter.attachView(this);
    }

    @Override
    protected void initNet() {

    }


    @Override
    public void onSwitch(int position, String tabText) {
        currentPosition = position;
    }

    @OnClick(R.id.tv_login_confirm)
    public void doLogin(View view) {
        String userName = etLoginUserName.getText().toString().trim();
        String password = etLoginPassword.getText().toString().trim();
        mLoginPresenter.login(userName, password);

    }

    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

    @Override
    public void onLoginSuccess(Login login) {
        mSharedPreferencesManager.putName(login.getName());
        mSharedPreferencesManager.putToken(login.getToken());
        GoUtil.goActivityAndFinish(this, MainActivity.class);
    }

    @Override
    public void onRegisterSuccess() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
    }

}
