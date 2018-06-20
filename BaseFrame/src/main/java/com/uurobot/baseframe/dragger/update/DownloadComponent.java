package com.uurobot.baseframe.dragger.update;

import com.uurobot.baseframe.dragger.DraggerActivity;

import dagger.Component;

/**
 * Created by Administrator on 2018/6/20.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = DownloadModule.class)
public interface DownloadComponent {
        void injectActivity(DraggerActivity activity);
}
