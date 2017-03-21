package com.kingja.kball.model;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kingja.kball.app.Constants;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Part;

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
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public Observable<HttpResult<Login>> login(String userName, String userPassword) {
        return apiService.login(userName, userPassword).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<HttpResult<List<Question>>> getQuestions(String token, int pageIndex, int pageSize, int solved) {
        return apiService.getQuestions(token, pageIndex, pageSize, solved).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<HttpResult<List<Answer>>> getAnswers(String token, long questionId, int pageIndex, int pageSize) {
        return apiService.getAnswers(token, questionId, pageIndex, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<HttpResult<Object>> publish(@Body MultipartBody imgs) {
        return apiService.publish(imgs).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<HttpResult<Object>> answer(@Body MultipartBody answerBody) {
        return apiService.answer(answerBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<HttpResult<SingleInt>> collect(String token, long questionId, int ifCollect) {
        return apiService.collect(token, questionId, ifCollect).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<HttpResult<SingleInt>> praise(String token, long answerId) {
        return apiService.praise(token, answerId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<HttpResult<List<Gift>>> getGifts(String token) {
        return apiService.getGifts(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<HttpResult<List<Gift>>> getMyGifts(String token) {
        return apiService.getMyGifts(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<HttpResult<Object>> buyGift(String token, long giftId, int count, int cost) {
        return apiService.buyGift(token, giftId, count, cost).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Observable<HttpResult<List<Question>>> getMyQuestions(String token, int pageIndex, int pageSize) {
        return apiService.getMyQuestions(token, pageIndex, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
      public Observable<HttpResult<List<MyAnswer>>> getMyAnswers(String token, int pageIndex, int pageSize) {
        return apiService.getMyAnswers(token, pageIndex, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
      public Observable<HttpResult<List<MyAttention>>> getMyAttentions(String token, int pageIndex, int pageSize) {
        return apiService.getMyAttentions(token, pageIndex, pageSize).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
