package com.kingja.kball.ui.mine.ask;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.model.entiy.Question;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface MyQuestionsContract {
    interface View extends BaseView {
        void showMyQuestions(List<Question> questions, boolean hasMore);

        void showMoreMyQuestions(List<Question> questions, boolean hasMore);
    }

    interface Presenter extends BasePresenter<View> {
        void getMyQuestions(String token, int pageIndex, int pageSize);
    }
}
