package com.unisrobot.robothead.visualedit.nodebean.eye;

import android.text.TextUtils;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.NodeEvent;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by WEI on 2018/6/27.
 */

public class EyeFeelingBean extends Node<Long> {
    private String event;
    private String content;
    private long time;

    public static EyeFeelingBean getBean(VpJsonBean.NodeDataBase nodeDataBase) {
        EyeFeelingBean eyeFeeling = new EyeFeelingBean();
        eyeFeeling.content = nodeDataBase.Args.get(0).Content;
        eyeFeeling.event = nodeDataBase.Event;
        eyeFeeling.setRobotMsgType(RobotMsgType.Timer);
        return eyeFeeling;
    }

    @Override
    public Long exeNode() {
        time = 1000;
        if (!TextUtils.isEmpty(event)) {
            // 一种是控制眼睛动画
            // 一种是控制胸口pad 动画
            switch (event) {
                case NodeEvent.Eyes.EMOTION:
                    break;
                case NodeEvent.Eyes.SCREEN_ANIM:
                    break;
                case NodeEvent.Eyes.SMART_DOCTOR:
                    break;
                case NodeEvent.Eyes.SMART_TREE:
                    break;
                case NodeEvent.Eyes.GOOD_WORLD:
                    break;
            }
        }
        return time;
    }
}
