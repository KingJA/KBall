package com.kingja.kball.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Description：TODO
 * Create Time：2016/10/12 16:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SubcribePresenter {
    private CompositeSubscription mSubscriptions;
    protected void addSubscription(Subscription subscription) {
        if (subscription == null) return;
        if (mSubscriptions == null) {
            mSubscriptions = new CompositeSubscription();
        }
        mSubscriptions.add(subscription);
    }

    protected void removeSubscriptions() {
        if (mSubscriptions != null) {
            mSubscriptions.clear();
        }
    }
}
