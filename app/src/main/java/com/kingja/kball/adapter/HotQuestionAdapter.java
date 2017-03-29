package com.kingja.kball.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kingja.kball.R;
import com.kingja.kball.app.Constants;
import com.kingja.kball.model.entiy.Question;

import java.util.List;

/**
 * Description：热门提问
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class HotQuestionAdapter extends BaseRvAdaper<Question> {

    public HotQuestionAdapter(Context context, List<Question> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new PersonManagerViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_hot_question;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, Question bean, final int position) {
        final PersonManagerViewHolder holder = (PersonManagerViewHolder) baseHolder;
        holder.tv_name.setText(bean.getName());
        holder.tv_title.setText(bean.getTitle());
        holder.tv_hot_answerCount.setText("回答:"+bean.getAnswerCount());
        holder.tv_hot_num.setText((position+1)+"");
        Glide.with(context)
                .load(Constants.BASE_URL + bean.getAvatar())
                .centerCrop()
                .placeholder(R.drawable.head_default)
                .crossFade()
                .into(holder.iv_headIcon);
    }


    class PersonManagerViewHolder extends ViewHolder {
        public TextView tv_name;
        public TextView tv_title;
        public TextView tv_hot_answerCount;
        public TextView tv_hot_num;
        public ImageView iv_headIcon;

        public PersonManagerViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_headIcon = (ImageView) itemView.findViewById(R.id.iv_headIcon);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_hot_answerCount = (TextView) itemView.findViewById(R.id.tv_hot_answerCount);
            tv_hot_num = (TextView) itemView.findViewById(R.id.tv_hot_num);
        }
    }
}
