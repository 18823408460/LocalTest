package com.unisrobot.javaread.designMode.StrategyPatter.step2.impl;

import com.unisrobot.javaread.designMode.StrategyPatter.step2.interfaces.QuackBehavior;

/**
 * Created by WEI on 2018/5/12.
 */

public class SQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}
