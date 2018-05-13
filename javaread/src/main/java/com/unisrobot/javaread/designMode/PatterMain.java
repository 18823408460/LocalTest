package com.unisrobot.javaread.designMode;

/**
 * Created by WEI on 2018/5/12.
 */

/**
 * 测试类
 *
 *
 * 子类 extends Duck{
 public void fly(){}
 public void quack(){}
 } // 采用继承，因为鸭子的行为在子类中是不断改变的，所以不恰当

 子类 extentd Duck implements Flyable{

 } // 采用继承和接口，继承共有属性，吧可变的行为 改成接口，
 但是接口没有实现,不具备代码复用性， 如果需求改变，所有的地方都的修改.


 》》设计原则： 把变化的行为和不变的行为 分开。(还要有复用)
 针对接口编程，而不是针对实现编程()
 多用组合， 少用继承

 Duck{
 FlyBehavior flyBehavior ;
 QuackBehavior queckBehavior;

 abstact void display() ;

 }

 1. 策略模式:  定义算法组， 他们之间可以相互替换

 */
public class PatterMain {
    public static void main(String[] args) {

    }
}
