package com.unisrobot.javaread.designMode.StrategyPatter.step2.impl;

import com.unisrobot.javaread.designMode.StrategyPatter.step2.interfaces.QuackBehavior;

/**
 * Created by WEI on 2018/5/12.
 */

public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}
