package com.kingja.kball.model;

import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.model.entiy.Login;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 项目名称：和Api相关联
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/12 16:32
 * 修改备注：
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("/mobile/account/doLogin.json")
    Observable<HttpResult<Login>> login(@Field("name") String userName, @Field("password") String userPassword);

    @FormUrlEncoded
    @POST("Register.php")
    Observable<HttpResult<Object>> register(@Field("user_name") String userName, @Field("user_password") String userPassword);

    @Multipart
    @POST("HeadIconUpload.php")
    Observable<HttpResult<Object>> uploadHeadIcon(@Part MultipartBody.Part photo);
}
