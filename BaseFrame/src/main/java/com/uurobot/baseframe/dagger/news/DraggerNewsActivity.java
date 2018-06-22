package com.uurobot.baseframe.dagger.news;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/21.
 */
//https://blog.csdn.net/io_field/article/details/71083516
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

        @Inject
        ScopeBean scopeBean;

        @Inject
        ScopeBean scopeBean2;

        @Inject
        ScopeBean scopeBean3;

        @Inject
        SingletonBean singletonBean;

        @Inject
        SingletonBean singletonBean2;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                StudentComponent studentComponent = DaggerStudentComponent.builder().build();
                studentComponent.inject(this);
                Log.e(TAG, "onCreate: " + studentBean);
                AppleBean appleBean = studentComponent.makeApple();
                Log.e(TAG, "onCreate: " + appleBean);

                Log.e(TAG, "onCreate:appleBean2= " + appleBean2);
                Log.e(TAG, "onCreate:appleBean3= " + appleBean3);


                Log.e(TAG, "onCreate: scopeBean=" + scopeBean.hashCode());
                Log.e(TAG, "onCreate: scopeBean2=" + scopeBean2.hashCode());
                Log.e(TAG, "onCreate: scopeBean3=" + scopeBean3.hashCode());
                Log.e(TAG, "onCreate: singletonBean=" + singletonBean.hashCode());
                Log.e(TAG, "onCreate: singletonBean2=" + singletonBean2.hashCode());
        }
}
