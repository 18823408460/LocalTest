package com.unisrobot.localtest.netRequest;

import com.unisrobot.localtest.netRequest.bean.ApiService;
import com.unisrobot.localtest.netRequest.bean.Reponse;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

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
                        .baseUrl(Cons.baseDouban)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        }

        public ApiService getApiService() {
                ApiService apiService = retrofit.create(ApiService.class);
                return apiService;
        }

}
