package com.kingja.kball.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.kball.app.App;
import com.kingja.kball.injector.component.AppComponent;

import butterknife.ButterKnife;

/**
 * Created with Android Studio.
 * User: ryan.hoo.j@gmail.com
 * Date: 3/16/16
 * Time: 12:14 AM
 * Desc: BaseFragment
 */
public abstract class BaseFragment extends Fragment {
    private ProgressDialog mDialogProgress;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCommon();
        initComponent(App.getContext().getAppComponent());
        initViewAndListener();
    }

    private void initCommon() {
        mDialogProgress = new ProgressDialog(getActivity());
    }
    /*设置圆形进度条*/
    protected void setProgressShow(boolean ifShow) {
        if (ifShow) {
            mDialogProgress.show(getActivity(), "", "加载中", true, true);
        } else {
            mDialogProgress.dismiss();
        }
    }

    protected abstract void initComponent(AppComponent appComponent);

    protected abstract void initViewAndListener();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(getContentId(), container, false);
        ButterKnife.bind(this, mRootView);
        return mRootView;
    }

    protected abstract int getContentId();



    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
