package com.uurobot.baseframe.dagger.news;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2018/6/22.
 */

@Singleton //单列
public class SingletonBean {
        private static final String TAG = "SingletonBean";
        private String data;

        @Inject
        public SingletonBean() {
                Log.e(TAG, "SingletonBean: ");
                this.data = "hello SingleTon";
        }

        @Override
        public String toString() {
                return "SingletonBean{" +
                        "data='" + data + '\'' +
                        '}';
        }
}
