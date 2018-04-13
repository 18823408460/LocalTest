package com.unisrobot.localtest.netRequest.bean;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Administrator on 2018/4/11.
 */

public interface ApiService {

        //  https://api.douban.com/v2/movie/top250?start=0&count=1
//        Call<MovieInfo> getMovieInfo(@Query("start") int start, @Query("count") int count);
        @GET("v2/movie/top250?start=0&count=1")
        Call<String> getMoiveInfo();


        @GET("token/getToken?appId=zwxMCclbnc5cJHxZJd&secret=QNbUWR1q7P3500595D0B")
        Call<Reponse> getToken();
}
