package com.unisrobot.robothead.visualEditNew.father;

import android.os.Bundle;

import com.unisrobot.robothead.visualEditNew.FatherNode;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

import java.util.List;

/**
 * Created by WEI on 2018/7/3.
 */

public class RepeatCountFatherNode extends FatherNode {

    public RepeatCountFatherNode(VpJsonBean.NodeDataBase nodeDataBase) {
        super(nodeDataBase);
    }

    @Override
    public void startExe() {

    }

    @Override
    public void stopExe() {

    }

    @Override
    public void parseData(VpJsonBean.NodeDataBase nodeDataBase) {
        nodeDataBaseList = nodeDataBase.Actions;
    }

    @Override
    public Bundle getBundle() {
        return null;
    }

    @Override
    public List<RobotMsgType> getRobotMsgTypeList() {
        return null;
    }
}
