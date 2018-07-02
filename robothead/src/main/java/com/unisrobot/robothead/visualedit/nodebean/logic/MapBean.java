package com.unisrobot.robothead.visualedit.nodebean.logic;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by Administrator on 2018/7/2.
 */

public class MapBean extends Node<Long> {
        private String pointName;

        @Override
        public Long exeNode() {
                return null;
        }

        public static MapBean getBean(VpJsonBean.NodeDataBase nodeDataBase) {
                MapBean mapBean = new MapBean();
                mapBean.pointName = nodeDataBase.Args.get(0).Content;
                mapBean.setRobotMsgType(RobotMsgType.Map);
                return mapBean;
        }
}
