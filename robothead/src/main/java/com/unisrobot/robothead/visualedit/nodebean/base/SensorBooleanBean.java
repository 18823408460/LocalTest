package com.unisrobot.robothead.visualedit.nodebean.base;

/**
 * Created by WEI on 2018/7/2.
 */

public class SensorBooleanBean {
    public String name;
    public String pos;
    public boolean state;

    public SensorBooleanBean() {
    }

    public SensorBooleanBean(String name, boolean state) {
        this.name = name;
        this.state = state;
    }
}