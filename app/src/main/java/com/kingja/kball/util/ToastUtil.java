package com.kingja.kball.util;


import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ToastUtil {

    public static Toast mToast2;
    public static Toast mToast;
    private static View toastRoot;
    private static TextView tv_message;

    public static void singleToast(Context mContext, String msg) {
        if (mToast2 == null) {
            mToast2 = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        mToast2.setText(msg);
        mToast2.show();
    }

    public static void showThreadToast(final Activity context, final String msg) {
        if ("main".equals(Thread.currentThread().getName())) {
            showToast(context, msg, 1);
        } else {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showToast(context, msg, 1);
                }
            });
        }
    }

    public static void showText(String message) {
        showToast(AppUtil.getContext(), message, Toast.LENGTH_SHORT);
    }



    private static void showToast(Context mContext, String message, int duration) {
        if (mToast == null) {
            mToast = new Toast(mContext);
        }
        tv_message.setText(message);
        mToast.setGravity(Gravity.BOTTOM, 0, AppUtil.dp2px(96));
        mToast.setDuration(duration);
        mToast.setView(toastRoot);
        mToast.show();
    }

    public static void showThreadToast(final String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                ToastUtil.showText(msg);
                Looper.loop();
            }
        }).start();
    }
}
