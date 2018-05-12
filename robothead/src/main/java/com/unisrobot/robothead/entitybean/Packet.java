package com.unisrobot.robothead.entitybean;

/**
 * Created by Administrator on 2018/5/11.
 */

public abstract class Packet {
        public int seqID;
        /**
         * 数据包编码成 byte数组
         *
         * @return
         */
        public abstract byte[] encodeBytes();

        /**
         * 从byte数组解码成数据包
         *
         * @param rawData
         * @return
         */
        public  abstract Packet decodeBytes(byte[] rawData);
}
