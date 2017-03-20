package com.kingja.kball.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.model.entiy.Gift;
import com.kingja.ui.dialog.BaseDialog;

/**
 * Description：TODO
 * Create Time：2017/3/20 10:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BuyDialog extends BaseDialog {

    private ImageView mIvBuyImg;
    private LinearLayout mLlDialogItem;
    private TextView mTvBuyGiftName;
    private ImageView mIvBuyGiftLevel;
    private LinearLayout mLlDialogPrice;
    private TextView mTvBuyGiftCost;
    private ImageView mIvBuyReduce;
    private TextView mIvBuyCount;
    private ImageView mIvBuyAdd;
    private TextView mTvBuyCancle;
    private TextView mTvBuyConfirm;
    private Gift gift;
    private Drawable drawable;
    private OnGiftBuyListener onGiftBuyListener;


    public BuyDialog(Context context, Gift gift, Drawable drawable) {
        super(context);
        this.gift = gift;
        this.drawable = drawable;
    }

    @Override
    protected int getContentView() {
        return R.layout.dialog_buy;
    }

    @Override
    public void initView() {
        mIvBuyImg = (ImageView) findViewById(R.id.iv_buy_img);
        mTvBuyGiftName = (TextView) findViewById(R.id.tv_buy_giftName);
        mIvBuyGiftLevel = (ImageView) findViewById(R.id.iv_buy_giftLevel);
        mTvBuyGiftCost = (TextView) findViewById(R.id.tv_buy_giftCost);
        mIvBuyReduce = (ImageView) findViewById(R.id.iv_buy_reduce);
        mIvBuyCount = (TextView) findViewById(R.id.iv_buy_count);
        mIvBuyAdd = (ImageView) findViewById(R.id.iv_buy_add);
        mTvBuyCancle = (TextView) findViewById(R.id.tv_buy_cancle);
        mTvBuyConfirm = (TextView) findViewById(R.id.tv_buy_confirm);
    }

    @Override
    public void initData() {
        mIvBuyImg.setImageDrawable(drawable);
        mTvBuyGiftName.setText(gift.getGiftName());
        mTvBuyGiftCost.setText(gift.getGiftCost() + "");
        mTvBuyCancle.setOnClickListener(this);
        mTvBuyConfirm.setOnClickListener(this);
        mIvBuyReduce.setOnClickListener(this);
        mIvBuyAdd.setOnClickListener(this);
    }

    @Override
    public void settData() {

    }

    @Override
    public void childClick(View v) {
        Integer giftCount = Integer.valueOf(mIvBuyCount.getText().toString().trim());
        switch (v.getId()) {

            case R.id.iv_buy_reduce:
                if (giftCount > 1) {
                    mIvBuyCount.setText(--giftCount+"");
                }
                break;
            case R.id.iv_buy_add:
                if (giftCount < 999) {
                    mIvBuyCount.setText(++giftCount+"");
                }
                break;
            case R.id.tv_buy_cancle:
                dismiss();
                break;
            case R.id.tv_buy_confirm:
                dismiss();
                if (onGiftBuyListener != null) {
                    onGiftBuyListener.onGiftBuy(gift.getGiftId(), giftCount,gift.getGiftCost());
                }
                break;

        }
    }

    public interface OnGiftBuyListener{
      void  onGiftBuy(long giftId, int giftCount, int giftCost);
    }

    public void setOnGiftBuyListener(OnGiftBuyListener onGiftBuyListener) {
        this.onGiftBuyListener = onGiftBuyListener;
    }
}
