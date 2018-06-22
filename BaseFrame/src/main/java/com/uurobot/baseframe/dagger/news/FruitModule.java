package com.uurobot.baseframe.dagger.news;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/21.
 */

/**
 * 1. 这种方式 生成的对象，对应的对象不需要用 @Inject 来标识，，
 *
 *
 * 2. 多个构造，只能有一个可以被 @Inject）
 *
 *
 */
@Module
public class FruitModule {
        @Type("color")
        @Provides
        public AppleBean2 provideApple2() {
                return new AppleBean2();
        }


        @Type("name")
        @Provides
        public AppleBean2 provideApple() {
                return new AppleBean2("helloApple");
        }

}
