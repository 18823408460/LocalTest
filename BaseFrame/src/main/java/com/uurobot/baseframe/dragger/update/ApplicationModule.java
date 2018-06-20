package com.uurobot.baseframe.dragger.update;

import android.content.Context;

import com.uurobot.baseframe.app.MainApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/20.
 */

// 第三方类的生产
@Module
public class ApplicationModule {
        private MainApp mApplication;

        public ApplicationModule(MainApp mApplication) {
                this.mApplication = mApplication;
        }

        // 这里增加其他的全局的方法
        @Provides
        @Singleton
        Context provideApplicationContext() {
                return mApplication.getApplicationContext();
        }
}
