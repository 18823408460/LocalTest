package com.uurobot.baseframe.dragger;

import android.app.Activity;
import android.os.Bundle;

import com.uurobot.baseframe.R;
import com.uurobot.baseframe.app.MainApp;
import com.uurobot.baseframe.dragger.update.DaggerDownloadComponent;
import com.uurobot.baseframe.dragger.update.DownloadModule;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;

/**
 * 依赖注入：就是目标类（目标类需要进行依赖初始化的类，下面都会用目标类一词来指代）
 * 中所依赖的其他的类的初始化过程，不是通过手动编码的方式创建，而是通过技术手段可以把其他的类的已经初始化好的实例自动注入到目标类中。
 * <p>
 * 怎样一步步完成dagger2这样伟大的依赖注入类库的场景来讲解
 * <p>
 * Inject，Component，Module，Provides是dagger2中
 */
public class DraggerActivity extends Activity {

        @Inject
        Tester tester;

        @Inject
        ParamTester paramTester;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                setContentView(R.layout.activity_dragger);

                // 方式一
                DaggerTestComponent.builder().build().inject(this);

                // 方式二
                DaggerTestComponent.builder().testModule(new TestModule()).build().inject(this);

                // 方式三
                DaggerDownloadComponent.builder().applicationComponent(MainApp.getAppComponent())
                        .downloadModule(new DownloadModule(this)) // 如果是无参构造，这里可以不用调用，因为默认会调用无参数构造
                        .build().injectActivity(this);
                tester.printHello();
                paramTester.printHello();


        }
}
