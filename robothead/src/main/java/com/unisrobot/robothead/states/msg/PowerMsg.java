package com.unisrobot.robothead.states.msg;

import com.unisrobot.robothead.states.beans.UartMsg;
import com.unisrobot.robothead.uart.bean.PowerBean;

/**
 * Created by Administrator on 2018/5/15.
 */

public class PowerMsg extends UartMsg<PowerBean> {
        public PowerMsg(int msgType, PowerBean msgData) {
                super(msgType, msgData);
        }

}
