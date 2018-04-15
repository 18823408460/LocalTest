package com.unisrobot.localtest.robot.Sensor;

/**
 * Created by Administrator on 2018/4/11.
 */

public interface SensorReceiver {
    void onError(Throwable p1);
    void onReceived(SensorEvent p1);
}
