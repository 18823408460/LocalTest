package com.unisrobot.robothead;

import android.app.Application;
import android.content.Context;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by Administrator on 2018/5/16.
 */

public class MainApp extends Application {
        private static Context context;

        public static Context getContext() {
                return context;
        }

        @Override
        public void onCreate() {
                super.onCreate();
                context = getApplicationContext();
                initIfytekSdk();
        }

        private void initIfytekSdk() {
                StringBuffer param = new StringBuffer();
                param.append("appid=" + "5a3b8f6b");
                param.append(",");
                param.append(SpeechConstant.ENGINE_MODE + "=" + SpeechConstant.MODE_MSC);
                SpeechUtility.createUtility(this, param.toString());
        }
}
