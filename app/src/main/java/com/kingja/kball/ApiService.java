package com.kingja.kball;

import com.kingja.kball.entiy.HttpResult;
import com.kingja.kball.entiy.Login;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * 项目名称：和Api相关联
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/12 16:32
 * 修改备注：
 */
public interface ApiService {
    @FormUrlEncoded
    @POST("Login.php")
    Observable<HttpResult<Login>> login(@Field("user_name") String userName, @Field("user_password") String userPassword);

    @FormUrlEncoded
    @POST("Register.php")
    Observable<HttpResult<Object>> register(@Field("user_name") String userName, @Field("user_password") String userPassword);

    @Multipart
    @POST("HeadIconUpload.php")
    Observable<HttpResult<Object>> uploadHeadIcon(@Part("head_icon") String description, @Part("file\"; filename=\"image.png\"") RequestBody requestBody);
}
