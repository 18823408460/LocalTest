package com.unisrobot.javaread.designMode.DecoratePatter.step2.tiaoliao;

import com.unisrobot.javaread.designMode.DecoratePatter.step2.Beverage;
import com.unisrobot.javaread.designMode.DecoratePatter.step2.Condiment;

/**
 * Created by WEI on 2018/5/12.
 */
// 调料作为  装饰这，，，
public class Whip extends Condiment {

    private Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + 1.3;
    }

    @Override
    public String setDescription() {
        return beverage.getDescription() + ",Whip";
    }
}
