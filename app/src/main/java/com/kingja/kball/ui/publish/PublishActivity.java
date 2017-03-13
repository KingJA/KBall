package com.kingja.kball.ui.publish;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.util.GoUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description：TODO
 * Create Time：2017/3/13 15:57
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublishActivity extends BaseActivity {
    @BindView(R.id.ll_publish_close)
    LinearLayout llPublishClose;
    @BindView(R.id.et_publish_content)
    EditText etPublishContent;
    @BindView(R.id.ll_publish_img)
    LinearLayout llPublishImg;
    @BindView(R.id.tv_publish_confirm)
    TextView tvPublishConfirm;

    @Override
    public void initVariable() {

    }

    @Override
    public int getContentId() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initViewAndListener() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.scale_big, R.anim.translate_down);
    }

    @OnClick({R.id.ll_publish_close, R.id.ll_publish_img, R.id.tv_publish_confirm})
    public void onSwitch(View view) {
        switch (view.getId()) {
            case R.id.ll_publish_close:
                finish();
                overridePendingTransition(R.anim.scale_big, R.anim.translate_down);
                break;
            case R.id.ll_publish_img:
                //TODO
                break;
            case R.id.tv_publish_confirm:
                //TODO
                break;
        }

    }

}
