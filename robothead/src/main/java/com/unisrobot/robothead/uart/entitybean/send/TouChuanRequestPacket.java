package com.unisrobot.robothead.uart.entitybean.send;

import android.util.Log;

import com.unisrobot.robothead.uart.bean.TouChuanMsgBean;
import com.unisrobot.robothead.uart.constant.SendCmdConstant;
import com.unisrobot.robothead.uart.constant.TouChuanMsgConstant;
import com.unisrobot.robothead.uart.entitybean.McuSendPacket;

/**
 * Created by Administrator on 2018/5/11.
 */

/**
 * 透传消息格式： seqId(2byte) +requestOrReponse(1byte) + msgType(2byte) + msgData
 */
public class TouChuanRequestPacket extends McuSendPacket {
    private static final String TAG = "TouChuanRequestPacket";
    private TouChuanMsgBean touChuanMsgBean;

    public TouChuanRequestPacket(TouChuanMsgBean touChuanMsgBean) {
        super();
        this.touChuanMsgBean = touChuanMsgBean;
    }

    @Override
    protected byte[] getContent() {
        Log.e(TAG, "getContent: " + touChuanMsgBean);
        int msgType = touChuanMsgBean.msgType;
        String msgData = touChuanMsgBean.msgData;
        int length = msgData.length();
        int seqID = getSeqID();

        byte[] datas = new byte[length + 5];
        int index = 0;
        datas[index++] = (byte) (seqID >> 8 & 0xff);
        datas[index++] = (byte) (seqID & 0xff);
        datas[index++] = TouChuanMsgConstant.Request;
        datas[index++] = (byte) (msgType >> 8 & 0xff);
        datas[index++] = (byte) (msgType & 0xff);

        System.arraycopy(msgData.getBytes(), 0, datas, 5, length);
        //Log.e(TAG, "getContent: " + PacketUtil.bytesToHex(datas));
        return datas;
    }

    @Override
    protected int getMsgType() {
        return SendCmdConstant.TouChuan;
    }
}
