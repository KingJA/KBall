package com.kingja.kball.ui.rank.hot;

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
public interface RankHotContract {
    interface View extends BaseView {
        void showHotQuestions(List<Question> questions, boolean hasMore);

        void showMoreHotQuestions(List<Question> questions, boolean hasMore);
    }

    interface Presenter extends BasePresenter<View> {
        void getHotQuestions(String token, int pageIndex, int pageSize);
    }
}
