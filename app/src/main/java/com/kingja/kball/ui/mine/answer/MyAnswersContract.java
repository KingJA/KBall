package com.kingja.kball.ui.mine.answer;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.model.entiy.MyAnswer;
import com.kingja.kball.model.entiy.Question;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface MyAnswersContract {
    interface View extends BaseView {
        void showMyAnswers(List<MyAnswer> questions, boolean hasMore);

        void showMoreMyAnswers(List<MyAnswer> questions, boolean hasMore);
    }

    interface Presenter extends BasePresenter<View> {
        void getMyAnswers(String token, int pageIndex, int pageSize);
    }
}
