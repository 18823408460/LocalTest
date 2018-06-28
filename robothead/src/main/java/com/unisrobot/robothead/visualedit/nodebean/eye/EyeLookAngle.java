package com.unisrobot.robothead.visualedit.nodebean.eye;

import android.text.TextUtils;

import com.unisrobot.robothead.visualedit.NodeEvent;
import com.unisrobot.robothead.visualedit.VpJsonBean;
import com.unisrobot.robothead.visualedit.nodebean.AppendUtil;

/**
 * Created by WEI on 2018/6/27.
 */

public class EyeLookAngle {
    private String direction;
    private short angle;
    private long time;

    public static EyeLookAngle getBean(VpJsonBean.NodeDataBase nodeDataBase) {
        EyeLookAngle eyeLookAngle = new EyeLookAngle();
        String inputNumberAngle = AppendUtil.getInputNumber(nodeDataBase, 0);
        eyeLookAngle.direction = nodeDataBase.Event;
        if (inputNumberAngle != null) {
            //eyeLookAngle.angle = Short.parseShort(inputNumberAngle);
        }
        return eyeLookAngle;
    }

    public void exeNode() {
        if (!TextUtils.isEmpty(direction)) {
            time = 1000;
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
    }
}
