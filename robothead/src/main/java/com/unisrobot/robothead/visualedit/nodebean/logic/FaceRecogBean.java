package com.unisrobot.robothead.visualedit.nodebean.logic;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by Administrator on 2018/7/2.
 */

public class FaceRecogBean extends Node<Long> {
        private long time;

        @Override
        public Long exeNode() {
                time = getTime(1);
                return time;
        }

        public static FaceRecogBean getBean(VpJsonBean.NodeDataBase nodeData) {
                FaceRecogBean faceRecogBean = new FaceRecogBean();
                faceRecogBean.setRobotMsgType(RobotMsgType.Timer);
                return faceRecogBean;
        }
}
