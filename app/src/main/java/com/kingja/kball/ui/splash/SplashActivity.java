package com.kingja.kball.ui.splash;

import android.os.Handler;
import android.text.TextUtils;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.kingja.kball.R;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.ui.detail.DaggerDetailQuestionCompnent;
import com.kingja.kball.ui.login.LoginActivity;
import com.kingja.kball.ui.main.MainActivity;
import com.kingja.kball.util.GoUtil;
import com.kingja.kball.util.SharedPreferencesManager;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description：TODO
 * Create Time：2017/3/514:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SplashActivity extends BaseActivity implements Runnable{
    @BindView(R.id.kbv)
    KenBurnsView kbv;

    @Inject
    SharedPreferencesManager mSpManager;

    @Override
    public void initVariable() {

    }

    @Override
    public int getContentId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerSplashCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initViewAndListener() {

    }

    @Override
    protected void initNet() {
        new Handler().postDelayed(this,2000);
    }

    @Override
    public void run() {
        if (TextUtils.isEmpty(mSpManager.getToken())) {
            GoUtil.goActivityAndFinish(this, LoginActivity.class);
        }else{
            GoUtil.goActivityAndFinish(this, MainActivity.class);
        }
    }
}
