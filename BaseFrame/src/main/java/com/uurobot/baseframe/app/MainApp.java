package com.uurobot.baseframe.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.uurobot.baseframe.dagger.news.activity.DaggerApplicationComponent;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2018/5/31.
 */

public class MainApp extends Application implements HasActivityInjector{
        public static Context context;
        public static Handler mainHandler;
        public static int mainThreadId;
        public static Thread mainThread;

        public static Context getContext() {
                return context;
        }

        private void _initInjector() {
                // 这里不做注入操作，只提供一些全局单例数据
//                sAppComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
                DaggerApplicationComponent.create().inject(this);
        }

        @Override
        public void onCreate() {
                super.onCreate();
                context = this;
                _initInjector();
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
        @Inject
        DispatchingAndroidInjector<Activity> dispatchingAndroidInjectorActivity;

        @Override
        public AndroidInjector<Activity> activityInjector() {
                return dispatchingAndroidInjectorActivity;
        }
}
