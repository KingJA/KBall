package com.kingja.kball.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.kball.base.BaseFragment;
import com.kingja.kball.model.Api;
import com.kingja.kball.R;
import com.kingja.kball.model.entiy.HttpResult;
import com.pizidea.imagepicker.AndroidImagePicker;
import com.pizidea.imagepicker.bean.ImageItem;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

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
        AndroidImagePicker.getInstance().pickSingle(getActivity(), false, new AndroidImagePicker.OnImagePickCompleteListener() {
            @Override
            public void onImagePickComplete(List<ImageItem> items) {
                if(items != null && items.size() > 0){
                    Log.e(TAG,"=====selected："+items.get(0).path);
                    File file = new File(items.get(0).path);
                    RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/png"), file);
                    MultipartBody.Part photo = MultipartBody.Part.createFormData("head_icon", "Screenshot_2016-10-07-19-28-49-107_com.tencent.mm.png", photoRequestBody);
                    new Api().uploadHeadIcon(photo).subscribe(new Subscriber<HttpResult<Object>>() {
                        @Override
                        public void onCompleted() {
                            Log.e(TAG, "onCompleted: ");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e(TAG, "onError: "+e.toString() );
                        }

                        @Override
                        public void onNext(HttpResult<Object> objectHttpResult) {
                            Log.e(TAG, "onNext: "+objectHttpResult.getMessage() );
                        }
                    });
                }
            }
        });
    }
}
