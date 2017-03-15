package com.kingja.kball.ui.main;

import android.util.Log;

import com.kingja.kball.rxbus.RefreshQuestionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Description：TODO
 * Create Time：2017/3/15 14:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AllQuestionFragment extends QuestionsFragment {
    @Override
    protected void initViewAndListener() {
        EventBus.getDefault().register(this);
        super.initViewAndListener();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(RefreshQuestionEvent event) {
        Log.e(TAG, "刷新: ");
        initNet();

    }
}
