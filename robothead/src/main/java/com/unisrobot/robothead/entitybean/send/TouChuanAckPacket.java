package com.unisrobot.robothead.entitybean.send;

import com.unisrobot.robothead.constant.CmdConstant;
import com.unisrobot.robothead.constant.TouChuanMsgConstant;
import com.unisrobot.robothead.entitybean.Packet;

/**
 * Created by Administrator on 2018/5/11.
 */

/**
 * 透传消息格式： seqId(2byte) +requestOrReponse(1byte) + msgType(2byte) + msgData
 */
public class TouChuanAckPacket implements Packet {
    private int seqID;

    public TouChuanAckPacket(int seqId) {
        this.seqID = seqId;
    }

    @Override
    public byte[] encodeBytes() {
        byte[] datas = new byte[5];
        int index = 0 ;
        datas[index++] = (byte) CmdConstant.TouChuan;
        datas[index++] = (byte) (seqID >> 8 & 0xff);
        datas[index++] = (byte) (seqID & 0xff);
        datas[index++] = TouChuanMsgConstant.Response;
        return datas;
    }

    @Override
    public Packet decodeBytes(byte[] rawData) {
        return null;
    }
}
