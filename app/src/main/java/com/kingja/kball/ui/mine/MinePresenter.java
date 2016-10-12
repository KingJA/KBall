package com.kingja.kball.ui.mine;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;
import com.kingja.kball.model.entiy.HttpResult;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import rx.Subscriber;

/**
 * Description：TODO
 * Create Time：2016/10/12 15:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MinePresenter implements MineContract.Presenter {
    private Api mApi;
    @Inject
    public MinePresenter(Api mApi) {
        this.mApi = mApi;
    }

    @Override
    public void uploadHeadIcon(MultipartBody.Part photo) {
        mApi.uploadHeadIcon(photo).subscribe(new Subscriber<HttpResult<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HttpResult<Object> objectHttpResult) {

            }
        });
    }

    @Override
    public void attachView(@NonNull MineContract.View view) {

    }

    @Override
    public void detachView() {

    }
}
