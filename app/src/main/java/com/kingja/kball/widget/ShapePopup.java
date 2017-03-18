package com.kingja.kball.widget;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.ui.popupwindow.BasePopupWindow;

/**
 * Description：TODO
 * Create Time：2017/3/17 11:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ShapePopup extends BasePopupWindow<String> {
    private LinearLayout mLlWeixinfrends;
    private LinearLayout mLlWeixin;
    private LinearLayout mLlWeibo;
    private TextView mTvCancle;

    private void assignViews() {

    }

    public ShapePopup(View parentView, Activity activity, String data) {
        super(parentView, activity, data);
    }

    public ShapePopup(View parentView, Activity activity) {
        super(parentView, activity);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setAnimationStyle(com.kingja.ui.R.style.PopupBottom2TopAnimation);
    }

    @Override
    public View setPopupView(Activity activity) {
        popupView = View.inflate(activity, R.layout.dialog_share, null);
        return popupView;
    }

    @Override
    public void initChildView() {
        mLlWeixinfrends = (LinearLayout) popupView.findViewById(R.id.ll_weixinfrends);
        mLlWeixin = (LinearLayout) popupView.findViewById(R.id.ll_weixin);
        mLlWeibo = (LinearLayout) popupView.findViewById(R.id.ll_weibo);
        mTvCancle = (TextView) popupView.findViewById(R.id.tv_cancle);
        mLlWeixinfrends.setOnClickListener(this);
        mLlWeixin.setOnClickListener(this);
        mLlWeibo.setOnClickListener(this);
        mTvCancle.setOnClickListener(this);
    }

    @Override
    public void OnChildClick(View v) {
        switch (v.getId()) {
            case R.id.ll_weixinfrends:
                break;
            case R.id.ll_weixin:
                break;
            case R.id.ll_weibo:
                break;
            case R.id.tv_cancle:
                closePopupWindow(activity);
                break;

        }
    }
}
