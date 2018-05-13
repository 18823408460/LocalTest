package com.unisrobot.javaread.designMode.DecoratePatter.step2.yinliao;

import com.unisrobot.javaread.designMode.DecoratePatter.step2.Beverage;

/**
 * Created by WEI on 2018/5/12.
 */

public class HouseBlend extends Beverage {
    public HouseBlend() {
        this.description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 2.2;
    }
}
