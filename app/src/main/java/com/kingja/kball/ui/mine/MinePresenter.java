package com.kingja.kball.ui.mine;

import android.support.annotation.NonNull;
import android.util.Log;

import com.kingja.kball.base.SubcribePresenter;
import com.kingja.kball.model.Api;
import com.kingja.kball.model.ResultSubscriber;
import com.kingja.kball.model.entiy.HttpResult;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import rx.Subscriber;
import rx.Subscription;

/**
 * Description：TODO
 * Create Time：2016/10/12 15:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MinePresenter extends SubcribePresenter implements MineContract.Presenter {
    private Api mApi;

    @Inject
    public MinePresenter(Api mApi) {
        this.mApi = mApi;
    }

    @Override
    public void uploadHeadIcon(MultipartBody.Part photo) {
        Subscription subscribe = mApi.uploadHeadIcon(photo).subscribe(new ResultSubscriber<Object>() {
            @Override
            protected void onSuccess(HttpResult<Object> httpResult) {
                Logger.e(httpResult.getMessage());
            }
        });
        addSubscription(subscribe);
    }

    @Override
    public void attachView(@NonNull MineContract.View view) {

    }

    @Override
    public void detachView() {
        removeSubscriptions();
    }
}
