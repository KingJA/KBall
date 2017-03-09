package com.kingja.kball.imgaeloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kingja.kball.R;
import com.kingja.kball.app.Constants;

/**
 * Description：TODO
 * Create Time：2017/3/9 11:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GlideLoader implements ImageLoader {
    @Override
    public void loadImage(Context context, String url, int resourceId, ImageView view) {
        Glide.with(context)
                .load(Constants.BASE_URL + url)
                .centerCrop()
                .placeholder(resourceId==0? R.drawable.head_default:resourceId)
                .crossFade()
                .into(view);
    }
}
