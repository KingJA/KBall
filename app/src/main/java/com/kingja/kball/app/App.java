package com.kingja.kball.app;

import android.app.Application;


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
