package com.kingja.kball.ui.mine.attention;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.model.entiy.MyAttention;
import com.kingja.kball.model.entiy.Question;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface MyAttentionsContract {
    interface View extends BaseView {
        void showMyAttentions(List<MyAttention> attentions, boolean hasMore);

        void showMoreMyAttentions(List<MyAttention> attentions, boolean hasMore);
    }

    interface Presenter extends BasePresenter<View> {
        void getMyAttentions(String token, int pageIndex, int pageSize);
    }
}
