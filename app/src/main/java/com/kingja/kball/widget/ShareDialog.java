package com.kingja.kball.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kingja.kball.R;

/**
 * Description：TODO
 * Create Time：2017/3/17 10:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ShareDialog extends BottomSheetDialog {
    private Context context;

    public ShareDialog(@NonNull Context context) {
        super(context, R.style.ShareDialog);
        this.context = context;
    }

    public ShareDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    protected ShareDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_share, null);
        setContentView(view);
//        configureBottomSheetBehavior(contentView);
    }
}
