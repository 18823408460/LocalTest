package com.unisrobot.robothead.visualedit.nodebean;

import com.unisrobot.robothead.visualedit.NodeParams;
import com.unisrobot.robothead.visualedit.type.NodeJsonType;
import com.unisrobot.robothead.visualedit.VpJsonBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/27.
 */

public class AppendUtil {
        /**
         * 获取 Append A。B 的值，数值型
         *
         * @param nodeDataBase
         * @param index
         * @return
         */
        public static String getInputNumber(VpJsonBean.NodeDataBase nodeDataBase, int index) {
                List<VpJsonBean.InputNumber> inputInits = nodeDataBase.InputInits;
                if (inputInits != null && inputInits.size() > 0) {
                        VpJsonBean.InputNumber inputNumber = inputInits.get(index);
                        if (index == 0) {
                                if (nodeDataBase.Appendent.Append_A != null) {
                                        AppendABData appendData = getAppendABData(nodeDataBase.Appendent.Append_A.get(0));
                                        inputNumber.Number = appendData.number;
                                }
                        } else if (index == 1) {
                                if (nodeDataBase.Appendent.Append_B != null) {
                                        AppendABData appendData1 = getAppendABData(nodeDataBase.Appendent.Append_A.get(0));
                                        inputNumber.Number = appendData1.number;
                                }
                        }
                        return inputNumber.Number;
                }
                return "";
        }

        public static AppendCData getAppendCData(VpJsonBean.NodeDataBase nodeDataBase) {
                AppendCData appendCData = null;
                VpJsonBean.Appendents appendent = nodeDataBase.Appendent;
                if (appendent != null) {
                        List<VpJsonBean.NodeDataBase> append_c = appendent.Append_C;
                        if (append_c != null && append_c.size() > 0) {
                                appendCData = new AppendCData();
                        }
                }
                return appendCData;
        }

        private static AppendABData getAppendABData(VpJsonBean.NodeDataBase nodeDataBase) {
                AppendABData appendData = new AppendABData();
                if (NodeJsonType.MIND.equals(nodeDataBase.Type)) {
                        switch (nodeDataBase.PrefabName) {
                                case NodeJsonType.Mind.MindPrefab_Num:
                                        appendData.number = nodeDataBase.Args.get(0).Content;
                                        break;
                                case NodeJsonType.Mind.MindPrefab_Calculate://计算两个数
                                        String inputNumber = getInputNumber(nodeDataBase, 0);
                                        String inputNumber2 = getInputNumber(nodeDataBase, 1);
                                        appendData.number = NumberUtil.handlerNumber(nodeDataBase.Pictures.get(0).Picture, inputNumber, inputNumber2);
                                        break;
                                case NodeJsonType.Mind.MindPrefab_CalculateNumber://四舍五入
                                        inputNumber = getInputNumber(nodeDataBase, 0);
                                        String content = nodeDataBase.Args.get(0).Content;
                                        if (NodeParams.Mind.SI_SHE_WU_RU.equals(content)) {
                                                appendData.number = Math.round(Double.valueOf(inputNumber)) + "";
                                        } else if (NodeParams.Mind.SIN.equals(content)) {
                                                Double aDouble = Double.valueOf(inputNumber);
                                                appendData.number = Math.sin(aDouble * Math.PI / 180) + "";
                                        }
                                        break;
                                case NodeJsonType.Mind.MindPrefab_AcalculateBRemainder://取余数
                                        inputNumber = getInputNumber(nodeDataBase, 0);
                                        inputNumber2 = getInputNumber(nodeDataBase, 1);
                                        appendData.number = Double.parseDouble(inputNumber) % Double.parseDouble(inputNumber2) + "";
                                        break;
                                case "MindPrefab_AndOrRelation":
                                        String action1 = null;
                                        String action2 = null;

                                        break;
                                case "MindPrefab_NumberIs":

                                        break;

                                case "MindPrefab_Heart":

                                        break;
                                case "MindPrefab_CalculateSecond": //比较两个数
                                        String appendResult = null;
                                        //如果 Append_A=null, 则用inputNumber1和inputNumber2比较，否则用 Append_A和 inputNumber2进行比较
                                        inputNumber = nodeDataBase.InputInits.get(0).Number;
                                        inputNumber2 = nodeDataBase.InputInits.get(1).Number;
                                        String oprator = nodeDataBase.Pictures.get(0).Picture;

                                        break;
                                default:
                                        break;
                        }
                } else if ("Perception".equals(nodeDataBase.Type)) {
                        switch (nodeDataBase.PrefabName) {
                                case "PerceptionPrefab_WaitTouch":
                                        break;
                                case "PerceptionPrefab_FellSomething":
                                        break;
                                case "PerceptionPrefab_OntologyExtension":
                                        String event = nodeDataBase.Event;

                                        break;
                                case "PerceptionPrefab_SensorTextContent":
                                        String content = nodeDataBase.Args.get(0).Content;// 扩展。。。
                                        break;
                                case "PerceptionPrefab_SensorCTCT":
                                        content = nodeDataBase.Args.get(0).Content;// 扩展。。。
                                        break;
                                case "PerceptionPrefab_SensorTextContentAttach":
                                        content = nodeDataBase.Args.get(0).Content;// 扩展。。。
                                        break;
                                default:
                                        break;
                        }
                }
                return appendData;
        }
}
