package com.kingja.kball.ui.mine;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.util.ToastUtil;
import com.pizidea.imagepicker.AndroidImagePicker;
import com.pizidea.imagepicker.bean.ImageItem;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/715:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MineFragment extends BaseFragment implements MineContract.View {
    @BindView(R.id.iv_userPhoto)
    ImageView ivUserPhoto;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @Inject
    MinePresenter mMinePresenter;

    @Override
    protected void initInjector() {
    }

    @Override
    protected void initViewAndListener() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_mine;
    }

    @OnClick(R.id.iv_userPhoto)
    public void uploadPhoto(View view) {
        AndroidImagePicker.getInstance().pickSingle(getActivity(), false, new AndroidImagePicker.OnImagePickCompleteListener() {
            @Override
            public void onImagePickComplete(List<ImageItem> items) {
                if (items != null && items.size() > 0) {
                    File file = new File(items.get(0).path);
                    RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
                    MultipartBody.Part photo = MultipartBody.Part.createFormData("head_icon", "Screenshot_2016-10-07-19-28-49-107_com.tencent.mm.png", photoRequestBody);
                    mMinePresenter.uploadHeadIcon(photo);
                }
            }
        });
    }

    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

    @Override
    public void onUploadHeadIconSuccess(HttpResult<Object> httpResult) {
        ToastUtil.showText("头像更换成功");
    }
}
