package com.kingja.kball.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.injector.component.DaggerAppComponent;
import com.kingja.kball.injector.module.ApiModule;
import com.kingja.kball.injector.module.AppModule;
import com.kingja.kball.injector.module.SharedPreferencesModule;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import cn.sharesdk.framework.ShareSDK;


/**
 * Description：App
 * Create Time：2016/10/14:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 *
 * 1.创建全局AppComponent
 * 2.对外提供方法获取AppComponent
 */
public class App extends MultiDexApplication {
    private static App sInstance;
    private AppComponent appComponent;
    private static SharedPreferences mSharedPreferences;
    private AppModule appModule;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        ShareSDK.initSDK(this);
        this.sInstance = this;
        mSharedPreferences = getSharedPreferences(Constants.APPLICATION_NAME, MODE_PRIVATE);
        setupComponent();
        Logger.init().logLevel(LogLevel.FULL);
    }

    /**
     * 全局注射器,把全局经常用的实例全引用，然后再给各个Activity或者Fragment引用
     */
    private void setupComponent() {
        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .sharedPreferencesModule(new SharedPreferencesModule()).build();
        appModule = new AppModule(this);
    }

    public static App getContext() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public AppModule getAppModule() {
        return appModule;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;/*64K说拜拜*/
    }
}
