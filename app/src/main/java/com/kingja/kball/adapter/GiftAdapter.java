package com.kingja.kball.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.model.entiy.Gift;
import com.kingja.kball.imgaeloader.ImageLoader;
import com.kingja.kball.util.NoDoubleClickListener;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GiftAdapter extends BaseRvAdaper<Gift> {

    private OnShowGiftListener onShowGiftListener;

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
        holder.ll_buy.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (onShowGiftListener != null) {
                    onShowGiftListener.onShowGiftDialog(list.get(position),holder.iv_giftImg.getDrawable());
                }
            }
        });
    }


    class GiftViewHolder extends ViewHolder {
        public TextView tv_gitfName;
        public TextView tv_gitfDes;
        public TextView tv_giftCost;
        public ImageView iv_giftImg;
        public LinearLayout ll_buy;

        public GiftViewHolder(View itemView) {
            super(itemView);
            tv_gitfName = (TextView) itemView.findViewById(R.id.tv_gitfName);
            tv_gitfDes = (TextView) itemView.findViewById(R.id.tv_gitfDes);
            tv_giftCost = (TextView) itemView.findViewById(R.id.tv_giftCost);
            iv_giftImg = (ImageView) itemView.findViewById(R.id.iv_giftImg);
            ll_buy = (LinearLayout) itemView.findViewById(R.id.ll_buy);
        }
    }

    public interface OnShowGiftListener {
        void onShowGiftDialog(Gift gift, Drawable drawable);
    }

    public void setOnShowGiftListener(OnShowGiftListener onShowGiftListener) {
        this.onShowGiftListener = onShowGiftListener;
    }
}
