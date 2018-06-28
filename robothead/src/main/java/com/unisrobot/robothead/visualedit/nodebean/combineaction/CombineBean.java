package com.unisrobot.robothead.visualedit.nodebean.combineaction;

import com.unisrobot.robothead.visualedit.nodebean.common.NodeEvent;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;

/**
 * Created by WEI on 2018/6/27.
 */

public class CombineBean {
    private int actionId = -1;
    private long time = 1000;

    public static CombineBean getBean(VpJsonBean.NodeDataBase nodeData) {
        CombineBean combineBean = new CombineBean();
        String content = nodeData.Event;
        int actionId = -1;
        long time = 1000;
        switch (content) {
            case NodeEvent.CombineAction.SLIENCE:
                actionId = 10104;
                time = 1000 * 4;
                break;
            case NodeEvent.CombineAction.WELCOME:
                actionId = 10105;
                time = 4000;
                break;
            case NodeEvent.CombineAction.SHUA_SHUAI:
                actionId = 10106;
                time = 3500;
                break;
            case NodeEvent.CombineAction.OPEN_HUAI_BAO:
                actionId = 10107;
                time = 3700;
                break;
            case NodeEvent.CombineAction.XIU_JI_ROU:
                actionId = 10113;
                time = 3000;
                break;
            case NodeEvent.CombineAction.SAY_HELLO:
                actionId = 10102;
                time = 3500;
                break;
            case NodeEvent.CombineAction.QIU_BAOBAO:
                actionId = 10114;
                time = 3000;
                break;
            case NodeEvent.CombineAction.JING_LI:
                actionId = 10205;
                time = 4400;
                break;
            case NodeEvent.CombineAction.ZHAO_SHOU:
                actionId = 10302;
                time = 5000;
                break;
            case NodeEvent.CombineAction.JU_JI_YUAN_QI:
                actionId = 10401;
                time = 3000;
                break;
            case NodeEvent.CombineAction.BU_YAO:
                actionId = 12101;
                time = 4000;
                break;
            case NodeEvent.CombineAction.ZEN_MEN_LA:
                actionId = 11301;
                time = 4100;
                break;
            default:
                break;
        }
        combineBean.actionId = actionId;
        combineBean.time = time;
        return combineBean;
    }

    public void exeNode() {
        if (actionId != -1) {
            //SerialPortMgr.getInstance().sendActionCmd(actionId);
        }
    }
}
