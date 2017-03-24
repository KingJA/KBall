package com.kingja.kball.ui.other;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.OtherUser;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2017/3/10 15:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface OtherContract {
    interface View extends BaseView {

        void showOtherUserInfo(OtherUser otherUser);
        /*关注用户*/
        void showAttention(int isAttentioned);
    }

    interface Presenter extends BasePresenter<View> {
        void getOtherUserInfo(String token, long otherAccountId);
        void attention(String token,long otherAccountId,int ifAttention);
    }
}
