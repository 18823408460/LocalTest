package com.unisrobot.firstmodule;

import android.app.Application;
import android.util.Log;

/**
 * Created by Administrator on 2018/4/13.
 */

public class FirstMoudleMainApp extends Application {
        private static final String TAG = "FirstMoudleMainApp";
        @Override
        public void onCreate() {
                super.onCreate();
                Log.e(TAG, "onCreate: ");
               // CrashHandler.getInstance().init(getApplicationContext());
        }
}
