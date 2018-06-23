package com.uurobot.baseframe.dagger.news.bean;

import com.uurobot.baseframe.dagger.news.AppleBean;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2018/6/23.
 */
@Subcomponent(modules = FruitModules.class)
public interface AppleSubcomponent {

        AppleBean supplyApple();

        @Subcomponent.Builder
        interface Builder{
                Builder appleModule(FruitModules module);
                AppleSubcomponent build();
        }
}
