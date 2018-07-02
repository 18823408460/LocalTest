package com.unisrobot.robothead.visualedit.nodebean.percetion;

import android.os.Bundle;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by Administrator on 2018/7/2.
 */

public class WaitTouchBean extends Node {
        private String sensorName;

        @Override
        public Object exeNode() {
                return null;
        }

        public static WaitTouchBean getBean(VpJsonBean.NodeDataBase nodeDataBase) {
                WaitTouchBean waitTouchBean = new WaitTouchBean();
                waitTouchBean.sensorName = nodeDataBase.Args.get(0).Content;
                waitTouchBean.setRobotMsgType(RobotMsgType.SensorThreePart);
                Bundle bundle = new Bundle();
                bundle.putString(Bundle_SensorName, waitTouchBean.sensorName);
                waitTouchBean.setBundle(bundle);
                return waitTouchBean;
        }
}
