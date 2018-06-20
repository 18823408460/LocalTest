package com.uurobot.baseframe.dragger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2018/6/20.
 */

/**
 * 把封装第三方类库的代码放入Module ,Module其实是一个简单工厂模式，Module里面的方法基本都是创建类实例的方法
 *
 * @Module 一般用来标记类，该注解告知 Dagger2 可以到该类中寻找需要的依赖。上述的 @Provides 则标记提供依赖实例的方法。两者都是一起使用的
 */
@Module
public class TestModule {
        /**
         * @Provides 只能用于标记非构造方法，并且该方法必须在 @Moudle 内部。
         * @Provides 修饰的方法的方法名建议以 provide 开头。
         */
        @Provides
        Tester provideTest() {
                return new Tester();
        }
}
