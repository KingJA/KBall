package com.kingja.kball.model;

import com.kingja.kball.model.entiy.Answer;
import com.kingja.kball.model.entiy.Gift;
import com.kingja.kball.model.entiy.HttpResult;
import com.kingja.kball.model.entiy.Login;
import com.kingja.kball.model.entiy.MyAnswer;
import com.kingja.kball.model.entiy.MyAttention;
import com.kingja.kball.model.entiy.Question;
import com.kingja.kball.model.entiy.SingleInt;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
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
    Observable<HttpResult<List<Answer>>> getAnswers(@Field("token") String token, @Field("questionId") long questionId, @Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("/mobile/question/collect")
    Observable<HttpResult<SingleInt>> collect(@Field("token") String token, @Field("questionId") long questionId, @Field("ifCollect") int ifCollect);

    @FormUrlEncoded
    @POST("/mobile/question/attention")
    Observable<HttpResult<SingleInt>> attention(@Field("token") String token, @Field("otherAccountId") long questionId, @Field("ifAttention") int ifAttention);

    @FormUrlEncoded
    @POST("/mobile/question/praise")
    Observable<HttpResult<SingleInt>> praise(@Field("token") String token, @Field("answerId") long answerId);

    @POST("/mobile/question/publish")
    Observable<HttpResult<Object>> publish(@Body MultipartBody imgs);

    @POST("/mobile/question/answer")
    Observable<HttpResult<Object>> answer(@Body MultipartBody imgs);

    @FormUrlEncoded
    @POST("/mobile/store/getGifts")
    Observable<HttpResult<List<Gift>>> getGifts(@Field("token") String token);

    @FormUrlEncoded
    @POST("/mobile/store/getMyGifts")
    Observable<HttpResult<List<Gift>>> getMyGifts(@Field("token") String token);

    @FormUrlEncoded
    @POST("/mobile/store/buyGift")
    Observable<HttpResult<Object>> buyGift(@Field("token") String token, @Field("giftId") long giftId, @Field("count") int count, @Field("cost") int cost);

    @Multipart
    @POST("HeadIconUpload.php")
    Observable<HttpResult<Object>> uploadHeadIcon(@Part MultipartBody.Part photo);

    /*我的提问*/
    @FormUrlEncoded
    @POST("/mobile/mine/getMyQuestions")
    Observable<HttpResult<List<Question>>> getMyQuestions(@Field("token") String token, @Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize);

    /*我的收藏*/
    @FormUrlEncoded
    @POST("/mobile/mine/getMyCollections")
    Observable<HttpResult<List<Question>>> getMyCollections(@Field("token") String token, @Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize);

    /*我的提问*/
    @FormUrlEncoded
    @POST("/mobile/mine/getMyAnswers")
    Observable<HttpResult<List<MyAnswer>>> getMyAnswers(@Field("token") String token, @Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize);

    /*我的关注*/
    @FormUrlEncoded
    @POST("/mobile/mine/getMyAttentions")
    Observable<HttpResult<List<MyAttention>>> getMyAttentions(@Field("token") String token, @Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize);

    /*我的粉丝*/
    @FormUrlEncoded
    @POST("/mobile/mine/getMyFans")
    Observable<HttpResult<List<MyAttention>>> getMyFans(@Field("token") String token, @Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize);


}
