package com.unisrobot.robothead.visualedit.type;

/**
 * Created by WEI on 2018/6/28.
 */

public enum RobotMsgType {
    Wake,//唤醒消息
    Recognizer,//识别消息
    SensorTouch,//机器内置触摸感应器
    SensorInsertLocal,//机器内置可插拔感应器
    SensorThreePart,// 机器外置传感器
    PlayEnd,// 音频播放结束
    Timer,//定时器消息，针对那些时间型的 Node
    Map,
    WaitForPhone;

    public enum SensorLocalType {
        BackSensor,
        LeftLegSensor,
    }

    public enum SensorThreePartType {
        TempurutrueSensor
    }
}

