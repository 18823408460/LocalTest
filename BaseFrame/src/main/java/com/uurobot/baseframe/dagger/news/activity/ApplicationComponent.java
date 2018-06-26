package com.uurobot.baseframe.dagger.news.activity;

import com.uurobot.baseframe.app.MainApp;

import dagger.Component;
import dagger.Module;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Administrator on 2018/6/23.
 */

@Component(modules = {AndroidInjectionModule.class,MainModule.class})
public interface ApplicationComponent {
        void inject(MainApp app);
}
