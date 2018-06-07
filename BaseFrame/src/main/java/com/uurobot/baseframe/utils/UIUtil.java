package com.uurobot.baseframe.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.app.MainApp;

/**
 * Created by WEI on 2018/6/6.
 */

public class UIUtil {

        public static Handler getMainHandler() {
                return MainApp.mainHandler;
        }

        public static Context getAppContext() {
                return MainApp.context;
        }

        public static String getString(int strId) {
                return getAppContext().getResources().getString(strId);
        }

        public static String[] getStringArray(int strId) {
                return getAppContext().getResources().getStringArray(strId);
        }

        public static View inflate(int viewId) {
                return View.inflate(getAppContext(), viewId, null);
        }

        public static void runUiThread(Runnable runnable) {
                long id = Thread.currentThread().getId();
                if (id == MainApp.mainThreadId) {
                        runnable.run();
                } else {
                        MainApp.mainHandler.post(runnable);
                }
        }


}
