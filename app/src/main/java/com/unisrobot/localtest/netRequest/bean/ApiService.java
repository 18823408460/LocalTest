package com.unisrobot.localtest.netRequest.bean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2018/4/11.
 */

public interface ApiService {

        //  https://api.douban.com/v2/movie/top250?start=0&count=1
//        Call<MovieInfo> getMovieInfo(@Query("start") int start, @Query("count") int count);
        @GET("/v2/movie/top250?start=0&count=1")
        Call<String> getMoiveInfo();


        // 这里返回的必须是一个gson对象，不能写成string，否则报错------- Expected a string but was BEGIN_OBJECT
        @GET("token/getToken?appId=zwxMCclbnc5cJHxZJd&secret=QNbUWR1q7P3500595D0B")
        Call<ResponseData> getToken();


        @GET("token/getToken?appId=zwxMCclbnc5cJHxZJd&secret=QNbUWR1q7P3500595D0B")
        Observable<ResponseData> getTokenObs();
}
