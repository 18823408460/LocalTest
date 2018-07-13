package com.unisrobot.robothead.visualEditNew;

import android.os.Bundle;

import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

import java.util.List;

/**
 * Created by WEI on 2018/7/3.
 * <p>
 * 所有的消息都通过下发的形式
 */

public abstract class Node {
    protected String Event;
    protected String PrefertName;

    public abstract void startExe();

    public abstract void stopExe();

    public abstract void parseData(VpJsonBean.NodeDataBase nodeDataBase);

    public abstract Bundle getBundle();

    public abstract List<RobotMsgType> getRobotMsgTypeList();

    public abstract boolean handlerMsg(RobotMsgType robotMsgType, Bundle bundle);
}
