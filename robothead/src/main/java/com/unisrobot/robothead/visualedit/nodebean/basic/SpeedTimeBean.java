package com.unisrobot.robothead.visualedit.nodebean.basic;

import android.text.TextUtils;
import android.util.Log;

import com.unisrobot.robothead.visualedit.nodebean.common.AppendUtil;
import com.unisrobot.robothead.visualedit.nodebean.common.NodeParams;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;

/**
 * Created by Administrator on 2018/6/27.
 */

public class SpeedTimeBean {
    private static final String TAG = SpeedTimeBean.class.getSimpleName();
    private short speed;
    private long xTime;
    private String direction;

    public void exeNode() {
        switch (direction) {
            case NodeParams.Basic.GO_AHEAD:
                // SerialPortMgr.getInstance().openSportCtr_Direction(speed, (short) 0, (int) xTime);
                // 1.执行动作 xTime
                // 2. xTime + diff 后 执行下一个
                xTime += 1000;
                break;
            case NodeParams.Basic.GO_BACK:
                //SerialPortMgr.getInstance().openSportCtr_Direction((short) -speed, (short) 0, (int) xTime);
                xTime += 1000;
                break;
            case NodeParams.Basic.GO_LEFT:
                xTime += 2500;
                break;
            case NodeParams.Basic.GO_RIGHT:
                xTime += 2500;
                break;
        }
    }

    public static SpeedTimeBean getBean(VpJsonBean.NodeDataBase nodeData) {
        SpeedTimeBean speedTimeBean = new SpeedTimeBean();
        String InputNumberSpeed = AppendUtil.getNumberParams(nodeData, 0);
        String InputNumberTime = AppendUtil.getNumberParams(nodeData, 1);
        Log.e(TAG, "getBean: InputNumberSpeed=" + InputNumberSpeed + "  InputNumberTime=" + InputNumberTime);
        String direction = nodeData.Args.get(0).Content;
        short speed = getSpeed(InputNumberSpeed);
        long time = getTime(InputNumberTime);
        speedTimeBean.direction = direction;
        speedTimeBean.xTime = time;
        speedTimeBean.speed = speed;
        return speedTimeBean;
    }

    private static short getSpeed(String speed) {
        float xSpeed = Float.parseFloat(speed);
        if (xSpeed > 420) {
            xSpeed = 420;
        }
        if (xSpeed < 0) {
            xSpeed = 0;
        }
        return (short) xSpeed;
    }

    private static long getTime(String time) {
        long xTime = 1000;
        if (!TextUtils.isEmpty(time)) {
            xTime = (long) (Double.parseDouble(time) * 1000);
        }
        return xTime;
    }
}
