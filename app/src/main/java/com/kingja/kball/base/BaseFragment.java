package com.kingja.kball.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 3/16/16
 * Time: 12:14 AM
 * Desc: BaseFragment
 */
public abstract class BaseFragment extends Fragment {
    protected String TAG=getClass().getSimpleName();

    private CompositeSubscription mSubscriptions;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addSubscription(subscribeEvents());
        initInjector();
        initViewAndListener();
    }

    protected abstract void initInjector();

    protected abstract void initViewAndListener();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(getContentId(), container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    protected abstract int getContentId();

    protected Subscription subscribeEvents() {
        return null;
    }

    protected void addSubscription(Subscription subscription) {
        if (subscription == null) return;
        if (mSubscriptions == null) {
            mSubscriptions = new CompositeSubscription();
        }
        mSubscriptions.add(subscription);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mSubscriptions != null) {
            mSubscriptions.clear();
        }
    }
}
