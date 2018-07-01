package com.unisrobot.robothead.visualedit.nodebean.language;

import com.unisrobot.robothead.visualedit.nodebean.base.Node;
import com.unisrobot.robothead.visualedit.nodebean.common.VpJsonBean;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by WEI on 2018/6/27.
 */

public class TtsBean extends Node<Long> {
    private String content;
    private long time;

    public static TtsBean getBean(VpJsonBean.NodeDataBase nodeData) {
        TtsBean ttsBean = new TtsBean();
        String content = nodeData.Args.get(0).Content;
        int length = content.length();
        long timeout = length * 450; //每一个字的tts 大概是（350ms，加上传输时间100ms）;
        ttsBean.content = content;
        ttsBean.time = timeout;
        ttsBean.setRobotMsgType(RobotMsgType.PlayEnd);
        ttsBean.setRobotMsgType(RobotMsgType.Timer);
        return ttsBean;
    }

    @Override
    public Long exeNode() {
        //MsgSendUtils.sendStringMsg(MsgType.PLAY_TTS, content);
        return time;
    }
}
