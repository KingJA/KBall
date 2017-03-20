package com.kingja.kball.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.imgaeloader.ImageLoader;
import com.kingja.kball.model.entiy.Gift;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyGiftAdapter extends BaseRvAdaper<Gift> {


    public MyGiftAdapter(Context context, List<Gift> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new MyGiftViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_my_gift;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, Gift bean, final int position) {
        final MyGiftViewHolder holder = (MyGiftViewHolder) baseHolder;
        holder.tv_giftName.setText(bean.getGiftName());
        holder.tv_giftCost.setText(bean.getGiftCost()+"");
        holder.tv_giftCount.setText("X "+bean.getGiftCount());
        ImageLoader.getInstance().loadImage(context,bean.getGiftUrl(),R.drawable.gift_cake,holder.iv_giftImg);
    }


    class MyGiftViewHolder extends ViewHolder {
        public TextView tv_giftName;
        public TextView tv_giftCount;
        public TextView tv_giftCost;
        public ImageView iv_giftImg;
        public ImageView iv_giftLevel;

        public MyGiftViewHolder(View itemView) {
            super(itemView);
            iv_giftLevel = (ImageView) itemView.findViewById(R.id.iv_giftLevel);
            iv_giftImg = (ImageView) itemView.findViewById(R.id.iv_giftImg);
            tv_giftName = (TextView) itemView.findViewById(R.id.tv_giftName);
            tv_giftCount = (TextView) itemView.findViewById(R.id.tv_giftCount);
            tv_giftCost = (TextView) itemView.findViewById(R.id.tv_giftCost);
        }
    }
}
