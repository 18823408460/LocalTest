package com.uurobot.baseframe.dagger.news;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.uurobot.baseframe.dagger.news.bean.BananaBean;
import com.uurobot.baseframe.dagger.news.bean.GreenTeaBean;
import com.uurobot.baseframe.dagger.news.bean.OrangeBean;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

/**
 * Created by Administrator on 2018/6/21.
 
 如果依赖实例的注入来源是@Provides方法时，@Provides方法必须被@Scope注解；如果依赖实例的注入来源是@Inject注解的构造函数时，实例类必须被@Scope注解。这样@Scope注解才会有效。也就是说，@Scope实际上是对注入器的控制。

Scope控制的实例的注入器是当前Component之内的实例注入器，而不会影响其他的Component中的实例注入器
 */
//https://blog.csdn.net/io_field/article/details/71083516

/**
 * 1. 多个 module 生产  scope 注解的实例，会取缓存吗？
 *
 * 2.一般都是在Application中生成一个AppComponent，然后其他的功能模块的Component依赖于AppComponent，作为AppComponent的子组件。
 *   可是，对于将子组建添加到父组件有两种方式。（子组件是继承和扩展父组件的对象的组件）
 *   》 dependencies
 *   》 subcomponents
 *
 *
 *
 * 3.声明子组件，与普通组件声明类似，可以通过编写一个抽象类或接口来创建一个子组件，该类或接口声明了返回应用程序相关的类型的抽象方法。
 * 可以使用@Component,也可以使用@Subcomponent注解，这个没有一定的强制性
 * 》
 * 》与@Component注解不同的是，使用@Subcomponent注解子组件，必须声明其xxComponent.Builder，否则编译时，会报错。
 *
 *
 *
 *
 */
public class DraggerNewsActivity extends Activity {
        private static final String TAG = "DraggerNewsActivity";
        @Inject
        public StudentBean studentBean;

        @Type("color")
        @Inject
        AppleBean2 appleBean2;

        @Type("name") //告诉 Dagger用哪个来注入。。
        @Inject
        AppleBean2 appleBean3;

        @Inject
        ScopeBean scopeBean;

        @Inject
        ScopeBean scopeBean2;

        @Inject
        ScopeBean scopeBean3;

        @Inject
        SingletonBean singletonBean;

        @Inject
        SingletonBean singletonBean2;


        @Inject
        OrangeBean orangeA;
        @Inject
        OrangeBean orangeB;

        @Inject
        BananaBean bananaA;
        @Inject
        BananaBean bananaB;

        @Inject
        GreenTeaBean greenTeaA;
        @Inject
        GreenTeaBean greenTeaB;

        @Inject
        AppleBean appleBeanA;
        @Inject
        AppleBean appleBeanB;


        //Lazy只有第一次调用get()方法时，调用构造函数实例化对象，然后将该对象缓存。以后再调用get()方法时，都会返回缓存的实例，并且不会再调用构造函数，创建实例
        @Inject
        Lazy<BananaBean> bananaBeanLazy;

        //不同的lazy 实例，会创建不同的对象
        @Inject
        Lazy<BananaBean> bananaBeanLazy1;

        // 每次调用get()方法，都会调用BananaBean的构造函数，实例化一个新的对象，而不像Lazy<T>那样将第一次创建的实例缓存
        // 尽管是Provider<T>提供注入的对象，但@Scope注解的功能依然存在(如果对象被@Scope注解， 则只有一次)
        @Inject
        Provider<BananaBean> bananaBeanProvider;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                StudentComponent studentComponent = DaggerStudentComponent.builder().build();
                studentComponent.inject(this);
                Log.e(TAG, "onCreate: " + studentBean);
                AppleBean appleBean = studentComponent.makeApple();
                Log.e(TAG, "onCreate: " + appleBean);

                Log.e(TAG, "onCreate:appleBean2= " + appleBean2);
                Log.e(TAG, "onCreate:appleBean3= " + appleBean3);


                Log.e(TAG, "onCreate: scopeBean=" + scopeBean.hashCode());
                Log.e(TAG, "onCreate: scopeBean2=" + scopeBean2.hashCode());
                Log.e(TAG, "onCreate: scopeBean3=" + scopeBean3.hashCode());
                Log.e(TAG, "onCreate: singletonBean=" + singletonBean.hashCode());
                Log.e(TAG, "onCreate: singletonBean2=" + singletonBean2.hashCode());

                BananaBean bananaBean = bananaBeanLazy.get();
                BananaBean bananaBean1 = bananaBeanLazy.get();

                Log.e(TAG, "bananaBeanLazy= "+bananaBean );
                Log.e(TAG, "bananaBeanLazy1= "+bananaBean1 );

                BananaBean bananaBean2 = bananaBeanLazy1.get();
                BananaBean bananaBean3 = bananaBeanLazy1.get();
                Log.e(TAG, "bananaBean2= "+bananaBean2 );
                Log.e(TAG, "bananaBean3= "+bananaBean3 );

                BananaBean bananaBean4 = bananaBeanProvider.get();
                BananaBean bananaBean5 = bananaBeanProvider.get();
                Log.e(TAG, "bananaBean4= "+bananaBean4 );
                Log.e(TAG, "bananaBean5= "+bananaBean5 );
        }
}
