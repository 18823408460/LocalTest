package com.uurobot.baseframe.dagger.news.activity;

import com.uurobot.baseframe.dagger.news.DraggerNewsActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by Administrator on 2018/6/23.
 */

@Subcomponent(modules = AndroidInjectionModule.class)
public interface MainSubComponent extends AndroidInjector<DraggerNewsActivity> {


        @Subcomponent.Builder
        abstract class Builder extends AndroidInjector.Builder<DraggerNewsActivity> {

        }
}
