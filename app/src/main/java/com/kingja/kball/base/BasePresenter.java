package com.kingja.kball.base;

import android.support.annotation.NonNull;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BasePresenter<T extends BaseView> {
    void attachView(@NonNull T view);
    void detachView();
}
