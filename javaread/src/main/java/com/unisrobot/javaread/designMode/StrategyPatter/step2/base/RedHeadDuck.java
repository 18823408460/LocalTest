package com.unisrobot.javaread.designMode.StrategyPatter.step2.base;

import com.unisrobot.javaread.designMode.StrategyPatter.step2.impl.FlyWithWings;
import com.unisrobot.javaread.designMode.StrategyPatter.step2.impl.Quack;

/**
 * Created by WEI on 2018/5/12.
 */

public class RedHeadDuck extends Duck {
    @Override
    public void display() {
        System.out.println("红头鸭");
    }

    public RedHeadDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }
}
