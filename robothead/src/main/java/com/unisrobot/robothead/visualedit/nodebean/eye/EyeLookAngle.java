package com.unisrobot.robothead.visualedit.nodebean.eye;

import android.text.TextUtils;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendUtil;
import com.unisrobot.robothead.visualedit.nodebean.common.NodeEvent;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by WEI on 2018/6/27.
 */

public class EyeLookAngle extends Node<Long> {
    private String direction;
    private Float angle;
    private long time;

    public static EyeLookAngle getBean(VpJsonBean.NodeDataBase nodeDataBase) {
        EyeLookAngle eyeLookAngle = new EyeLookAngle();
        String event = nodeDataBase.Event;
        if (NodeEvent.Eyes.LOOK_BROUNT.equals(event)) {

        } else {
            String inputNumberAngle = AppendUtil.getNumberParams(nodeDataBase, 0);
            eyeLookAngle.direction = nodeDataBase.Event;
            if (inputNumberAngle != null) {
                eyeLookAngle.angle = Float.parseFloat(inputNumberAngle);
            }
        }
        eyeLookAngle.setRobotMsgType(RobotMsgType.Timer);

        return eyeLookAngle;
    }

    @Override
    public Long exeNode() {
        time = 1000;
        if (!TextUtils.isEmpty(direction)) {
            switch (direction) {
                case NodeEvent.Eyes.LOOK_LEFT:
                    //SerialPortMgr.getInstance().sendHeadCmd((short) 0, (short) 0, angle,(short) 5000);
                    break;
                case NodeEvent.Eyes.LOOK_RIGHT_:
                    // SerialPortMgr.getInstance().sendHeadCmd((short) 0, (short) 0, (short) -angle, (short) 5000);
                    break;
                case NodeEvent.Eyes.LOOK_UP:
                    // SerialPortMgr.getInstance().sendHeadCmd((short) -angle, (short) 5000, (short) 0,(short) 0);
                    break;
                case NodeEvent.Eyes.LOOK_DOWN:
                    //SerialPortMgr.getInstance().sendHeadCmd(angle, (short) 5000, (short) 0, (short) 0);
                    break;
                case NodeEvent.Eyes.LOOK_BROUNT:
                    // SerialPortMgr.getInstance().sendHeadCmd((short) 0, (short) 5000, (short) 0,(short) 5000);
                    break;
            }
        }
        return time;
    }
}
