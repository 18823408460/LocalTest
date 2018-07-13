package com.unisrobot.robothead.visualEditNew;

import android.os.Bundle;

import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

import java.util.List;

/**
 * Created by WEI on 2018/7/3.
 */

public abstract class ChildNode extends Node {

    public ChildNode(VpJsonBean.NodeDataBase nodeDataBase) {
        parseData(nodeDataBase);
    }

    @Override
    public boolean handlerMsg(RobotMsgType robotMsgType, Bundle bundle) {
        List<RobotMsgType> robotMsgTypeList = getRobotMsgTypeList();
        Bundle bundle1 = getBundle();
        return false;
    }
}
