package com.unisrobot.javaread.designMode.ObservalPatter.step2;

import com.unisrobot.javaread.designMode.ObservalPatter.step1.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by WEI on 2018/5/12.
 */

public class Display3 implements DisplayElement, Observer {
    private float temp;
    private float hum;

    public Display3(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("display1====" + temp);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData2) {
            WeatherData2 weatherData2 = (WeatherData2) o;
            hum = weatherData2.getHum();
            display();
        }
    }
}
