package com.kingja.kball.app;

import android.app.Application;

import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.injector.component.DaggerAppComponent;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;


/**
 * Description：App
 * Create Time：2016/10/14:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class App extends Application {
    private static App sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance=this;
        setupComponent();
        Logger.init().logLevel(LogLevel.FULL);
    }

    private void setupComponent() {
        appComponent = DaggerAppComponent.create();
    }

    public static App getContext() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
