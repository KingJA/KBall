package com.kingja.kball.model;

import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.util.ToastUtil;

import rx.Subscriber;

/**
 * Description：TODO
 * Create Time：2016/10/12 15:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class ResultSubscriber<T> extends Subscriber<HttpResult<T>> {


    @Override
    public void onError(Throwable e) {
        onError("网络连接错误");
    }

    @Override
    public void onNext(HttpResult<T> httpResult) {
        if (httpResult.getCode() == 0) {
            onSuccess(httpResult);
        } else {
            onError(httpResult.getMessage());
        }

    }

    @Override
    public abstract void onStart();

    protected abstract void onSuccess(HttpResult<T> httpResult);

    protected abstract void onError(String errorText);

    @Override
    public abstract void onCompleted();

}
