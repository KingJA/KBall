package com.kingja.kball.ui.detail;

import com.kingja.kball.base.BasePresenter;
import com.kingja.kball.base.BaseView;
import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.SingleInt;

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
        void showAnswers(List<Answer> list,boolean hasMore);
        void showMoreAnswers(List<Answer> list,boolean hasMore);
        /*收藏问题*/
        void showCollected(int isCollected);

    }

    interface Presenter extends BasePresenter<View> {
        void getAnswers(String token,long questionId,int pageIndex,int pageSize);
        /*收藏问题*/
        void collect(String token,long questionId,int ifCollect);
    }
}
