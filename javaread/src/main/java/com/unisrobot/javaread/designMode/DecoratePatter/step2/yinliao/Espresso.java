package com.unisrobot.javaread.designMode.DecoratePatter.step2.yinliao;

import com.unisrobot.javaread.designMode.DecoratePatter.step2.Beverage;

/**
 * Created by WEI on 2018/5/12.
 */

public class Espresso extends Beverage {
    public Espresso() {
        this.description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.1;
    }
}
