package com.uurobot.baseframe.dragger.update;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/20.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
        Context getContext();
        // ....可以增加其他的全局方法
}
