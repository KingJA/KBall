package com.kingja.kball.model;

import com.kingja.kball.app.Constans;
import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.model.entiy.Login;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Part;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 项目名称：和ApiService相关联
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 15:11
 * 修改备注：
 */
public class Api {

    private ApiService apiService;

    public Api() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }
    public Observable<HttpResult<Login>> login(String userName, String userPassword) {
        return apiService.login(userName,userPassword).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Observable<HttpResult<Object>> register(String userName, String userPassword) {
        return apiService.register(userName,userPassword).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public  Observable<HttpResult<Object>> uploadHeadIcon(@Part MultipartBody.Part photo){
        return apiService.uploadHeadIcon(photo).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
