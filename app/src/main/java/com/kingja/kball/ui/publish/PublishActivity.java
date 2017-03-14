package com.kingja.kball.ui.publish;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kingja.kball.R;
import com.kingja.kball.adapter.CameraImgAdapter;
import com.kingja.kball.adapter.DividerItemDecoration;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.model.Api;
import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.ui.detail.DaggerDetailQuestionCompnent;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
    @BindView(R.id.rv_publish_imgs)
    RecyclerView rvPublishImgs;
    @BindView(R.id.et_publish_title)
    EditText etPublishTitle;
    @Inject
    Api api;
    private List<String> photoList = new ArrayList<>();
    private CameraImgAdapter mCameraImgAdapter;

    @Override
    public void initVariable() {

    }

    @Override
    public int getContentId() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerPublishCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initViewAndListener() {
        mCameraImgAdapter = new CameraImgAdapter(this, photoList);
        GridLayoutManager mgr = new GridLayoutManager(this, Constants.GRIDVIEW_COUNT);
        rvPublishImgs.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.HORIZONTAL_LIST));
        rvPublishImgs.setLayoutManager(mgr);
        rvPublishImgs.setHasFixedSize(true);
        rvPublishImgs.setAdapter(mCameraImgAdapter);
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
                GalleryConfig config = new GalleryConfig.Build()
                        .limitPickPhoto(3)
                        .singlePhoto(false)
                        .hintOfPick("pictrues must be less than 3")
                        .filterMimeTypes(new String[]{"image/jpeg"})
                        .build();
                GalleryActivity.openActivity(PublishActivity.this, 100, config);

                break;
            case R.id.tv_publish_confirm:
                doPublish();
                break;
        }

    }

    private void doPublish() {
        String title = etPublishTitle.getText().toString().trim();
        String content = etPublishContent.getText().toString().trim();

        RequestBody titleBody = RequestBody.create(
                MediaType.parse("multipart/form-data"), content);
        RequestBody contentBody = RequestBody.create(
                MediaType.parse("multipart/form-data"), content);


        List<String> photos = mCameraImgAdapter.getData();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("title", title);
        builder.addFormDataPart("content", content);

        for (String photo : photos) {
            File file = new File(photo);
            RequestBody requestBody = RequestBody.create(
                    MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("files", file.getName(), requestBody);
        }
        MultipartBody body = builder.build();//调用即可
        api.publish(body).subscribe(new Observer<HttpResult<Object>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(HttpResult<Object> objectHttpResult) {
                Log.e(TAG, "onNext: " + objectHttpResult.getResultText());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete: ");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (100 == requestCode && resultCode == Activity.RESULT_OK) {
            List<String> photos = (List<String>) data.getSerializableExtra(GalleryActivity.PHOTOS);
            mCameraImgAdapter.setData(photos);
        }
    }

}
