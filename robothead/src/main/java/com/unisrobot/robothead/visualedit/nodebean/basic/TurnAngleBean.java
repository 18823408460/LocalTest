package com.unisrobot.robothead.visualedit.nodebean.basic;

import android.text.TextUtils;

import com.unisrobot.robothead.visualedit.nodebean.common.NodeEvent;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendUtil;

/**
 * Created by Administrator on 2018/6/27.
 */

public class TurnAngleBean {
    private short angle;
    private long xTime;
    private String direction ="";

    public void exeNode() {
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
    }

    public static TurnAngleBean getBean(VpJsonBean.NodeDataBase nodeData) {
        TurnAngleBean speedTimeBean = new TurnAngleBean();
        String InputNumberAngle = AppendUtil.getInputNumberStatic(nodeData, 0);
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

    private static long getTime(String time) {
        long xTime = 1000;
        if (!TextUtils.isEmpty(time)) {
            xTime = (long) (Double.parseDouble(time) * 1000);
        }
        return xTime;
    }
}
