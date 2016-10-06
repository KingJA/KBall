package com.kingja.kball;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kingja.kball.app.ActivityModule;
import com.kingja.kball.app.App;
import com.kingja.kball.app.AppComponent;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Description：TODO
 * Create Time：2016/10/14:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected String TAG=getClass().getSimpleName();
    private CompositeSubscription mSubscriptions;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSubscription(subscribeEvents());
        initVariable();
        initCommon();
        setContentView(getContentId());
        ButterKnife.bind(this);
        initView();
        initEvent();
        initDate();
        setDate();

    }

    /**
     * 初始化公共组件
     */
    private void initCommon() {

    }

    public  abstract void initVariable();
    public  abstract int getContentId();
    public  abstract void initView();
    public  abstract void initEvent();
    public  abstract void initDate();
    public  abstract void setDate();


    protected void addSubscription(Subscription subscription) {
        if (subscription == null) return;
        if (mSubscriptions == null) {
            mSubscriptions = new CompositeSubscription();
        }
        mSubscriptions.add(subscription);
    }

    protected Subscription subscribeEvents() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscriptions != null) {
            mSubscriptions.clear();
        }
    }
    protected AppComponent getAppComponent() {
        return App.getInstance().getAppComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
