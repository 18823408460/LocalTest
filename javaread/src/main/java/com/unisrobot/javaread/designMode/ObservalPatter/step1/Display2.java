package com.unisrobot.javaread.designMode.ObservalPatter.step1;

/**
 * Created by WEI on 2018/5/12.
 */

public class Display2 implements DisplayElement, Observers {
    private float temp;
    private Subject weatherData;

    public Display2(Subject subject) {
        this.weatherData = subject;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("display1");
    }

    @Override
    public void update(float temp, float hum, float pressure) {
        this.temp = temp;
    }
}
