package com.uurobot.baseframe.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by WEI on 2018/6/6.
 */

public class ActivityManager {
        private volatile static ActivityManager activityManager;
        private Stack<Activity> activityStack;


        private ActivityManager() {
                activityStack = new Stack<>();
        }

        public static ActivityManager getInstance() {
                if (activityManager == null) {
                        synchronized (ActivityManager.class) {
                                if (activityManager == null) {
                                        activityManager = new ActivityManager();
                                }
                        }
                }
                return activityManager;
        }

        public void add(Activity activity) {
                if (activityStack.contains(activity)) {

                }
                activityStack.add(activity);
        }

        public void remove(Activity activity) {
                if (activityStack.contains(activity)) {
                        activity.finish();
                        activityStack.remove(activity);
                }
        }

        public void removeAll() {
                for (int i = activityStack.size(); i >= 0; i--) {
                        Activity remove = activityStack.remove(i);
                        remove.finish();
                }
        }

        public int getStackSize() {
                return activityStack.size();
        }
}
