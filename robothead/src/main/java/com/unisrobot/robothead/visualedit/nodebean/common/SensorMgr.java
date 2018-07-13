package com.unisrobot.robothead.visualedit.nodebean.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;

import com.unisrobot.robothead.visualedit.nodebean.base.SensorBooleanBean;
import com.unisrobot.robothead.visualedit.nodebean.base.SensorNumBean;

import java.util.Observable;

/**
 * Created by WEI on 2018/7/1.
 * <p>
 * 传感器工具类，返回传感器的检测结果:
 * boolean:
 * 数值:
 */

public class SensorMgr extends Observable {
    // SparseArray是android里为<Interger,Object>这样的Hashmap而专门写的类
//    private SparseArray sensorNumMap = new SparseArray();
//    private SparseArray sensorBooleanMap = new SparseArray();
    private static ArrayMap<String, SensorNumBean> sensorNumMap = new ArrayMap<>();
    private static ArrayMap<String, SensorBooleanBean> sensorBooleanMap = new ArrayMap<>();
    private static ArrayMap<String, Integer> sensorTouchMap = new ArrayMap<>();
    private Handler handler;
    private static volatile SensorMgr sensorMgr;

    public static SensorMgr getSensorMgr() {
        if (sensorMgr == null) {
            synchronized (SensorMgr.class) {
                if (sensorMgr == null) {
                    sensorMgr = new SensorMgr();
                }
            }
        }
        return sensorMgr;
    }

    private SensorMgr() {
        //这些传感器数据每个 X s 获取一次，然后更新, 以及一些触摸传感器的触摸次数记录
        // 传感器 = 系统触摸传感器 + 系统可插入传感器(这个数据需要主动去获取) + 外置传感器
        //系统可插入传感器 = 人体+ 超神波（距离） +
        initHandler();
        updateSensorData();

        //所有 boolean 类型个数据，全部通过观察者发送出去
        notifyObservers(new SensorBooleanBean());
    }

    public void updateBooleanSensor(String pos, String name, boolean value) {
        SensorBooleanBean sensorBooleanBean = sensorBooleanMap.get(pos);
        if (sensorBooleanBean == null) {
            sensorBooleanBean = new SensorBooleanBean(name, value);
        } else {
            sensorBooleanBean.state = value;
        }
        sensorBooleanMap.put(pos, sensorBooleanBean);
    }

    public void updateTouchSensor(String pos) {
        Integer integer = sensorTouchMap.get(pos);
        sensorTouchMap.put(pos, ++integer);
    }

    public void updateNumSensor(String pos, String name, float value) {
        SensorNumBean sensorNumBean = sensorNumMap.get(pos);
        if (sensorNumBean == null) {
            sensorNumBean = new SensorNumBean(name, value);
        }
        sensorNumBean.value = value;
        sensorNumMap.put(pos, sensorNumBean);
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread("getSensorDataThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    private void updateSensorData() {
        sensorNumMap.put(NodeParams.Perception.LEFT, new SensorNumBean("超神波", 1));
        sensorNumMap.put(NodeParams.Perception.RIGHT, new SensorNumBean("超神波", 1));
        sensorTouchMap.put(NodeParams.Perception.LEFT, 1);
        sensorTouchMap.put(NodeParams.Perception.RIGHT, 3);
        sensorBooleanMap.put(NodeParams.Perception.CHEST_DOWN, new SensorBooleanBean("人体", false));
        sensorBooleanMap.put(NodeParams.Perception.CHEST_UP, new SensorBooleanBean("人体", false));
    }

    private static float getData(String event, String pos, float defaultValue) {
        SensorNumBean sensorNumBean = sensorNumMap.get(pos);
        float resultData;
        if (event.equals(sensorNumBean.name)) { //如果当前位置的传感器名字 一致 ,
            resultData = sensorNumBean.value;
        } else {
            resultData = defaultValue;
        }

        return resultData;
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
            case NodeEvent.Perception.LIGHT_SENSOR://感知：光照强度
                resultData = getData(event, pos, 100);
                break;
            case NodeEvent.Perception.CSB_SENSOR://感知：超神波度数
                resultData = getData(event, pos, 65535);
                break;
            case NodeEvent.Perception.WEN_SHI_SENSOR://感知：温湿度传感器度数
                if (NodeParams.Perception.WNEDU.equals(params)) {
                    resultData = getData(event, pos, 38);
                } else if (NodeParams.Perception.SHIDU.equals(params)) {
                    resultData = getData(event, pos, 99);
                }
                break;
            case NodeEvent.Perception.WEN_DU_SENSOR://感知：温度传感器度数
                resultData = getData(event, pos, 33);
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
                case NodeParams.Perception.AHEAD_BARRIER://感知：当前方有障碍
                    // 需要查询超神波 + 人体
                    break;
                case NodeParams.Perception.BACK_BARRIER://感知：当后方有障碍
                    break;
                case NodeParams.Perception.VOICE_SHOU://感知：当听到拍手声音
                    //但如果是多层嵌套，这里也不好实现
                case NodeParams.Perception.VOICE://感知：当听到说话声音,这两个交给系统消息下发RobotMsgType
                    break;
                case NodeParams.Perception.PEOPEL://感知：当感应到人体
                    break;
            }
        }
        return resultData;
    }

    /**
     * 检测人体传感器
     *
     * @param pos
     * @return
     */
    public static boolean getPeopleSensor(String pos) {
        boolean resultData = false;
        if (!TextUtils.isEmpty(pos)) {
            SensorBooleanBean sensorBooleanBean = sensorBooleanMap.get(pos);
            if ("人体传感器".equals(sensorBooleanBean.name)) {
                return sensorBooleanBean.state;
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
            Integer integer = sensorTouchMap.get(pos);
            return integer == countInt;
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
