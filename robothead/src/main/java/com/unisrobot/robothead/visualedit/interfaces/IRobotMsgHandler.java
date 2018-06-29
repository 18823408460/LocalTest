package com.unisrobot.robothead.visualedit.interfaces;

import android.os.Bundle;

import com.unisrobot.robothead.visualedit.type.RobotMsgType;

/**
 * Created by WEI on 2018/6/29.
 */

public interface IRobotMsgHandler {
    boolean handlerMsg(RobotMsgType robotMsgType, Bundle bundle);
}
