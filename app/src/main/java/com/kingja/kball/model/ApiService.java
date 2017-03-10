package com.kingja.kball.model;

import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.model.entiy.Login;
import com.kingja.kball.model.entiy.Question;

import java.util.List;

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
    @POST("/mobile/account/login")
    Observable<HttpResult<Login>> login(@Field("name") String userName, @Field("password") String userPassword);

    @FormUrlEncoded
    @POST("/mobile/question/getQuestions")
    Observable<HttpResult<List<Question>>> getQuestions(@Field("token") String token, @Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize, @Field("solved") int solved);

    @FormUrlEncoded
    @POST("/mobile/question/getAnswers")
    Observable<HttpResult<List<Answer>>> getAnswers(@Field("token") String token, @Field("questionId") long questionId);

    @Multipart
    @POST("HeadIconUpload.php")
    Observable<HttpResult<Object>> uploadHeadIcon(@Part MultipartBody.Part photo);
}
