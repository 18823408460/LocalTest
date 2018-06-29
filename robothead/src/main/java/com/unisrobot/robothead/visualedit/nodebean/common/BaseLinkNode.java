package com.unisrobot.robothead.visualedit.nodebean.common;

import android.os.Bundle;
import android.text.TextUtils;

import com.unisrobot.robothead.visualedit.interfaces.INodeExeEnd;
import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by WEI on 2018/6/28.
 */

public abstract class BaseLinkNode {
    private RobotMsgType waitRobotMsgType;
    private INodeExeEnd iNodeExeEnd;

    public BaseLinkNode(INodeExeEnd iNodeExeEnd) {
        this.iNodeExeEnd = iNodeExeEnd;
        waitRobotMsgType = getWaitType();
    }

    public abstract RobotMsgType getWaitType();

    public abstract void exeNode();

    public abstract void stop();

    public void handlerRobotMsg(RobotMsgType robotMsgType, Bundle bundle) {
        if (robotMsgType.equals(waitRobotMsgType)) {//如果等待的消息就是回调的消息
            String subWaitMsg = subWaitMsg();
            String string = bundle.getString(subWaitBundleKey());
            if (!TextUtils.isEmpty(subWaitMsg) && !TextUtils.isEmpty(string) && subWaitMsg.equals(string)) {
                if (iNodeExeEnd != null) {
                    iNodeExeEnd.onEnd();
                }
            }
        }
    }

    public abstract String subWaitMsg();

    public abstract String subWaitBundleKey();
}
