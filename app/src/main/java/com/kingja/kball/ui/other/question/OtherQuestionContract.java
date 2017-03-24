package com.kingja.kball.ui.other.question;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.model.entiy.Question;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface OtherQuestionContract {
    interface View extends BaseView {
        void showOtherQuestions(List<Question> questions, boolean hasMore);

        void showMoreOtherQuestions(List<Question> questions, boolean hasMore);
    }

    interface Presenter extends BasePresenter<View> {
        void getOtherQuestions(String token,long otherAccountId, int pageIndex, int pageSize);
    }
}
