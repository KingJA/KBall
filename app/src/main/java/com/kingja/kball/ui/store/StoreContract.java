package com.kingja.kball.ui.store;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.Gift;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2017/3/10 15:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface StoreContract {
    interface View extends BaseView {
        void setGift(List<Gift>list);

    }

    interface Presenter extends BasePresenter<View> {
        void getGifts(String token);
    }
}
