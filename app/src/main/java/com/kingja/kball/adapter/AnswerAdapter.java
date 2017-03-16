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
import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.util.NoDoubleClickListener;
import com.kingja.kball.util.ToastUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AnswerAdapter extends BaseRvAdaper<Answer> {
    @Inject
    ImageLoader imageLoader;
    private OnPraiseListener onPraiseListener;

    public AnswerAdapter(Context context, List<Answer> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new AnswerViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_answer;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, final Answer bean, final int position) {
        final AnswerViewHolder holder = (AnswerViewHolder) baseHolder;
        holder.tv_answer_name.setText(bean.getName());
        holder.tv_answer_date.setText(bean.getCreateTime());
        holder.tv_answer_content.setText(bean.getContent());
        holder.tv_answer_praiseCount.setText(bean.getPraiseCount() + "");
        holder.iv_answer_praise.setBackgroundResource(bean.getIsPraised() == 1 ? R.drawable.praise_press : R.drawable.praise_nor);
        Glide.with(context)
                .load(Constants.BASE_URL + bean.getAvatar())
                .centerCrop()
                .placeholder(R.drawable.head)
                .crossFade()
                .into(holder.iv_answer_head);
        holder.iv_answer_praise.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {

                if (bean.getIsPraised() == 1) {
                    ToastUtil.showText("已经点过赞啦");
                } else {
                    if (onPraiseListener != null) {
                        onPraiseListener.onPraise(holder.iv_answer_praise, holder.tv_answer_praiseCount);
                    }
                }
            }
        });
    }


    class AnswerViewHolder extends ViewHolder {
        public TextView tv_answer_name;
        public TextView tv_answer_date;
        public TextView tv_answer_content;
        public TextView tv_answer_praiseCount;
        public ImageView iv_answer_head;
        public ImageView iv_answer_praise;

        public AnswerViewHolder(View itemView) {
            super(itemView);
            tv_answer_name = (TextView) itemView.findViewById(R.id.tv_answer_name);
            tv_answer_date = (TextView) itemView.findViewById(R.id.tv_answer_date);
            tv_answer_content = (TextView) itemView.findViewById(R.id.tv_answer_content);
            tv_answer_praiseCount = (TextView) itemView.findViewById(R.id.tv_answer_praiseCount);
            iv_answer_head = (ImageView) itemView.findViewById(R.id.iv_answer_head);
            iv_answer_praise = (ImageView) itemView.findViewById(R.id.iv_answer_praise);
        }
    }

    public interface OnPraiseListener {
        void onPraise(ImageView iv, TextView tv);
    }

    public void setOnPraiseListener(OnPraiseListener onPraiseListener) {
        this.onPraiseListener = onPraiseListener;
    }
}
