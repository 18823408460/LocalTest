package com.unisrobot.localtest;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Administrator on 2018/4/13.
 */

public class MainApplication extends MultiDexApplication {

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
