package com.unisrobot.localtest.netRequest;

/**
 * Created by Administrator on 2018/4/11.
 */

import android.content.Context;
import android.util.Log;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.unisrobot.localtest.threadPool.ThreadPoolMgr;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 由移动支付Square公司贡献
 */
// 从 1.0.0 --- 2.7.5 ,,然后升级到了Okhttp3（内部修改比较大）
public class OkHttpMgr {

        private static final String TAG = "OkHttpMgr";

        private static OkHttpMgr okHttpMgr;

        private OkHttpClient okHttpClient;

        public static OkHttpMgr getOkHttpMgr() {
                if (okHttpMgr == null) {
                        synchronized (OkHttpMgr.class) {
                                if (okHttpMgr == null) {
                                        okHttpMgr = new OkHttpMgr();
                                }
                        }
                }
                return okHttpMgr;
        }

        private OkHttpMgr() {
                okHttpClient = new OkHttpClient();
                okHttpClient.setConnectTimeout(3000, TimeUnit.MILLISECONDS);
                okHttpClient.setReadTimeout(3000, TimeUnit.MILLISECONDS);
                okHttpClient.setWriteTimeout(3000, TimeUnit.MILLISECONDS);
        }

        /**
         * 同步获取
         */
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

        /**
         * 异步获取
         */
        public void getDataAyn() {
                ThreadPoolMgr.getInstance().excute(new Runnable() {
                        @Override
                        public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(Cons.baseUrl)
                                        .append("appId=" + Cons.APPID)
                                        .append("&")
                                        .append("secret=" + Cons.SECRET);
                                Request request = new Request.Builder().url(stringBuilder.toString()).build();
                                okHttpClient.newCall(request).enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Request request, IOException e) {
                                                Log.e(TAG, "run: onFailure===");
                                        }

                                        @Override
                                        public void onResponse(Response response) throws IOException {
                                                boolean successful = response.isSuccessful();
                                                if (successful) {
                                                        String s = response.body().toString();
                                                        //这里要注意。。。。。。。。。。。。
                                                        String string = response.body().string();
                                                        Log.e(TAG, "run: response===" + string);
                                                }

                                        }
                                });

                        }
                });
        }

        public void postData() {
                ThreadPoolMgr.getInstance().excute(new Runnable() {
                        @Override
                        public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(Cons.baseUrl)
                                        .append("appId=" + Cons.APPID)
                                        .append("&")
                                        .append("secret=" + Cons.SECRET);

                                // MediaType用于描述Http请求和响应体的内容类型，也就是Content-Type。
                                MediaType mediatype = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
                                String jsonStr = "{\"username\":\"lisi\",\"nickname\":\"李四\"}";//json数据.
                                RequestBody requestBody = RequestBody.create(mediatype, jsonStr);
                                Request request = new Request.Builder()
                                        .url(stringBuilder.toString())
                                        .post(requestBody)
                                        .build();
                                try {
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

        // 怎么设置tag，怎么cancle
        public void cancle() {

        }
}
