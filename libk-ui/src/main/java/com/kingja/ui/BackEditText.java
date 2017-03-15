package com.kingja.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Description：监听返回键
 * Create Time：2017/3/15 14:03
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BackEditText extends EditText {
    private OnBackListener onBackListener;

    public BackEditText(Context context) {
        super(context);
    }

    public BackEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BackEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnBackListener {
        void onBack();
    }


    public void setOnBackListener(OnBackListener listener) {
        this.onBackListener = listener;
    }

    @Override

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (onBackListener != null) {
                onBackListener.onBack();
            }
        }
        return false;
    }
}
