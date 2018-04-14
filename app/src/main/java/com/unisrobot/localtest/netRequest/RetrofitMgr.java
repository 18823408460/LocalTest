package com.unisrobot.localtest.netRequest;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.unisrobot.localtest.netRequest.bean.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2018/4/11.
 * <p>
 * 由移动支付Square公司贡献
 * <p>
 * 1. 缺点是在Retrofit 1.x中没有直接取消正在进行中任务的方法。如果你想做这件事必须手动杀死，而这并不好实现
 * 在2.0的基础上添加了该功能
 */
// Retrofit是从 1.0.0 -- 2.0.0beta2.0 ,之后改名为 Retrofit2，内部做了很大的修改。

// Retrofit 内部是用Okhttp
public class RetrofitMgr {

        private volatile static RetrofitMgr retrofitMgr;

        private Retrofit retrofit;

        public static RetrofitMgr getRetrofitMgr() {
                if (retrofitMgr == null) {
                        synchronized (RetrofitMgr.class) {
                                if (retrofitMgr == null) {
                                        retrofitMgr = new RetrofitMgr();
                                }
                        }
                }
                return retrofitMgr;
        }

        private RetrofitMgr() {
                retrofit = new Retrofit.Builder()
                        .baseUrl(Cons.base)
                        //.client(new OkHttpClient.Builder().build()) //这里可加可不加？？？？
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
        }

        public ApiService getApiService() {
                ApiService apiService = retrofit.create(ApiService.class);
                return apiService;
        }

}
