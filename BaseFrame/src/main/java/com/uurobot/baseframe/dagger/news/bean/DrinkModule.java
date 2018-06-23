package com.uurobot.baseframe.dagger.news.bean;

import com.uurobot.baseframe.dagger.news.AppleBean;
import com.uurobot.baseframe.dagger.news.TodoScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/23.
 */

@Module
public class DrinkModule {
        @TodoScope
        @Provides
        public AppleBean providerApple() {
                return new AppleBean();
        }

        @TodoScope
        @Provides
        public GreenTeaBean providerDrink() {
                return new GreenTeaBean();
        }
}
