package com.uurobot.baseframe.dagger.news;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/21.
 */

public class DraggerNewsActivity extends Activity {
        private static final String TAG = "DraggerNewsActivity";

        @Inject
        public StudentBean studentBean;

        @Type("color")
        @Inject
        AppleBean2 appleBean2;

        @Type("name") //告诉 Dagger用哪个来注入。。
        @Inject
        AppleBean2 appleBean3;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                StudentComponent studentComponent = DaggerStudentComponent.builder().build();
                studentComponent.inject(this);
                Log.e(TAG, "onCreate: " + studentBean);
                AppleBean appleBean = studentComponent.makeApple();
                Log.e(TAG, "onCreate: " + appleBean);

                Log.e(TAG, "onCreate:appleBean2= "+appleBean2 );
                Log.e(TAG, "onCreate:appleBean3= "+appleBean3);
        }
}
