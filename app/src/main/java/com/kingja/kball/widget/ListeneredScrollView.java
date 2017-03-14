package com.kingja.kball.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ScrollView;

/**
 * Description：TODO
 * Create Time：2017/3/14 9:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ListeneredScrollView extends ScrollView {
    private OnScrollBottomListener onScrollBottomListener;
    private OnScrollTopListener onScrollTopListener;

    public ListeneredScrollView(Context context) {
        this(context, null);
    }

    public ListeneredScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListeneredScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (getScrollY() == 0 && onScrollTopListener != null) {
            onScrollTopListener.onScrollTop();
        } else if ((getScrollY() + getHeight() - getPaddingTop() - getPaddingBottom() == getChildAt(0).getHeight()) && onScrollBottomListener != null) {
            onScrollBottomListener.onScrollBottom();
        }
    }

    public interface OnScrollTopListener {
        void onScrollTop();
    }

    public interface OnScrollBottomListener {
        void onScrollBottom();
    }

    public void setOnScrollBottomListener(OnScrollBottomListener onScrollBottomListener) {

        this.onScrollBottomListener = onScrollBottomListener;
    }

    public void setOnScrollTopListener(OnScrollTopListener onScrollTopListener) {

        this.onScrollTopListener = onScrollTopListener;
    }

}
