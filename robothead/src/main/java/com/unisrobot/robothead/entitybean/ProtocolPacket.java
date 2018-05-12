package com.unisrobot.robothead.entitybean;

/**
 * Created by Administrator on 2018/5/11.
 */

import com.unisrobot.robothead.constant.ProtocolConstant;
import com.unisrobot.robothead.util.PacketUtil;

/**
 * 协议包：
 * <p>
 * 协议头(2byte) + 长度(2byte) + mcu消息包 + 校验码(1byte) + 协议尾(2byte)
 */
public class ProtocolPacket extends Packet {
        public Packet getDataPacket() {
                return dataPacket;
        }

        public void setDataPacket(Packet dataPacket) {
                this.dataPacket = dataPacket;
        }

        public int getCmdType() {
                return cmdType;
        }

        private int cmdType;
        private Packet dataPacket;

        @Override
        public byte[] encodeBytes() {
                byte[] bytes = dataPacket.encodeBytes();
                int length = bytes.length;
                byte[] protocolBytes = new byte[length + 7];
                int index = 0;
                protocolBytes[index++] = ProtocolConstant.Head_H;
                protocolBytes[index++] = ProtocolConstant.Head_L;
                protocolBytes[index++] = (byte) ((length >> 8) & 0xff);
                protocolBytes[index++] = (byte) (length & 0xff);
                System.arraycopy(bytes, 0, protocolBytes, index, length);
                index += length;
                protocolBytes[index++] = PacketUtil.getCheckCode(bytes);
                protocolBytes[index++] = ProtocolConstant.Tail_H;
                protocolBytes[index++] = ProtocolConstant.Tail_L;

                return protocolBytes;
        }

        private static final String TAG = "ProtocolPacket";
        @Override
        public Packet decodeBytes(byte[] rawData) {
                int length = rawData.length;
                byte[] mcuDatas = new byte[length - 7];
                System.arraycopy(rawData, 4, mcuDatas, 0, length - 7);
                cmdType = mcuDatas[0]&0xff;
                dataPacket = PacketUtil.parse(mcuDatas);
                return this;
        }
}
