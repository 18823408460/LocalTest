package com.uurobot.baseframe.app;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2018/5/31.
 */

public class MainApp extends Application {
        @Override
        public void onCreate() {
                super.onCreate();
                initOkHttp();
        }

        private void initOkHttp() {
                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        // .addInterceptor(new LoggerInterceptor("TAG"))
                        .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                        .readTimeout(10000L, TimeUnit.MILLISECONDS)
                        //其他配置
                        .build();

                OkHttpUtils.initClient(okHttpClient);
        }
}
