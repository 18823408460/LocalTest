package com.unisrobot.robothead.states.msg;

import com.unisrobot.robothead.states.beans.UartMsg;

/**
 * Created by Administrator on 2018/5/15.
 */

public class TouChuanMsg extends UartMsg<String> {

        public TouChuanMsg(int msgType, String msgData) {
                super(msgType, msgData);
        }
}
