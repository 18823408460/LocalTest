package com.unisrobot.javaread.designMode.ObservalPatter.step2;

import java.util.Observable;

/**
 * Created by WEI on 2018/5/12.
 */

/**
 * 这里发布数据------, 通知其他的 观察者  更新数据
 */
public class WeatherData2 extends Observable {
    private float temp;
    private float hum;
    private float pressure;

    public float getTemp() {
        return temp;
    }

    public float getHum() {
        return hum;
    }

    public float getPressure() {
        return pressure;
    }

    public void measureChanges() {
        setChanged();
        notifyObservers();
    }

    public void setMeasures(float temp, float hum, float pressure) {
        this.temp = temp;
        this.hum = hum;
        this.pressure = pressure;
        measureChanges();
    }
}
