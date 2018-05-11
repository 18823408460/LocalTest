package com.unisrobot.robothead.entitybean.response;

import android.util.Log;

import com.unisrobot.robothead.bean.TouChuanMsgBean;
import com.unisrobot.robothead.entitybean.McuResponsePacket;
import com.unisrobot.robothead.util.PacketUtil;

/**
 * Created by Administrator on 2018/5/11.
 * <p>
 * 透传消息格式： seqId(2byte) + msgType(2byte) + msgData
 */

public class TouChuanResponsePacket extends McuResponsePacket<TouChuanMsgBean> {
        private static final String TAG = "TouChuanResponsePacket";


        @Override
        protected TouChuanMsgBean decodeContent(byte[] data) {
                seqID = ((data[0] & 0xff) << 8) | (data[1] & 0x0ff);
                Log.e(TAG, "decodeContent: sepId====="+seqID );
                int msgType = ((data[2] & 0xff) << 8) | (data[3] & 0x0ff);
                byte[] msgDatas = new byte[data.length - 4];
                System.arraycopy(data, 4, msgDatas, 0, data.length - 4);
                String msgData = new String(msgDatas);
                return new TouChuanMsgBean(msgType, msgData);
        }
}
