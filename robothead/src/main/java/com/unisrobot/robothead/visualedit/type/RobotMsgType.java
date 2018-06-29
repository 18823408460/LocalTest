package com.unisrobot.robothead.visualedit.type;

/**
 * Created by WEI on 2018/6/28.
 */

public enum RobotMsgType {
    Wake,//唤醒消息
    Recognizer,//识别消息
    SensorLocal,//机器内置感应器
    SensorThreePart,// 机器外置传感器
    PlayEnd,// 音频播放结束
    Timer;//定时器消息，针对那些时间型的 Node

    public enum SensorLocalType {
        BackSensor,
        LeftLegSensor,
    }

    public enum SensorThreePartType {
        TempurutrueSensor
    }
    }

