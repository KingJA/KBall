package com.kingja.kball.ui.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.entiy.Login;
import com.kingja.kball.ui.main.MainActivity;
import com.kingja.kball.util.GoUtil;
import com.kingja.kball.util.SharedPreferencesManager;
import com.kingja.kball.util.ToastUtil;
import com.kingja.ui.SwitchMultiButton;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements SwitchMultiButton.OnSwitchListener, LoginContract.View {

    @BindView(R.id.iv_login_logo)
    ImageView ivLoginLogo;
    @BindView(R.id.smb_login_switch)
    SwitchMultiButton smbLoginSwitch;
    @BindView(R.id.et_login_userName)
    EditText etLoginUserName;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.btn_login_confirm)
    Button btnLoginConfirm;
    @BindView(R.id.iv_login_loginByWX)
    ImageView ivLoginLoginByWX;
    @BindView(R.id.iv_login_loginByQQ)
    ImageView ivLoginLoginByQQ;
    @BindView(R.id.et_login_checkCode)
    EditText etLoginCheckCode;
    @BindView(R.id.btn_login_checkCode)
    Button btnLoginCheckCode;
    @BindView(R.id.ll_login_checkCode)
    LinearLayout llLoginCheckCode;
    @Inject
    LoginPresenter mLoginPresenter;
    @Inject
    SharedPreferencesManager mSharedPreferencesManager;

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
        smbLoginSwitch.setText(Arrays.asList("登录", "注册"));
        smbLoginSwitch.setOnSwitchListener(this);
    }

    @Override
    protected void initNet() {

    }


    @Override
    public void onSwitch(int position, String tabText) {
        llLoginCheckCode.setVisibility(position == 1 ? View.VISIBLE : View.GONE);
        currentPosition = position;
    }

    @OnClick(R.id.btn_login_confirm)
    public void onLoginOrRegister(View view) {
        String userName = etLoginUserName.getText().toString().trim();
        String password = etLoginPassword.getText().toString().trim();
        if (currentPosition == 0) {
            mLoginPresenter.login(userName, password);
        } else {
            mLoginPresenter.register(userName, password);
        }

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
        ToastUtil.showText(login.getName());
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
