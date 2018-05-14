package com.unisrobot.robothead.uart.entitybean.response;

import com.unisrobot.robothead.uart.bean.PowerBean;
import com.unisrobot.robothead.uart.entitybean.McuResponsePacket;

/**
 * Created by Administrator on 2018/5/11.
 */

public class PowerResponsePacket extends McuResponsePacket<PowerBean> {
    private static final String TAG = "PowerResponsePacket";

    @Override
    protected PowerBean decodeContent(byte[] data) {
        
        return null;
    }
}
