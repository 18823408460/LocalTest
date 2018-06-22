package com.uurobot.baseframe.dagger.news;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/21.
 */

/**
 * 不管@Inejct还是@Provide方法，形成了由它们的依赖关系链接做组成的IOC容器。
 * 这时，应用程序应可以通过一个桥接调用IOC中实例，以完成依赖的注入的，
 * 那就不得不提@Component。@Component一般用来注解接口
 *
 * @Component中使用modules，表明该Component在哪些注入的Module中查找依赖
 * @Component中使用dependencie，表明该Component在哪些注入的Component中查找依赖 添加注入方法，一般使用inject
 * 可以声明方法，提供注入的实例
 */
// 方式一
//@Component(modules = StudentModule.class)


// 方式二：
@TodoScope
@Singleton
@Component(modules = {FruitModule.class})
public interface StudentComponent {
        void inject(DraggerNewsActivity activity);

        AppleBean makeApple();
}
