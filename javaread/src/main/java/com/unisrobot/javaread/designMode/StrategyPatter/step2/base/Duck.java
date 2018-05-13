package com.unisrobot.javaread.designMode.StrategyPatter.step2.base;

import com.unisrobot.javaread.designMode.StrategyPatter.step2.interfaces.FlyBehavior;
import com.unisrobot.javaread.designMode.StrategyPatter.step2.interfaces.QuackBehavior;

/**
 * Created by WEI on 2018/5/12.
 */

public abstract class Duck {
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public void swim() {
        System.out.println("swim");
    }

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }


    //不同种类的鸭子的 外观 不一样， 交给子类实现
    public abstract void display();
}
