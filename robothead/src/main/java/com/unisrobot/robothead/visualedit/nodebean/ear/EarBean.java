package com.unisrobot.robothead.visualedit.nodebean.ear;

import android.text.TextUtils;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by WEI on 2018/6/27.
 */

public class EarBean extends Node<Long> {
    private String content;
    private long time;

    public static EarBean getBean(VpJsonBean.NodeDataBase nodeData) {
        EarBean earBean = new EarBean();
        earBean.content = nodeData.Args.get(0).Content;
        earBean.time = 3000 ;
        earBean.setRobotMsgType(RobotMsgType.Timer);
        earBean.setRobotMsgType(RobotMsgType.Recognizer);
        return earBean;
    }

    @Override
    public Long exeNode() {
        if (!TextUtils.isEmpty(content)) {
//            MsgSendUtils.sendStringMsg(MsgType.SEND_RECOGNITION_RESULT, contentPos);
        }
        return time;
    }
}
