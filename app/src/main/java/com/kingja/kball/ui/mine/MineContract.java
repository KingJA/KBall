package com.kingja.kball.ui.mine;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;

import okhttp3.MultipartBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface MineContract {
    interface View extends BaseView {
        void showLoading();

        void hideLoading();

        void onUploadHeadIconSuccess();


    }

    interface Presenter extends BasePresenter<View> {
        void uploadHeadIcon(MultipartBody.Part photo);

    }
}
