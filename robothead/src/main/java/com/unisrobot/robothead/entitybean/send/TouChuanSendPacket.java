package com.unisrobot.robothead.entitybean.send;

import android.util.Log;

import com.unisrobot.robothead.bean.TouChuanMsgBean;
import com.unisrobot.robothead.constant.CmdConstant;
import com.unisrobot.robothead.entitybean.McuSendPacket;
import com.unisrobot.robothead.util.PacketUtil;

/**
 * Created by Administrator on 2018/5/11.
 */

/**
 * 透传消息格式： seqId(2byte) + msgType(2byte) + msgData
 */
public class TouChuanSendPacket extends McuSendPacket {
        private static final String TAG = "TouChuanSendPacket";
        private TouChuanMsgBean touChuanMsgBean;

        public TouChuanSendPacket(TouChuanMsgBean touChuanMsgBean) {
                this.touChuanMsgBean = touChuanMsgBean;
        }

        @Override
        protected byte[] getContent() {
                Log.e(TAG, "getContent: "+ touChuanMsgBean );
                int msgType = touChuanMsgBean.msgType;
                String msgData = touChuanMsgBean.msgData;
                int length = msgData.length();
                int seqID = getSeqID();

                byte[] datas = new byte[length + 4];
                int index = 0;
                datas[index++] = (byte) (seqID >> 8 & 0xff);
                datas[index++] = (byte) (seqID & 0xff);
                datas[index++] = (byte) (msgType >> 8 & 0xff);
                datas[index++] = (byte) (msgType & 0xff);

                System.arraycopy(msgData.getBytes(), 0, datas, 4, length);
                Log.e(TAG, "getContent: "+ PacketUtil.bytesToHex(datas));
                return datas;
        }

        @Override
        protected int getMsgType() {
                return CmdConstant.TouChuan;
        }
}
