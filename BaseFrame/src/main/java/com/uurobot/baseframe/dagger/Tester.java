package com.uurobot.baseframe.dragger;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/20.
 */

public class Tester {
        private static final String TAG = Tester.class.getSimpleName();



        /**
         * @Inject 标记构造方法来生成依赖对象的方式有它的局限性, 如：
         * 1. 第三方类，无法修改
         * 2. 接口是没有构造的
         *
         * ，， 这个时候，就需要Provied 标记
         */
        @Inject
        public Tester() {
                Log.e(TAG, "Tester: Construct ========= ");
        }

        public void printHello() {
                Log.e(TAG, "printHello: heloo======== ");
        }
}
