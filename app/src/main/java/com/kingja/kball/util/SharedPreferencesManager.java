package com.kingja.kball.util;

/**
 * Description：TODO
 * Create Time：2016/8/15 13:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SharedPreferencesManager {
    private SharedPreferencesIO mSharedPreferencesIO;
    public SharedPreferencesManager(SharedPreferencesIO mSharedPreferencesIO) {
        this.mSharedPreferencesIO = mSharedPreferencesIO;
    }


    private static final String NAME = "NAME";
    private static final String EMPTY_STRING = "";

    /*================================GET================================*/
    public  String getName() {
        return (String) mSharedPreferencesIO.get(NAME, EMPTY_STRING);
    }



    /*================================PUT================================*/

    public  void putName(String name) {
        mSharedPreferencesIO.put(NAME, name);
    }



}
