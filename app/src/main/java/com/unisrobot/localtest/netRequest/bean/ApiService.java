package com.unisrobot.localtest.netRequest.bean;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Administrator on 2018/4/11.
 */

public interface ApiService {
        @GET("token/getToken?appId=zwxMCclbnc5cJHxZJd&secret=QNbUWR1q7P3500595D0B")
        Call<Reponse> getToken();
}
