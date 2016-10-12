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
    public void onCompleted() {
    }


    @Override
    public void onError(Throwable e) {
        ToastUtil.showText("网络连接错误");
    }

    @Override
    public void onNext(HttpResult<T> httpResult) {
        if (httpResult.getCode() == 0) {
            onSuccess(httpResult);
        } else {
            ToastUtil.showText(httpResult.getMessage());
        }

    }

    protected abstract void onSuccess(HttpResult<T> httpResult);
}
