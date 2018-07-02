package com.unisrobot.robothead.visualedit.nodebean.logic;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.nodebean.common.AppendUtil;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by WEI on 2018/6/27.
 * <p>
 * 等待 x s 后 继续执行 下一个 task
 */

public class LogicWaitSecondBean extends Node<Long> {
        // ms
        private long time;

        public static LogicWaitSecondBean getBean(VpJsonBean.NodeDataBase nodeDataBase) {
                LogicWaitSecondBean logicWaitSecondBean = new LogicWaitSecondBean();
                String inputNumber = AppendUtil.getNumberParams(nodeDataBase, 0);
                logicWaitSecondBean.time = (long) (Double.parseDouble(inputNumber) * 1000);
                logicWaitSecondBean.setRobotMsgType(RobotMsgType.Timer);
                return logicWaitSecondBean;
        }

        @Override
        public Long exeNode() {

                return time;
        }
}
