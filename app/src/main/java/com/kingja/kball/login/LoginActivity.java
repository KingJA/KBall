package com.kingja.kball.login;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kingja.kball.Api;
import com.kingja.kball.BaseActivity;
import com.kingja.kball.R;
import com.kingja.kball.entiy.HttpResult;
import com.kingja.kball.entiy.Login;
import com.kingja.ui.SwitchMultiButton;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.functions.Action1;

public class LoginActivity extends BaseActivity implements SwitchMultiButton.OnSwitchListener {


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
    Api api;
    private int currentPosition;

    @Override
    public void initVariable() {
        DaggerLoginCompnent.builder()
                .appComponent(getAppComponent())
                .activityModule(getActivityModule())
                .build()
                .inject(this);
    }

    @Override
    public int getContentId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void initDate() {
        smbLoginSwitch.setText(Arrays.asList("登录", "注册"));
        smbLoginSwitch.setOnSwitchListener(this);

    }

    @Override
    public void setDate() {

    }

    @Override
    public void onSwitch(int position, String tabText) {
        llLoginCheckCode.setVisibility(position==1? View.VISIBLE:View.GONE);
        currentPosition = position;
    }
    @OnClick(R.id.btn_login_confirm)
    public void onLoginOrRegister(View view) {
        String userName= etLoginUserName.getText().toString().trim();
        String password= etLoginPassword.getText().toString().trim();

        if (currentPosition == 0) {
            api.login(userName,password).subscribe(new Subscriber<HttpResult<Login>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(HttpResult<Login> loginHttpResult) {
                    Log.e(TAG, "login: "+loginHttpResult.getMessage() );
                }
            });
        }else{
            api.register(userName,password).subscribe(new Action1<HttpResult<Object>>() {
                @Override
                public void call(HttpResult<Object> loginHttpResult) {
                    Log.e(TAG, "register: "+loginHttpResult.getMessage() );
                }
            });
        }

    }

}
