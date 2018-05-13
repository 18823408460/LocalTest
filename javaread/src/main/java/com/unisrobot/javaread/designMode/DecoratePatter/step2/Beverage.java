package com.unisrobot.javaread.designMode.DecoratePatter.step2;

/**
 * Created by WEI on 2018/5/12.
 */

public abstract class Beverage {
    public String description = "Unknonw Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
