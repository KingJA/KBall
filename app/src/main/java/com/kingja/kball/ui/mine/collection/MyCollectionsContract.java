package com.kingja.kball.ui.mine.collection;

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
public interface MyCollectionsContract {
    interface View extends BaseView {
        void showMyCollections(List<Question> questions, boolean hasMore);

        void showMoreMyCollections(List<Question> questions, boolean hasMore);
    }

    interface Presenter extends BasePresenter<View> {
        void getMyCollections(String token, int pageIndex, int pageSize);
    }
}
