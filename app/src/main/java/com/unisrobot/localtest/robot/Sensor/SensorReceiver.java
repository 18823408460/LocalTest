package com.unisrobot.localtest.robot.Sensor;

/**
 * Created by Administrator on 2018/4/11.
 */

public interface SensorReceiver {
        public abstract void onError(Throwable p1);


        public abstract void onReceived(SensorEvent p1);
}
