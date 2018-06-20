package com.uurobot.baseframe.dragger;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/20.
 */

/**
 * 通过这个标记，Dragger会去实现这个类
 * <p>
 * 这是一个桥梁，连接 依赖方 和 被 依赖方。。。Component叫另外一个名字注入器（Injector）
 * Component现在是一个注入器，就像注射器一样，Component会把目标类依赖的实例注入到目标类中，来初始化目标类中的依赖
 */
//@Component
@Component(modules = TestModule.class)
public interface TestComponent {
        void inject(DraggerActivity activity);
}
