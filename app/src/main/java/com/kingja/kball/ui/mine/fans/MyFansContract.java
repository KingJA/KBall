package com.kingja.kball.ui.mine.fans;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.model.entiy.MyAttention;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface MyFansContract {
    interface View extends BaseView {
        void showMyFans(List<MyAttention> attentions, boolean hasMore);

        void showMoreMyFans(List<MyAttention> attentions, boolean hasMore);
    }

    interface Presenter extends BasePresenter<View> {
        void getMyFans(String token, int pageIndex, int pageSize);
    }
}
