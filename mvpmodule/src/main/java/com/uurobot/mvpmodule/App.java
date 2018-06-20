package com.uurobot.mvpmodule;

import android.app.Activity;
import android.app.Application;

import com.uurobot.mvpmodule.dragger.AppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Administrator on 2018/6/20.
 */

public class App extends Application implements HasActivityInjector {
        @Override
        public void onCreate() {
                super.onCreate();
                DaggerAppComponent.create().inject(this);
        }

        @Inject
        DispatchingAndroidInjector<Activity> dispatchingAndroidInjectorActivity;

        @Override
        public AndroidInjector<Activity> activityInjector() {
                return dispatchingAndroidInjectorActivity;
        }
}
