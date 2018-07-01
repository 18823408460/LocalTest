package com.unisrobot.robothead.visualedit.bean;

/**
 * Created by Administrator on 2018/6/30.
 * <p>
 * 需要动态获取的 传感器的值：
 * <p>
 * 如：
 * 左侧 温湿度传感器 的 温度 值
 * 右侧 超神波传感器的  值
 *
 * {
 "NodeID":9,
 "Type":"Perception",
 "PrefabName":"PerceptionPrefab_SensorTextContent",
 "Event":"光照传感器光照强度",
 "HeartInfo":null,
 "Args":[
 {
 "Content":"左侧",
 "WordsStyle":null
 }
 ],
 "InputInits":null,
 "Colors":null,
 "Pictures":null,
 "Appendent":null,
 "Actions":null,
 "Switch":null
 }
 {
 "NodeID":13,
 "Type":"Perception",
 "PrefabName":"PerceptionPrefab_SensorCTCT",
 "Event":"温湿度传感器",
 "HeartInfo":null,
 "Args":[
 {
 "Content":"左侧",
 "WordsStyle":null
 },
 {
 "Content":"温度",
 "WordsStyle":null
 }
 ],
 "InputInits":null,
 "Colors":null,
 "Pictures":null,
 "Appendent":null,
 "Actions":null,
 "Switch":null
 }

 */

public class SensorNumber {
    public String event;
    public String contentPos;
    public String contentName;
}
