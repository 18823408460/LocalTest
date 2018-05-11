package com.unisrobot.robothead.entitybean;

/**
 * Created by Administrator on 2018/5/11.
 */

public interface Packet {

        /**
         * 数据包编码成 byte数组
         *
         * @return
         */
        byte[] encodeBytes();

        /**
         * 从byte数组解码成数据包
         *
         * @param rawData
         * @return
         */
        Packet decodeBytes(byte[] rawData);
}
