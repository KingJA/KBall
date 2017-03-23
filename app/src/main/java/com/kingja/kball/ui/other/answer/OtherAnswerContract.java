package com.kingja.kball.ui.other.answer;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.MyAnswer;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2017/3/10 15:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface OtherAnswerContract {
    interface View extends BaseView {
        void showOtherAnswers(List<MyAnswer> list, boolean hasMore);
        void showMoreOtherAnswers(List<MyAnswer> list, boolean hasMore);

    }

    interface Presenter extends BasePresenter<View> {
        void getOtherAnswers(String token,long otherAccountId, int pageIndex, int pageSize);
    }
}
