package com.unisrobot.robothead.visualedit.bean;

/**
 * Created by Administrator on 2018/6/30.
 * <p>
 * 需要动态获取的 传感器的值：
 * <p>
 * 如：
 * 左侧 温湿度传感器 的 温度 值
 * 右侧 超神波传感器的  值
 */

public class SensorNumber {
        public String postion;
        public String name;
        public String valueName; // 这里可以为空
        public String value;
}
