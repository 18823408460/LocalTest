package com.unisrobot.robothead.uart.bean;

/**
 * Created by WEI on 2018/5/11.
 */

public class PowerBean {
    public int batter;
    public boolean isCharging;

    public PowerBean(int batter, boolean isCharging) {
        this.batter = batter;
        this.isCharging = isCharging;
    }

    @Override
    public String toString() {
        return "PowerBean{" +
                "batter=" + batter +
                ", isCharging=" + isCharging +
                '}';
    }
}
