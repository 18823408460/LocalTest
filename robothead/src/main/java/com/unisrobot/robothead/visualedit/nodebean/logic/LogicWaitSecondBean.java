package com.unisrobot.robothead.visualedit.nodebean.logic;

import com.unisrobot.robothead.visualedit.VpJsonBean;
import com.unisrobot.robothead.visualedit.nodebean.AppendUtil;

/**
 * Created by WEI on 2018/6/27.
 * <p>
 * 等待 x s 后 继续执行 下一个 task
 */

public class LogicWaitSecondBean {
    // ms
    private long time;

    public static LogicWaitSecondBean getBean(VpJsonBean.NodeDataBase nodeDataBase) {
        LogicWaitSecondBean logicWaitSecondBean = new LogicWaitSecondBean();
        VpJsonBean.InputNumber inputNumber = AppendUtil.getInputNumber(nodeDataBase, 0);
        logicWaitSecondBean.time = (long) (Double.parseDouble(inputNumber.Number) * 1000);
        return logicWaitSecondBean;
    }

    public void exeNode() {

    }
}
