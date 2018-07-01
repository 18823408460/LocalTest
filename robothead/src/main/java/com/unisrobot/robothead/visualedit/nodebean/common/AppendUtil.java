package com.unisrobot.robothead.visualedit.nodebean.common;

import com.unisrobot.robothead.visualedit.type.NodeJsonType;

import java.util.List;

/**
 * Created by Administrator on 2018/6/27.
 */

public class AppendUtil {

    private static boolean getBooleanAppendAB(VpJsonBean.Appendents appendent, int index) {
        boolean resultData = false;
        if (appendent != null) {
            if (index == 0) {
                List<VpJsonBean.NodeDataBase> appendAlist = appendent.Append_A;
                if (appendAlist != null && appendAlist.size() > 0) {
                    VpJsonBean.NodeDataBase nodeDataBase = appendAlist.get(0);
                    resultData = getBooleanParams(nodeDataBase);
                }
            } else {
                List<VpJsonBean.NodeDataBase> appendAlist = appendent.Append_B;
                if (appendAlist != null && appendAlist.size() > 0) {
                    VpJsonBean.NodeDataBase nodeDataBase = appendAlist.get(0);
                    resultData = getBooleanParams(nodeDataBase);
                }
            }
        }
        return resultData;
    }

    public static boolean getBooleanParams(VpJsonBean.NodeDataBase nodeDataBase) {
        boolean resultData = false;
        if (NodeJsonType.MIND.equals(nodeDataBase.Type)) {
            switch (nodeDataBase.PrefabName) {
                case NodeJsonType.Mind.MindPrefab_CompareTwoNum: //比较两个数
                    String numberParams1 = getNumberParams(nodeDataBase, 0);
                    String numberParams2 = getNumberParams(nodeDataBase, 1);
                    String operate = nodeDataBase.Pictures.get(0).Picture;
                    resultData = NumberUtil.compareTowNum(operate, numberParams1, numberParams2);
                    break;
                case NodeJsonType.Mind.MindPrefab_AndOrRelation://且/或
                    String logicOperate = nodeDataBase.Event;
                    boolean booleanAppendA = getBooleanAppendAB(nodeDataBase.Appendent, 0);
                    boolean booleanAppendB = getBooleanAppendAB(nodeDataBase.Appendent, 1);
                    resultData = NumberUtil.numLogicOperate(logicOperate, booleanAppendA, booleanAppendB);
                    break;
                case NodeJsonType.Mind.MindPrefab_NumberIs://数字 是 XX
                    String numberParams = getNumberParams(nodeDataBase, 0);
                    String content = nodeDataBase.Args.get(0).Content;
                    resultData = NumberUtil.numberIs(content, numberParams);
                    break;
                case NodeJsonType.Mind.MindPrefab_NumberCanBeDone://数字 可被XX
                    numberParams1 = getNumberParams(nodeDataBase, 0);
                    numberParams2 = getNumberParams(nodeDataBase, 1);
                    content = nodeDataBase.Args.get(0).Content;
                    resultData = NumberUtil.numCanbeDone(content, numberParams1, numberParams2);
                    break;
                case NodeJsonType.Mind.MindPrefab_Not:
                    boolean appendCData = getAppendCBooleanData(nodeDataBase);
                    resultData = !appendCData;
                    break;
            }
        } else if (NodeJsonType.PERCEPTION.equals(nodeDataBase.Type)) {
            switch (nodeDataBase.PrefabName) {
                case NodeJsonType.Perception.PerceptionPrefab_When: //当
                    resultData = SensorMgr.getSensorState(nodeDataBase.Args.get(0).Content);
                    break;
                case NodeJsonType.Perception.PerceptionPrefab_SensorTextContent:
                    String event = nodeDataBase.Event;
                    if (NodeEvent.Perception.PEOPLE_SENSOR.equals(event)) { //
                        resultData = SensorMgr.getPeopleSensor(nodeDataBase.Args.get(0).Content);
                    }
                    break;
                case NodeJsonType.Perception.PerceptionPrefab_SensorTextContentAttach: //触碰传感器被触碰
                    String pos = nodeDataBase.Args.get(0).Content;
                    String count = nodeDataBase.Args.get(1).Content;
                    resultData = SensorMgr.getTouchCount(pos, count);
                    break;
                case NodeJsonType.Perception.PerceptionPrefab_SensorAPICC://年龄
                    String age = getNumberParams(nodeDataBase, 0);
                    String operate = nodeDataBase.Pictures.get(0).Picture;
                    resultData = SensorMgr.getPeopleAge(age, operate);
                    break;
                case NodeJsonType.Perception.PerceptionPrefab_SensorAPIContext:
                    String sex = nodeDataBase.Args.get(0).Content;
                    resultData = SensorMgr.getPeopleSex(sex);
                    break;
            }
        }
        return resultData;
    }

    /**
     * 获取 Append A。B 数值型
     *
     * @param nodeDataBase
     * @param index
     * @return
     */
    public static String getNumberParams(VpJsonBean.NodeDataBase nodeDataBase, int index) {
        List<VpJsonBean.InputNumber> inputInits = nodeDataBase.InputInits;
        if (inputInits != null && inputInits.size() > 0) {
            VpJsonBean.InputNumber inputNumber = inputInits.get(index);
            if (index == 0) {
                if (nodeDataBase.Appendent.Append_A != null) { //  有可能是一个动态变化的值
                    String appendABData = getAppendABData(nodeDataBase.Appendent.Append_A.get(0));
                    inputNumber.Number = appendABData;
                } else { // 一定是一个静态的值
                }
            } else if (index == 1) {
                if (nodeDataBase.Appendent.Append_B != null) {
                    String appendABData = getAppendABData(nodeDataBase.Appendent.Append_B.get(0));
                    inputNumber.Number = appendABData;
                } else {
                }
            }
            return inputNumber.Number;
        }
        return "";
    }


    private static String getAppendABData(VpJsonBean.NodeDataBase nodeDataBase) {
        String resultData = "";
        if (NodeJsonType.MIND.equals(nodeDataBase.Type)) {
            switch (nodeDataBase.PrefabName) {
                case NodeJsonType.Mind.MindPrefab_Num: // 选择型参数= 数字
                    resultData = nodeDataBase.Args.get(0).Content;
                    break;
                case NodeJsonType.Mind.MindPrefab_Calculate://计算两个数
                    String inputNumber1 = getNumberParams(nodeDataBase, 0);
                    String inputNumber2 = getNumberParams(nodeDataBase, 1);
                    String operate = nodeDataBase.Pictures.get(0).Picture;
                    resultData = NumberUtil.calculateTwoNum(operate, inputNumber1, inputNumber2);
                    break;
                case NodeJsonType.Mind.MindPrefab_CalculateOneNumber://四舍五入
                    inputNumber1 = getNumberParams(nodeDataBase, 0);
                    operate = nodeDataBase.Args.get(0).Content;
                    resultData = NumberUtil.calculateOneNum(operate, inputNumber1);
                    break;
                case NodeJsonType.Mind.MindPrefab_AcalculateBRemainder://取余数
                    inputNumber1 = getNumberParams(nodeDataBase, 0);
                    inputNumber2 = getNumberParams(nodeDataBase, 1);
                    Double data = Double.parseDouble(inputNumber1) % Double.parseDouble(inputNumber2);
                    resultData = Double.toString(data);
                    break;
                case NodeJsonType.Mind.MindPrefab_AtoBRandomInteger: // a -> b 之间的随机数
                    inputNumber1 = getNumberParams(nodeDataBase, 0);
                    inputNumber2 = getNumberParams(nodeDataBase, 1);
                    resultData = NumberUtil.getRandomNum(inputNumber1, inputNumber2);
                    break;
                case "MindPrefab_Heart":
                    break;
                default:
                    break;
            }
        } else if (NodeJsonType.PERCEPTION.equals(nodeDataBase.Type)) {
            switch (nodeDataBase.PrefabName) {
                case NodeJsonType.Perception.PerceptionPrefab_SensorCTCT:
                case NodeJsonType.Perception.PerceptionPrefab_SensorTextContent:
                    String event = nodeDataBase.Event;
                    if (NodeEvent.Perception.LIGHT_SENSOR.equals(event) ||
                            NodeEvent.Perception.CSB_SENSOR.equals(event) ||
                            NodeEvent.Perception.WEN_DU_SENSOR.equals(event)) {
                        String params2 = null; // 温湿度传感器 > 温度还是湿度
                        List<VpJsonBean.DataArgs> args = nodeDataBase.Args;
                        if (args.size() > 1) {
                            params2 = args.get(1).Content;
                        }
                        float sensorData = SensorMgr.getSensorData(event, args.get(0).Content, params2);
                        resultData = Float.toString(sensorData);
                    }
                    break;
                default:
                    break;
            }
        }
        return resultData;
    }


    public static boolean getAppendCBooleanData(VpJsonBean.NodeDataBase nodeDataBase) {
        boolean result = false;
        VpJsonBean.Appendents appendent = nodeDataBase.Appendent;
        if (appendent != null) {
            List<VpJsonBean.NodeDataBase> append_c = appendent.Append_C;
            if (append_c != null && append_c.size() > 0) {
                result = getBooleanParams(nodeDataBase);
            }
        }
        return result;
    }

    public static VpJsonBean.NodeDataBase getAppendCData(VpJsonBean.NodeDataBase nodeDataBase) {
        VpJsonBean.NodeDataBase appcData = null;
        VpJsonBean.Appendents appendent = nodeDataBase.Appendent;
        if (appendent != null) {
            List<VpJsonBean.NodeDataBase> append_c = appendent.Append_C;
            if (append_c != null && append_c.size() > 0) {
                appcData = append_c.get(0);
            }
        }
        return appcData;
    }
}
