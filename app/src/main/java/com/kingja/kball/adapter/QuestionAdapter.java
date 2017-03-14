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
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class QuestionAdapter extends BaseRvAdaper<Question> {

    public QuestionAdapter(Context context, List<Question> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new PersonManagerViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_question;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, Question bean, final int position) {
        final PersonManagerViewHolder holder = (PersonManagerViewHolder) baseHolder;
        holder.tv_name.setText(bean.getName());
        holder.tv_title.setText(bean.getTitle());
        holder.tv_pariseCount.setText(bean.getCollectCount() + " 收藏 • ");
        holder.tv_answerCount.setText(bean.getAnswerCount() + " 回答 • ");
        holder.tv_date.setText(bean.getCreateTime());
        holder.iv_img.setVisibility(View.GONE);
        setQuestionImg(holder.iv_img,bean.getImgUrls());
        Glide.with(context)
                .load(Constants.BASE_URL + bean.getAvatar())
                .centerCrop()
                .placeholder(R.drawable.head_default)
                .crossFade()
                .into(holder.iv_headIcon);


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

    class PersonManagerViewHolder extends ViewHolder {
        public TextView tv_name;
        public TextView tv_title;
        public ImageView iv_img;
        public ImageView iv_headIcon;
        public TextView tv_pariseCount;
        public TextView tv_answerCount;
        public TextView tv_date;

        public PersonManagerViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_headIcon = (ImageView) itemView.findViewById(R.id.iv_headIcon);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_pariseCount = (TextView) itemView.findViewById(R.id.tv_pariseCount);
            tv_answerCount = (TextView) itemView.findViewById(R.id.tv_answerCount);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
        }
    }
}
