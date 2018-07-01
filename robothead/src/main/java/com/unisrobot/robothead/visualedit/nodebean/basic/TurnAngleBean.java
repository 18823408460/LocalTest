package com.unisrobot.robothead.visualedit.nodebean.basic;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendUtil;
import com.unisrobot.robothead.visualedit.nodebean.common.NodeEvent;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;

/**
 * Created by Administrator on 2018/6/27.
 */

public class TurnAngleBean extends Node<Long> {
    private short angle;
    private long xTime;
    private String direction = "";

    @Override
    public Long exeNode() {
        switch (direction) {
            case NodeEvent.Basic.TRUN_LEFT_ANGLE:
                //   SerialPortMgr.getInstance().sendDirectMotion((short) 0x0B, (short) 10, (short) -angle);
                // 1.执行动作 xTime
                // 2. xTime + diff 后 执行下一个
                xTime += 1000;
                break;
            case NodeEvent.Basic.TRUN_RIGHT_ANGLE:
                //  SerialPortMgr.getInstance().sendDirectMotion((short) 0x0B, (short) 10, (short) angle);
                xTime += 1000;
                break;
        }
        return xTime;
    }

    public static TurnAngleBean getBean(VpJsonBean.NodeDataBase nodeData) {
        TurnAngleBean speedTimeBean = new TurnAngleBean();
        String InputNumberAngle = AppendUtil.getNumberParams(nodeData, 0);
//        String direction = nodeData.Args.get(0).Content;
        short speed = getAngle(InputNumberAngle);
        //   speedTimeBean.direction = direction;
        speedTimeBean.xTime = getTime("");
        speedTimeBean.angle = speed;
        return speedTimeBean;
    }

    private static short getAngle(String speed) {
        short angle = Short.parseShort(speed);
        return angle;
    }


}
