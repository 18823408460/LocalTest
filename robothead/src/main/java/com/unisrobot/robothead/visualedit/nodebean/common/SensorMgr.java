package com.unisrobot.robothead.visualedit.nodebean.common;

import android.text.TextUtils;

/**
 * Created by WEI on 2018/7/1.
 * <p>
 * 传感器工具类，返回传感器的检测结果:
 * boolean:
 * 数值:
 */

public class SensorMgr {

    /**
     * @param name --- 传感器的名字
     * @param pos  --- 哪个位置的传感器
     * @return
     */
    public static boolean getSensorState(String name, String pos) {

        return false;
    }

    /**
     * @param event
     * @param pos
     * @param params
     * @return
     */
    public static float getSensorData(String event, String pos, String params) {
        float resultData = -1;
        switch (event) {
            case NodeEvent.Perception.LIGHT_SENSOR:
                resultData = 100;
                break;
            case NodeEvent.Perception.CSB_SENSOR:
                resultData = 65536;
                break;
            case NodeEvent.Perception.WEN_SHI_SENSOR:
                if (NodeParams.Perception.WNEDU.equals(params)) {
                    resultData = 38;
                } else if (NodeParams.Perception.SHIDU.equals(params)) {
                    resultData = 99;
                }
                break;
            case NodeEvent.Perception.WEN_DU_SENSOR:
                resultData = 33;
                break;
        }
        return resultData;
    }

    /**
     * 根据一个条件去检测对应的状态
     *
     * @param condition
     * @return
     */
    public static boolean getSensorState(String condition) {
        boolean resultData = false;
        if (!TextUtils.isEmpty(condition)) {
            switch (condition) {
                case NodeParams.Perception.AHEAD_BARRIER:
                    break;
                case NodeParams.Perception.BACK_BARRIER:
                    break;
                case NodeParams.Perception.VOICE_SHOU:
                    break;
                case NodeParams.Perception.VOICE:
                    break;
                case NodeParams.Perception.PEOPEL:
                    break;
            }
        }
        return false;
    }

    /**
     * 检测人体传感器
     * @param content
     * @return
     */
    public static boolean getPeopleSensor(String content) {
        boolean resultData = false;
        if (!TextUtils.isEmpty(content)) {
            switch (content) {
                case NodeParams.Perception.LEFT:
                    break;
                case NodeParams.Perception.RIGHT:
                    break;
                case NodeParams.Perception.LEFT_AHEAD:
                    break;
                case NodeParams.Perception.RIGHT_AHEAD:
                    break;
                case NodeParams.Perception.CHEST_UP:
                    break;
                case NodeParams.Perception.CHEST_DOWN:
                    break;
                case NodeParams.Perception.BACK_LEFT:
                    break;
                case NodeParams.Perception.BACK_RIGHT:
                    break;
            }
        }
        return resultData;
    }

    /**
     * 获取某个传感器的次数
     *
     * @param pos
     * @param count
     * @return
     */
    public static boolean getTouchCount(String pos, String count) {
        boolean resultData = false;
        int countInt = Integer.parseInt(count);
        if (!TextUtils.isEmpty(pos)) {
            switch (pos) {
                case NodeParams.Perception.LEFT:
                    break;
                case NodeParams.Perception.RIGHT:
                    break;
                case NodeParams.Perception.LEFT_AHEAD:
                    break;
                case NodeParams.Perception.RIGHT_AHEAD:
                    break;
                case NodeParams.Perception.CHEST_UP:
                    break;
                case NodeParams.Perception.CHEST_DOWN:
                    break;
                case NodeParams.Perception.BACK_LEFT:
                    break;
                case NodeParams.Perception.BACK_RIGHT:
                    break;
            }
        }
        return resultData;
    }

    /**
     * 获取年龄
     *
     * @param age
     * @param operate
     * @return
     */
    public static boolean getPeopleAge(String age, String operate) {
        boolean resultData = false;
        int ageInt = Integer.parseInt(age);
        switch (operate) {
            case "Bigger":// 大于
                break;
            case "Smaller"://小于
                break;
            case "Equal":// 等于
                break;
            case "NotEqual":// 不等于
                break;
        }
        return resultData;
    }

    /**
     * 获取性别 == 男，女
     *
     * @param sex
     * @return
     */
    public static boolean getPeopleSex(String sex) {
        boolean resultData = false;
        return resultData;
    }
}
