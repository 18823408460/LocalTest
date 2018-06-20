package com.uurobot.mvpmodule.dragger;

import com.uurobot.mvpmodule.App;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author woong
 */
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
})
public interface AppComponent {
    void inject(App app);
}
