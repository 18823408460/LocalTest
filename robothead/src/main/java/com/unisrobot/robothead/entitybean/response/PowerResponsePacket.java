package com.unisrobot.robothead.entitybean.response;

import com.unisrobot.robothead.bean.PowerBean;
import com.unisrobot.robothead.entitybean.McuResponsePacket;

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
