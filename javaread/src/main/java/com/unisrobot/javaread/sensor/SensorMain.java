package com.unisrobot.javaread.sensor;

/**
 * Created by WEI on 2018/7/6.
 */

public class SensorMain {





    public static void main(String[] args) {
        BaseSensor<Float> wendu = new WenduSensor();
        Float aFloat = wendu.parseData();
    }











   //  server
    public void parseData(byte[] data) {
            BaseSensor baseSensor ;


    }
}
