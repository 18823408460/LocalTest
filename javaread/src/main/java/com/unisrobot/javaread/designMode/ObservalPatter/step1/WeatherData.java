package com.unisrobot.javaread.designMode.ObservalPatter.step1;

import java.util.ArrayList;

/**
 * Created by WEI on 2018/5/12.
 */

/**
 * 这里发布数据------, 通知其他的 观察者  更新数据
 */
public class WeatherData implements Subject {
    private ArrayList<Observers> arrayList;
    private float temp;
    private float hum;
    private float pressure;

    public WeatherData() {
        arrayList = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observers observer) {
        arrayList.add(observer);
    }

    @Override
    public void removeObserver(Observers observer) {
        int i = arrayList.indexOf(observer);
        if (i > 0) {
            arrayList.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < arrayList.size(); i++) {
            Observers observers = arrayList.get(i);
            observers.update(temp, hum, pressure);
        }
    }

    public void measureChanges() {
        notifyObservers();
    }

    public void setMeasures(float temp, float hum, float pressure) {
        this.temp = temp;
        this.hum = hum;
        this.pressure = pressure;
        measureChanges();
    }
}
