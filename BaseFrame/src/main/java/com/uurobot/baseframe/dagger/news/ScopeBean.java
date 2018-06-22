package com.uurobot.baseframe.dagger.news;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/6/22.
 * <p>
 * 在默认情况下，也就是说仅仅使用@Inject注解构造函数，而没有使用@Scope注解类时，
 * 每次依赖注入都会创建一个实例(通过注入类型的构造函数创建实例)。
 * 如果使用了@Scope注解了该类，注入器会缓存第一次创建的实例，然后每次重复注入缓存的实例，而不会再创建新的实例
 *
 *
 * 2. 如果有类注入实例的类被@Scope注解，那么其Component必须被相同的Scope注解
 * 3. 如果有类注入实例的类被@Scope注解, 如果要用到module，则在其生产方法中加 @Scope 就行。（注入来源是@Provides方法时，@Provides方法必须被@Scope注解）
 *
 */
@TodoScope
public class ScopeBean {
        private static final String TAG = "ScopeBean";
        private String data;

        @Inject
        //        @Singleton
        public ScopeBean() {
                Log.e(TAG, "ScopeBean: ..........");
                this.data = "helloSington";
        }

        @Override
        public String toString() {
                return "ScopeBean{" +
                        "data='" + data + '\'' +
                        '}';
        }
}
