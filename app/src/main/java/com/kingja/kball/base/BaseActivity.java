package com.kingja.kball.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.kingja.kball.app.ActivityModule;
import com.kingja.kball.app.App;
import com.kingja.kball.app.AppComponent;
import com.kingja.kball.util.AppManager;

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
    protected String TAG = getClass().getSimpleName();
    private CompositeSubscription mSubscriptions;
    private ProgressDialog mDialogProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSubscription(subscribeEvents());
        initVariable();
        initCommon();
        setContentView(getContentId());
        ButterKnife.bind(this);
        initInjector();
        initViewAndListener();
        AppManager.getAppManager().addActivity(this);
    }


    /*初始化公共组件*/
    private void initCommon() {
        mDialogProgress = new ProgressDialog(this);
    }

    protected void setProgressShow(boolean ifShow) {
        if (ifShow) {
            mDialogProgress.show(this,"","加载中",true,true);
        }else{
            mDialogProgress.dismiss();
        }

    }

    /*初始化数据*/
    public abstract void initVariable();

    /*获取界面Id*/
    public abstract int getContentId();

    /*依赖注入*/
    protected abstract void initInjector();

    /*初始化界面和事件*/
    protected abstract void initViewAndListener();

    /*RxBus事件总线*/
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

    protected AppComponent getAppComponent() {
        return App.getContext().getAppComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscriptions != null) {
            mSubscriptions.clear();
        }
        if (mDialogProgress.isShowing()) {
            mDialogProgress.dismiss();
            mDialogProgress=null;
        }
        AppManager.getAppManager().finishActivity(this);
    }
}
