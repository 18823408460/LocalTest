package com.unisrobot.robothead.entitybean;

/**
 * Created by Administrator on 2018/5/11.
 */

import com.unisrobot.robothead.entitybean.Packet;

/**
 * mcu 数据包：
 * <p>
 * 命令码 + 数据
 */
public abstract class McuResponsePacket<T> extends Packet {
        /**
         * 对于透传消息，需要用到消息ID
         */
        private static int seqIDAchor = 0;

        protected abstract T decodeContent(byte[] data);

        protected T data;

        public T getContent() {
                return data;
        }

        public McuResponsePacket() {
                seqID = seqIDAchor;
                seqIDAchor += 1;
                if (seqIDAchor > 65536) {
                        seqIDAchor = 0;
                }
        }

        @Override
        public byte[] encodeBytes() {
                return new byte[0];
        }

        @Override
        public Packet decodeBytes(byte[] rawData) {
                byte[] datas = new byte[rawData.length - 1];
                System.arraycopy(rawData,1,datas,0,datas.length);
                data = decodeContent(datas);
                return this;
        }
}
