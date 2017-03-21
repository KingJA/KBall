package com.kingja.kball.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kingja.kball.R;
import com.kingja.kball.app.Constants;
import com.kingja.kball.imgaeloader.ImageLoader;
import com.kingja.kball.model.entiy.MyAttention;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyAttentionAdapter extends BaseRvAdaper<MyAttention> {

    public MyAttentionAdapter(Context context, List<MyAttention> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new MyAttentionViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_my_attention;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, MyAttention bean, final int position) {
        final MyAttentionViewHolder holder = (MyAttentionViewHolder) baseHolder;
        holder.tv_attention_name.setText(bean.getName());
        holder.tv_attention_title.setText(bean.getRankInfo().getTitle());
        holder.tv_attention_des.setText(bean.getDes());
        ImageLoader.getInstance().loadImage(context,bean.getAvatar(),R.drawable.head,holder.iv_attention_head);
    }


    class MyAttentionViewHolder extends ViewHolder {
        public TextView tv_attention_name;
        public TextView tv_attention_title;
        public TextView tv_attention_des;
        public ImageView iv_attention_head;
        public ImageView iv_attention_level;

        public MyAttentionViewHolder(View itemView) {
            super(itemView);
            tv_attention_name = (TextView) itemView.findViewById(R.id.tv_attention_name);
            tv_attention_title = (TextView) itemView.findViewById(R.id.tv_attention_title);
            tv_attention_des = (TextView) itemView.findViewById(R.id.tv_attention_des);
            iv_attention_head = (ImageView) itemView.findViewById(R.id.iv_attention_head);
            iv_attention_level = (ImageView) itemView.findViewById(R.id.iv_attention_level);
        }
    }
}
