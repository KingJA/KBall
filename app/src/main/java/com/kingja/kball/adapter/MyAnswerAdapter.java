package com.kingja.kball.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kingja.kball.R;
import com.kingja.kball.app.Constants;
import com.kingja.kball.model.entiy.MyAnswer;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MyAnswerAdapter extends BaseRvAdaper<MyAnswer> {

    public MyAnswerAdapter(Context context, List<MyAnswer> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new MyAnswerViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_my_answer;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, MyAnswer bean, final int position) {
        final MyAnswerViewHolder holder = (MyAnswerViewHolder) baseHolder;
        holder.tv_answer_content.setText(bean.getAnswerContent());
        holder.tv_question_title.setText(bean.getTitle());
        holder.tv_question_date.setText(bean.getAnswerTime());
        holder.tv_answer_praiseCount.setText(bean.getPraiseCount()+"");
        holder.iv_question_img.setVisibility(View.GONE);
        setQuestionImg(holder.iv_question_img,bean.getImgUrls());
    }

    private void setQuestionImg(ImageView iv_img, String imgUrls) {
        if (TextUtils.isEmpty(imgUrls)) {
            return;
        }
        String[] imgArr = imgUrls.split("#");
        iv_img.setVisibility(View.VISIBLE);
        Glide.with(context)
                .load(Constants.BASE_URL + imgArr[0])
                .centerCrop()
                .placeholder(R.drawable.city)
                .crossFade()
                .into(iv_img);
    }

    class MyAnswerViewHolder extends ViewHolder {
        public TextView tv_answer_content;
        public TextView tv_question_title;
        public TextView tv_question_date;
        public TextView tv_answer_praiseCount;
        public ImageView iv_question_img;

        public MyAnswerViewHolder(View itemView) {
            super(itemView);
            tv_answer_content = (TextView) itemView.findViewById(R.id.tv_answer_content);
            tv_question_title = (TextView) itemView.findViewById(R.id.tv_question_title);
            tv_question_date = (TextView) itemView.findViewById(R.id.tv_question_date);
            tv_answer_praiseCount = (TextView) itemView.findViewById(R.id.tv_answer_praiseCount);
            iv_question_img = (ImageView) itemView.findViewById(R.id.iv_question_img);
        }
    }
}
