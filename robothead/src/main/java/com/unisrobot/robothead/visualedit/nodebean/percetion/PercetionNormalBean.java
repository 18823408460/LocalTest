package com.unisrobot.robothead.visualedit.nodebean.percetion;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.NodeEvent;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by Administrator on 2018/7/2.
 */

public class PercetionNormalBean extends Node<Long> {
        private long time;

        @Override
        public Long exeNode() {
                return time;
        }

        public static PercetionNormalBean getBean(VpJsonBean.NodeDataBase nodeDataBase) {
                PercetionNormalBean percetionNormalBean = new PercetionNormalBean();
                String event = nodeDataBase.Event;
                percetionNormalBean.time = getTime(2);
                switch (event) {
                        case NodeEvent.Perception.OPEN:
                                break;
                        case NodeEvent.Perception.CLOSE:
                                break;
                        case NodeEvent.Perception.ADD:
                                break;
                        case NodeEvent.Perception.SUB:
                                break;
                        case NodeEvent.Perception.LOOK_PEOPLE:
                                break;
                }
                percetionNormalBean.setRobotMsgType(RobotMsgType.Timer);
                return percetionNormalBean;
        }
}
