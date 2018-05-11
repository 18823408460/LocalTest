package com.unisrobot.robothead.util;

import android.util.Log;

import com.unisrobot.robothead.constant.CmdConstant;
import com.unisrobot.robothead.constant.McuMsgTypeConstant;
import com.unisrobot.robothead.entitybean.response.CsbResponsePacket;
import com.unisrobot.robothead.entitybean.McuResponsePacket;
import com.unisrobot.robothead.entitybean.response.TouChuanResponsePacket;

/**
 * Created by Administrator on 2018/5/11.
 */

public class PacketUtil {
        /**
         * 获取校验码
         *
         * @param data
         * @return
         */
        public static byte getCheckCode(byte[] data) {
                byte code = 0;
                for (int i = 0, len = data.length; i < len; i++) {
                        code += data[i];
                }
                return code;
        }

        public static byte getCheckCode(byte[] data, int start, int end) {
                byte code = 0;
                for (int i = start, len = end; i < len; i++) {
                        code += data[i];
                }
                return code;
        }

        /**
         * 数据包是否 有效（根据校验码计算）
         *
         * @param data
         * @return
         */
        public static boolean isValid(byte[] data) {
                return getCheckCode(data, 4, data.length - 3) == data[data.length - 2];
        }

        public static String bytesToHex(byte[] src) {
                StringBuilder stringBuilder = new StringBuilder("");
                if (src == null || src.length <= 0) {
                        return null;
                }
                for (int i = 0; i < src.length; i++) {
                        int v = src[i] & 0xFF;
                        String hv = Integer.toHexString(v);
                        if (hv.length() < 2) {
                                stringBuilder.append(0);
                        }
                        stringBuilder.append(hv + ",  ");
                }
                return stringBuilder.toString();
        }

        /**
         * 拼接两个byte【】
         *
         * @param data1
         * @param data2
         * @return
         */
        public static byte[] mergerBytes(byte[] data1, byte[] data2) {
                byte[] data3 = new byte[data1.length + data2.length];
                System.arraycopy(data1, 0, data3, 0, data1.length);
                System.arraycopy(data2, 0, data3, data1.length, data2.length);
                return data3;
        }

        /**
         * 解析数据包，最终转换成 McuResponsePacket
         *
         * @param mcuData
         * @return
         */
        private static final String TAG = "PacketUtil";

        public static McuResponsePacket parse(byte[] mcuData) {
//                byte mcuCmdType = (byte) (mcuData[0] &  //这里是负值
                int mcuCmdType =  (mcuData[0] & 0xff);
                Log.e(TAG, "parse: " + PacketUtil.bytesToHex(mcuData));
                switch (mcuCmdType) {
                        case McuMsgTypeConstant.Msg_Arm_Error: {
                                break;
                        }
                        case McuMsgTypeConstant.Msg_CSB: {
                                return (McuResponsePacket) new CsbResponsePacket().decodeBytes(mcuData);
                        }
                        case McuMsgTypeConstant.Msg_TouChuan: {
                                return (McuResponsePacket) new TouChuanResponsePacket().decodeBytes(mcuData);
                        }
                }
                return null;
        }

        /**
         * 是否是透传消息
         *
         * @param cmd
         * @return
         */
        public static boolean isTouChuanMsg(int cmd) {
                return cmd == CmdConstant.TouChuan;
        }

        /**
         * 是否是mcu 的响应消息
         *
         * @param cmd
         * @return
         */
        public static boolean isMcuReponseMsg(int cmd) {
                return cmd == CmdConstant.ActionFinish || cmd == CmdConstant.CsbDataResponse;
        }
}
