package com.kingja.kball.base;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.injector.component.AppComponent;

import butterknife.BindView;

/**
 * Description：TODO
 * Create Time：2017/3/20 14:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class BaseTitleActivity extends BaseActivity {
    @BindView(R.id.ll_title_back)
    LinearLayout llTitleBack;
    @BindView(R.id.tv_title_title)
    TextView tvTitleTitle;
    @BindView(R.id.fl_content)
    FrameLayout flContent;

    public abstract void initVariable();

    @Override
    public int getContentId() {
        return R.layout.activity_title;
    }

    protected abstract void initComponent(AppComponent appComponent);

    @Override
    protected void initViewAndListener() {
        tvTitleTitle.setText(getContentTitle() == null ? "" : getContentTitle());
        llTitleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        View content = View.inflate(this, getContentView(), null);
        if (content != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            flContent.addView(content, params);
        }
        initView();
    }

    protected abstract String getContentTitle();

    protected abstract int getContentView();

    protected abstract void initView();


    protected abstract void initNet();

}
