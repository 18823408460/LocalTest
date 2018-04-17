package com.unisrobot.localtest.netRequest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.unisrobot.localtest.R;
import com.unisrobot.localtest.netRequest.bean.ApiService;
import com.unisrobot.localtest.netRequest.bean.Reponse;
import com.unisrobot.localtest.netRequest.bean.ResponseData;
import com.unisrobot.localtest.rx.RxBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2018/4/11.
 */

public class NetRequestActivity extends Activity {

        private static final String TAG = "NetRequestActivity";

        @BindView(R.id.button2)
        Button button2;

        @BindView(R.id.button3)
        Button button3;

        @BindView(R.id.button4)
        Button button4;

        @BindView(R.id.button5)
        Button button5;

        @BindView(R.id.button6)
        Button button6;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_netrequest);
                ButterKnife.bind(this);

                Subscription subscribe = RxBus.getDefault().toObservable(String.class).subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                                Log.e(TAG, "call: " + s);
                        }
                });
        }

        @OnClick(R.id.button2)
        public void okhttpSyn() {
                testRxBus();
        }

        private void testRxBus() {

                for (int i = 0; i < 5; i++) {
                        final int finalI = i;
                        new Thread(new Runnable() {
                                @Override
                                public void run() {
                                        RxBus.getDefault().post("value=" + finalI + "  name=" + Thread.currentThread().getName());
                                }
                        }).start();
                }
//                boolean unsubscribed = subscribe.isUnsubscribed();
//                if (!unsubscribed){
//                     subscribe.unsubscribe();
//                }

        }

        private void testRestrofitObs() {
                ApiService apiService = RetrofitMgr.getRetrofitMgr().getApiService();
                apiService.getTokenObs().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseData>() {
                                @Override
                                public void accept(ResponseData responseData) throws Exception {
                                        Log.e(TAG, "accept: " + responseData);
                                }
                        });
        }

        private void testRetrofitf() {
                ApiService apiService = RetrofitMgr.getRetrofitMgr().getApiService();
                Call<ResponseData> moiveInfo = apiService.getToken();
                moiveInfo.enqueue(new Callback<ResponseData>() {
                        @Override
                        public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                                boolean success = response.isSuccessful();
                                if (success) {
                                        String body = response.body().toString();
                                        Log.e(TAG, "onResponse: " + body);
                                }
                                else {
                                        Log.e(TAG, "onResponse: error");
                                }
                        }

                        @Override
                        public void onFailure(Call<ResponseData> call, Throwable t) {
                                Log.e(TAG, "onFailure: " + t.toString());
                        }
                });

        }

        private void testRetrofit() {
//                ApiService apiService = RetrofitMgr.getRetrofitMgr().getApiService();
//                Call<Reponse> token = apiService.getToken();
//                token.enqueue(new Callback<Reponse>() {
//                        @Override
//                        public void onResponse(Response<Reponse> response, Retrofit retrofit) {
//                                boolean success = response.isSuccess();
//                                if (success){
//                                        Reponse body = response.body();
//                                        Log.e(TAG, "onResponse: "+body );
//                                }
//                        }
//
//                        @Override
//                        public void onFailure(Throwable t) {
//                                Log.e(TAG, "onFailure: "+t.toString() );
//                        }
//                });
        }

        @OnClick(R.id.button3)
        public void okhttpAyn() {
                OkHttpMgr.getOkHttpMgr().getDataAyn();
        }
}
