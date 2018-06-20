package com.uurobot.baseframe.dragger.update;

import com.uurobot.baseframe.app.MainApp;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

/**
 * Created by Administrator on 2018/6/20.
 */

// modules 可以管理多个 module
@Singleton
@Component(modules = {


})
public interface AppComponent extends AndroidInjector<MainApp> {

        @Component.Builder
        abstract class Builder extends AndroidInjector.Builder<MainApp> {
        }
}
