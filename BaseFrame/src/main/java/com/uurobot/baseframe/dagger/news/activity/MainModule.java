package com.uurobot.baseframe.dagger.news.activity;

import android.app.Activity;

import com.uurobot.baseframe.activitys.MainActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by Administrator on 2018/6/23.
 */

@Module(subcomponents = MainSubComponent.class)
public abstract class MainModule {
        @Binds
        @IntoMap
        @ActivityKey(MainActivity.class)
        abstract AndroidInjector.Factory<? extends Activity>
        bindYourActivityInjectorFactory(MainSubComponent.Builder builder);
}

