package com.kingja.kball.ui.answer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.kingja.kball.R;
import com.kingja.kball.adapter.CameraImgAdapter;
import com.kingja.kball.adapter.DividerItemDecoration;
import com.kingja.kball.app.Constants;
import com.kingja.kball.base.BaseActivity;
import com.kingja.kball.injector.component.AppComponent;
import com.kingja.kball.injector.component.DaggerAppComponent;
import com.kingja.kball.model.Api;
import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.rxbus.RefreshQuestionEvent;
import com.kingja.kball.ui.publish.DaggerPublishCompnent;
import com.kingja.kball.util.CheckUtil;
import com.kingja.kball.util.DialogUtil;
import com.kingja.kball.util.SharedPreferencesManager;
import com.kingja.kball.util.ToastUtil;
import com.kingja.ui.BackEditText;
import com.tangxiaolv.telegramgallery.GalleryActivity;
import com.tangxiaolv.telegramgallery.GalleryConfig;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
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
public class AnswerActivity extends BaseActivity {
    @BindView(R.id.ll_publish_close)
    LinearLayout llPublishClose;
    @BindView(R.id.et_publish_content)
    BackEditText etPublishContent;
    @BindView(R.id.ll_publish_img)
    LinearLayout llPublishImg;
    @BindView(R.id.tv_publish_confirm)
    TextView tvPublishConfirm;
    @BindView(R.id.rv_publish_imgs)
    RecyclerView rvPublishImgs;
    @BindView(R.id.et_publish_title)
    BackEditText etPublishTitle;
    @Inject
    Api api;
    @Inject
    SharedPreferencesManager mSharedPreferencesManager;
    private List<String> photoList = new ArrayList<>();
    private CameraImgAdapter mCameraImgAdapter;
    private NormalDialog quitDialog;

    @Override
    public void initVariable() {

    }

    @Override
    public int getContentId() {
        return R.layout.activity_answer;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerAnswerCompnent.builder()
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
        quitDialog = DialogUtil.getDoubleDialog(this, "确定要退出编辑？", "取消", "确定");
        quitDialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                quitDialog.dismiss();
            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                quitDialog.dismiss();
                finish();
                overridePendingTransition(R.anim.scale_big, R.anim.translate_down);
            }
        });
        etPublishTitle.setOnBackListener(onBackListener);
    }

    private BackEditText.OnBackListener onBackListener = new BackEditText.OnBackListener() {
        @Override
        public void onBack() {
            checkBackAble();
        }
    };

    private void checkBackAble() {
        String title = etPublishTitle.getText().toString().trim();
        String content = etPublishContent.getText().toString().trim();
        List<String> photos = mCameraImgAdapter.getData();
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content) && photos.size() == 0) {
            finish();
            overridePendingTransition(R.anim.scale_big, R.anim.translate_down);
        } else {
            quitDialog.show();
        }
    }

    @Override
    protected void initNet() {

    }


    private static final int PIRTURE_PICKER = 100;

    @OnClick({R.id.ll_publish_close, R.id.ll_publish_img, R.id.tv_publish_confirm})
    public void onSwitch(View view) {
        switch (view.getId()) {
            case R.id.ll_publish_close:
                checkBackAble();
                break;
            case R.id.ll_publish_img:
                GalleryConfig config = new GalleryConfig.Build()
                        .limitPickPhoto(3)
                        .singlePhoto(false)
                        .hintOfPick("pictrues must be less than 3")
                        .filterMimeTypes(new String[]{"image/jpeg"})
                        .build();
                GalleryActivity.openActivity(AnswerActivity.this, PIRTURE_PICKER, config);

                break;
            case R.id.tv_publish_confirm:
                doPublish();
                break;
        }

    }

    private void doPublish() {
        String title = etPublishTitle.getText().toString().trim();
        String content = etPublishContent.getText().toString().trim();
        if (!(CheckUtil.checkEmpty(title, "请输入标题") && CheckUtil.checkEmpty(content, "请输入详细内容"))) {
            return;
        }

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("token", mSharedPreferencesManager.getToken());
        builder.addFormDataPart("title", title);
        builder.addFormDataPart("content", content);

        //上传图片
        List<String> photos = mCameraImgAdapter.getData();
        for (String photo : photos) {
            File file = new File(photo);
            RequestBody requestBody = RequestBody.create(
                    MediaType.parse("multipart/form-data"), file);
            builder.addFormDataPart("files", file.getName(), requestBody);
        }

        MultipartBody body = builder.build();
        setProgressShow(true);
        api.publish(body).subscribe(new Observer<HttpResult<Object>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HttpResult<Object> objectHttpResult) {
                setProgressShow(false);
                if (objectHttpResult.getResultCode() == 0) {
                    ToastUtil.showText("发布成功");
                    EventBus.getDefault().post(new RefreshQuestionEvent());
                    finish();
                } else {
                    ToastUtil.showText("发布出现问题,请稍后再试");
                }
            }

            @Override
            public void onError(Throwable e) {
                setProgressShow(false);
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PIRTURE_PICKER && resultCode == Activity.RESULT_OK) {
            List<String> photos = (List<String>) data.getSerializableExtra(GalleryActivity.PHOTOS);
            mCameraImgAdapter.setData(photos);
        }
    }

}
