package com.kingja.kball.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Description：TODO
 * Create Time：2017/3/2 15:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SpManager {

    private final SharedPreferences mSharedPreferences;

    private SpManager(Application application) {
        mSharedPreferences = application.getSharedPreferences("", Context.MODE_PRIVATE);
    }

    private static SpManager mInstance;

    public SpManager getInstance(Application application) {
        if (mInstance == null) {
            synchronized (SpManager.class) {
                if (mInstance == null) {
                    mInstance = new SpManager(application);
                }
            }
        }
        return mInstance;
    }

    private void putString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
