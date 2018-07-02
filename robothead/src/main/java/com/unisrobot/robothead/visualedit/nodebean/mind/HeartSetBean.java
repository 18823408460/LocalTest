package com.unisrobot.robothead.visualedit.nodebean.mind;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendUtil;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by Administrator on 2018/7/2. 心形bean
 */

public class HeartSetBean extends Node<Long> {
        private long time;
        private String color;
        private String value;

        public String getColor() {
                return color;
        }

        public String getValue() {
                return value;
        }

        public void setValue(String value) {
                this.value = value;
        }

        @Override
        public Long exeNode() {
                return time;
        }


        public static HeartSetBean getBean(VpJsonBean.NodeDataBase nodeData) {
                HeartSetBean heartBean = new HeartSetBean();
                heartBean.color = nodeData.Pictures.get(0).Picture;
                heartBean.value = AppendUtil.getNumberParams(nodeData, 0);
                heartBean.time = getTime(1);
                heartBean.setRobotMsgType(RobotMsgType.Timer);
                return heartBean;
        }
}
