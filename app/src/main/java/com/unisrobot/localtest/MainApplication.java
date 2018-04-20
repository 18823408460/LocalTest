package com.unisrobot.localtest;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by Administrator on 2018/4/13.
 */

public class MainApplication extends MultiDexApplication {
        private static final String TAG = "MainApplication";
        private static Context context;

        @Override
        public void onCreate() {
                super.onCreate();
                MultiDex.install(this);
                context = this.getApplicationContext();
        }

        public static Context getContext() {
                return context;
        }


}
