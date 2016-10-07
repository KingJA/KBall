package com.kingja.kball.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.kball.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description：TODO
 * Create Time：2016/10/715:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_userPhoto)
    ImageView ivUserPhoto;
    @BindView(R.id.tv_userName)
    TextView tvUserName;

    @Override
    protected int getContentId() {
        return R.layout.fragment_mine;
    }

    @OnClick(R.id.iv_userPhoto)
    public void uploadPhoto(View view) {
        Log.e(TAG, "uploadPhoto: ");
    }

}
