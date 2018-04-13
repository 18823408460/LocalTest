package com.unisrobot.localtest.netRequest;


import android.util.Log;

import com.unisrobot.localtest.threadPool.ThreadPoolMgr;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/4/11.
 * <p>
 * 拦截，分发，缓存 ？？？？？？
 * <p>
 * 拦截，分发，缓存 ？？？？？？
 * <p>
 * 拦截，分发，缓存 ？？？？？？
 * <p>
 * 拦截，分发，缓存 ？？？？？？
 */

// 从 3.0.0 --- 3.10.0至今

/**
 * 拦截，分发，缓存 ？？？？？？
 */

/**
 *  okhttp3 和 okhttp 的区别：
 *
 *  1. okhttp3 增加builder 创建。
 *  2. OkHttpClient参数配置,okhttp3需要通过builder来设置。。
 *  3. 3.0 之后新增了两个类Cookiejar、Cookie两个类，开放接口，需要用户自己去实现cookie的配管理
 *  4. 异步回调不一样：Call和Callback不同，okhttp3对Call做了更简洁的封装，okhttp3 Call是个接口，
 *  okhttp的call是个普通class，一定要注意，无论哪个版本，call都不能执行多次，多次执行需要重新创建
 *
 *  5. OkHttpClient的Cancel方法去掉，3.0之前我们去掉call 可以直接使用mOkHttpClient.cancel(tag);{
 *            synchronized (mOkHttpClient.dispatcher().getClass()) {
                 for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
                 if (tag.equals(call.request().tag())) call.cancel();
                 }

                 for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
                 if (tag.equals(call.request().tag())) call.cancel();
                 }
 }
 *  }
 *
 */
public class Okhttp3Mgr {

        private static final String TAG = "Okhttp3Mgr";

        private volatile  static Okhttp3Mgr okHttpMgr;

        private OkHttpClient okHttpClient;

        public static Okhttp3Mgr getOkHttpMgr() {
                if (okHttpMgr == null) {
                        synchronized (OkHttpMgr.class) {
                                if (okHttpMgr == null) {
                                        okHttpMgr = new Okhttp3Mgr();
                                }
                        }
                }
                return okHttpMgr;
        }

        private Okhttp3Mgr() {
                // 通过构造创建：build中默认设置参数
//                okHttpClient = new OkHttpClient();

                // 通过构造者模式先设置后build生成的方式。
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                okHttpClient = builder.connectTimeout(3000, TimeUnit.MILLISECONDS)
                        .addInterceptor(new Interceptor() { // applicationcen 层拦截器
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                        return null;
                                }
                        })
                        .addNetworkInterceptor(new Interceptor() { // network层拦截器
                                @Override
                                public Response intercept(Chain chain) throws IOException {
                                        return null;
                                }
                        })
                        .readTimeout(3000, TimeUnit.MILLISECONDS)
                        .writeTimeout(3000, TimeUnit.MILLISECONDS)
                        .build();
        }

        public void getDataSyn() {
                ThreadPoolMgr.getInstance().excute(new Runnable() {
                        @Override
                        public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(Cons.baseUrl)
                                        .append("appId=" + Cons.APPID)
                                        .append("&")
                                        .append("secret=" + Cons.SECRET);
                                Request request = new Request.Builder().url(stringBuilder.toString()).build();
                                try {
                                        Log.e(TAG, "run: response==start=");
                                        Response execute = okHttpClient.newCall(request).execute();
                                        Log.e(TAG, "run: response===" + execute);
                                }
                                catch (IOException e) {
                                        e.printStackTrace();
                                        Log.e(TAG, "run: IOException=" + e);
                                }
                        }
                });
        }

}
