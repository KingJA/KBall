package com.kingja.kball.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kingja.kball.R;
import com.kingja.kball.app.Constants;
import com.kingja.kball.model.entiy.Gift;
import com.kingja.kball.imgaeloader.ImageLoader;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GiftAdapter extends BaseRvAdaper<Gift> {

    public GiftAdapter(Context context, List<Gift> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new GiftViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_gift;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, Gift bean, final int position) {
        final GiftViewHolder holder = (GiftViewHolder) baseHolder;
        holder.tv_gitfName.setText(bean.getGiftName());
        holder.tv_gitfDes.setText(bean.getGiftDes());
        holder.tv_giftCost.setText(bean.getGiftCost()+"");
        ImageLoader.getInstance().loadImage(context,bean.getGiftUrl(),R.drawable.gift_cake,holder.iv_giftImg);
    }


    class GiftViewHolder extends ViewHolder {
        public TextView tv_gitfName;
        public TextView tv_gitfDes;
        public TextView tv_giftCost;
        public ImageView iv_giftImg;

        public GiftViewHolder(View itemView) {
            super(itemView);
            tv_gitfName = (TextView) itemView.findViewById(R.id.tv_gitfName);
            tv_gitfDes = (TextView) itemView.findViewById(R.id.tv_gitfDes);
            tv_giftCost = (TextView) itemView.findViewById(R.id.tv_giftCost);
            iv_giftImg = (ImageView) itemView.findViewById(R.id.iv_giftImg);
        }
    }
}
