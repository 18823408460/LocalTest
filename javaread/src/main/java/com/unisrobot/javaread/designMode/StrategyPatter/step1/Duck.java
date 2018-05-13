package com.unisrobot.javaread.designMode.StrategyPatter.step1;

/**
 * Created by WEI on 2018/5/12.
 */

public abstract class Duck {

    public void quack() {
        System.out.println("quack");
    }

    public void swim() {
        System.out.println("swim");
    }
    // 结果发现所有的 鸭子都会飞了---橡皮鸭其实不能飞，显然这样写有问题
    public void fly() {
        System.out.println("fly");
    }

    //不同种类的鸭子的 外观 不一样， 交给子类实现
    public abstract void display();
}
