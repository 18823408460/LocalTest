package com.unisrobot.robothead.entitybean.send;

import android.util.Log;

import com.unisrobot.robothead.constant.SendCmdConstant;
import com.unisrobot.robothead.constant.TouChuanMsgConstant;
import com.unisrobot.robothead.entitybean.Packet;
import com.unisrobot.robothead.util.PacketUtil;

/**
 * Created by Administrator on 2018/5/11.
 */

/**
 * 透传消息格式： seqId(2byte) +requestOrReponse(1byte) + msgType(2byte) + msgData
 */
public class TouChuanAckPacket extends Packet {
        private static final String TAG = "TouChuanAckPacket";

        public TouChuanAckPacket(int seqId) {
                this.seqID = seqId;
        }

        @Override
        public byte[] encodeBytes() {
                byte[] datas = new byte[4];
                int index = 0;
                datas[index++] = (byte) SendCmdConstant.TouChuan;
                datas[index++] = (byte) (seqID >> 8 & 0xff);
                datas[index++] = (byte) (seqID & 0xff);
                datas[index++] = TouChuanMsgConstant.Response;
               // Log.e(TAG, "TouChuanAckPacket encodeBytes: " + PacketUtil.bytesToHex(datas));
                return datas;
        }

        @Override
        public Packet decodeBytes(byte[] rawData) {
                return null;
        }


}
