package com.kingja.kball.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kingja.kball.R;
import com.kingja.kball.app.Constants;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2017/3/9 15:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CameraImgAdapter extends BaseRvAdaper<String> {
    public CameraImgAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new SingleImgViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_img;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, String s, int position) {
        final SingleImgViewHolder holder = (SingleImgViewHolder) baseHolder;
        Glide.with(context)
                .load(s)
                .centerCrop()
                .placeholder(R.drawable.city)
                .crossFade()
                .into(holder.iv_single);
    }

    class SingleImgViewHolder extends ViewHolder {
        public ImageView iv_single;

        public SingleImgViewHolder(View itemView) {
            super(itemView);
            iv_single = (ImageView) itemView.findViewById(R.id.iv_single);
        }
    }
}
