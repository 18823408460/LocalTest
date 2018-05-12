package com.unisrobot.robothead.entitybean.response;

import android.util.Log;

import com.unisrobot.robothead.bean.TouChuanMsgBean;
import com.unisrobot.robothead.constant.TouChuanMsgConstant;
import com.unisrobot.robothead.entitybean.McuResponsePacket;

/**
 * Created by Administrator on 2018/5/11.
 * <p>
 * 透传消息格式： seqId(2byte) +requestOrReponse(1byte) + msgType(2byte) + msgData
 */

public class TouChuanResponsePacket extends McuResponsePacket<TouChuanMsgBean> {
    private static final String TAG = "TouChuanSendResponse";
    public int packetType;

    @Override
    protected TouChuanMsgBean decodeContent(byte[] data) {
        seqID = ((data[0] & 0xff) << 8) | (data[1] & 0x0ff);
        packetType = data[2] & 0xff;
        if (TouChuanMsgConstant.Request == packetType) {
            int msgType = ((data[3] & 0xff) << 8) | (data[4] & 0x0ff);
            byte[] msgDatas = new byte[data.length - 5];
            System.arraycopy(data, 5, msgDatas, 0, data.length - 5);
            String msgData = new String(msgDatas);
            return new TouChuanMsgBean(msgType, msgData);

        } else if (TouChuanMsgConstant.Response == packetType) {
            Log.e(TAG, "decodeContent Response: sepId=====" + seqID);
        }
        return null;
    }
}
