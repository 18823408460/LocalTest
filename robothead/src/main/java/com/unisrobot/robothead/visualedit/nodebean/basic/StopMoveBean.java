package com.unisrobot.robothead.visualedit.nodebean.basic;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendUtil;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by WEI on 2018/7/1.
 */

public class StopMoveBean extends Node<Long> {
    private long time;

    @Override
    public Long exeNode() {
        return time;
    }

    public static StopMoveBean getBean(VpJsonBean.NodeDataBase nodeData) {
        StopMoveBean stopMoveBean = new StopMoveBean();
        String timer = AppendUtil.getNumberParams(nodeData, 0);
        stopMoveBean.time = getTime(timer);
        stopMoveBean.setRobotMsgType(RobotMsgType.Timer);
        return stopMoveBean;
    }
}
