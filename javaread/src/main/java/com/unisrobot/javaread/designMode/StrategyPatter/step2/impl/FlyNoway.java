package com.unisrobot.javaread.designMode.StrategyPatter.step2.impl;

import com.unisrobot.javaread.designMode.StrategyPatter.step2.interfaces.FlyBehavior;

/**
 * Created by WEI on 2018/5/12.
 */

public class FlyNoway implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("fly no way");
    }
}
