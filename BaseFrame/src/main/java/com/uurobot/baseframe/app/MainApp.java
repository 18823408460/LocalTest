package com.uurobot.baseframe.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.widget.ListView;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2018/5/31.
 */

public class MainApp extends Application {
    public static Context context;
    public static Handler mainHandler;
    public static int mainThreadId;
    public static Thread mainThread;


    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mainHandler = new Handler();
        mainThreadId = android.os.Process.myPid();
        mainThread = Thread.currentThread();
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
