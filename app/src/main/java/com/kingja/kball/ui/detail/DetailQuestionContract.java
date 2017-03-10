package com.kingja.kball.ui.detail;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.model.entiy.Answer;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2017/3/10 15:11
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DetailQuestionContract {
    interface View extends BaseView {
        //获取问答
        void showAnswers(List<Answer> list);

    }

    interface Presenter extends BasePresenter<View> {
        void getAnswers(String token,long questionId);
    }
}
