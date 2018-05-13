package com.unisrobot.javaread.designMode.ObservalPatter;

/**
 * Created by WEI on 2018/5/12.
 */

import com.unisrobot.javaread.designMode.ObservalPatter.step1.Display1;
import com.unisrobot.javaread.designMode.ObservalPatter.step1.Display2;
import com.unisrobot.javaread.designMode.ObservalPatter.step1.WeatherData;

/**
 * 观察者 要做到 松耦合...
 *
 *
 *  设计原则:::::让交互类  之间的做到松耦合
 */
public class ObserverMain {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        //这两个观察者 应该订阅 数据
        Display1 display1 = new Display1(weatherData);
        Display2 display2 = new Display2(weatherData);

        //通知更新数据
        weatherData.setMeasures(1f, 2f, 3f);
    }
}
