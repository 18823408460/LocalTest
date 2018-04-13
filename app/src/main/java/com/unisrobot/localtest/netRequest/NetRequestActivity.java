package com.unisrobot.localtest.netRequest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.unisrobot.localtest.R;
import com.unisrobot.localtest.netRequest.bean.ApiService;
import com.unisrobot.localtest.netRequest.bean.Reponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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
        }

        @OnClick(R.id.button2)
        public void okhttpSyn() {
                testRetrofit();
        }

        private void testRetrofitf(){
                ApiService apiService = RetrofitMgr.getRetrofitMgr().getApiService();
                Call<String> moiveInfo = apiService.getMoiveInfo();
                moiveInfo.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Response<String> response, Retrofit retrofit) {
                                boolean success = response.isSuccess();
                                if (success){
                                        String body = response.body();
                                        Log.e(TAG, "onResponse: "+body );
                                }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                                Log.e(TAG, "onFailure: "+t.toString() );
                        }
                });
        }

        private void testRetrofit(){
                ApiService apiService = RetrofitMgr.getRetrofitMgr().getApiService();
                Call<Reponse> token = apiService.getToken();
                token.enqueue(new Callback<Reponse>() {
                        @Override
                        public void onResponse(Response<Reponse> response, Retrofit retrofit) {
                                boolean success = response.isSuccess();
                                if (success){
                                        Reponse body = response.body();
                                        Log.e(TAG, "onResponse: "+body );
                                }
                        }

                        @Override
                        public void onFailure(Throwable t) {
                                Log.e(TAG, "onFailure: "+t.toString() );
                        }
                });
        }

        @OnClick(R.id.button3)
        public void okhttpAyn() {
                OkHttpMgr.getOkHttpMgr().getDataAyn();
        }
}
