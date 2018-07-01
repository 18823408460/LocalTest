package com.unisrobot.robothead.visualedit.nodebean.common;

import com.unisrobot.robothead.visualedit.bean.SensorNumber;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入型参数的值 ：这个bean 是放在AppendA,B 中
 */

public class InputParamBean {
    private String staticNumber;
    private List<SensorNumber> sensorNumberList;

    public InputParamBean() {
    }

    public void addStaticNumber(String number) {
        this.staticNumber += number;
    }

    public void addSensorNumber(SensorNumber sensorNumber) {
        if (sensorNumberList == null) {
            sensorNumberList = new ArrayList<>();
        }
        sensorNumberList.add(sensorNumber);
    }
    public void addSensorNumber(List<SensorNumber> list) {
        if (sensorNumberList == null) {
            sensorNumberList = new ArrayList<>();
        }
        sensorNumberList.addAll(list);
    }
    public List<SensorNumber> getSensorNumberList() {
        return sensorNumberList;
    }

    public String getStaticNumber() {
        return staticNumber;
    }
}
