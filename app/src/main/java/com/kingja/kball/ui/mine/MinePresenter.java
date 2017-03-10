package com.kingja.kball.ui.mine;

import android.support.annotation.NonNull;

import com.kingja.kball.model.Api;

import javax.inject.Inject;

import okhttp3.MultipartBody;

/**
 * Description：TODO
 * Create Time：2016/10/12 15:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MinePresenter   implements MineContract.Presenter {
    private Api mApi;
    private MineContract.View mView;

    @Inject
    public MinePresenter(Api mApi) {
        this.mApi = mApi;
    }

    @Override
    public void uploadHeadIcon(MultipartBody.Part photo) {

//        mApi.uploadHeadIcon(photo).subscribe(new ResultObserver<Object>() {
//            @Override
//            public void onStart() {
//                mView.showLoading();
//            }
//
//            @Override
//            protected void onSuccess(HttpResult<Object> httpResult) {
//                mView.hideLoading();
//                mView.onUploadHeadIconSuccess(httpResult);
//            }
//
//            @Override
//            protected void onError(String errorText) {
//                mView.hideLoading();
//                ToastUtil.showText(errorText);
//            }
//
//            @Override
//            public void onCompleted() {
//                mView.hideLoading();
//            }
//        });
    }

    @Override
    public void attachView(@NonNull MineContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
    }
}
