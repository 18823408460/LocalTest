package com.uurobot.baseframe.dragger;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/20.
 */

public class ParamTester {
        private static final String TAG = "ParamTester";

        @Inject
        public ParamTester() {
        }

        public void printHello() {
                Log.e(TAG, "printHello: parameTester");
        }
}
