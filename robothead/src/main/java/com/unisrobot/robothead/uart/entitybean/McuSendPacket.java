package com.unisrobot.robothead.uart.entitybean;

/**
 * Created by Administrator on 2018/5/11.
 */

/**
 * mcu 数据包：
 * <p>
 * 命令码 + 数据
 */
public abstract class McuSendPacket extends Packet {
        private static final String TAG = "McuSendPacket";
        public int getSeqID() {
                return seqID;
        }

        /**
         * 对于透传消息，需要用到消息ID
         */
        private static int seqIDAchor = 0;

        protected abstract byte[] getContent();

        protected abstract int getMsgType();

        public McuSendPacket() {
                seqID = seqIDAchor;
                seqIDAchor += 1;
                if (seqIDAchor > 65536) {
                        seqIDAchor = 0;
                }
        }

        @Override
        public byte[] encodeBytes() {
                byte[] content = getContent();
               // Log.e(TAG, "encodeBytes: "+ PacketUtil.bytesToHex(content));
                int msgType = getMsgType();
                byte[] datas = new byte[content.length + 1];
                datas[0] = (byte) msgType;
                System.arraycopy(content, 0, datas, 1, content.length);
                return datas;
        }

        @Override
        public Packet decodeBytes(byte[] rawData) {
                return this;
        }
}
