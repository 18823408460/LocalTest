package com.unisrobot.javaread.syn;

/**
 * Created by Administrator on 2018/7/9.
 */

public class EncodeUtils {
        public static byte[] send(int count) {
                byte[] bytes = new byte[10];
                int index = 0;

                bytes[index++] = 0x55;
                bytes[index++] = (byte) 0xaa;

                bytes[index++] = 0x00;
                bytes[index++] = 0x0a;

                bytes[index++] = 0x00;
                bytes[index++] = 0x00;
                bytes[index++] = 0x00;
                bytes[index++] = (byte) count;

                bytes[index++] = 0x0d;
                bytes[index++] = 0x0a;

                return bytes;
        }


        public static String bytesToHexString(byte[] src) {
                StringBuilder stringBuilder = new StringBuilder("");
                if (src == null || src.length <= 0) {
                        return null;
                }
                for (int i = 0; i < src.length; i++) {
                        int v = src[i] & 0xFF;
                        String hv = Integer.toHexString(v);
                        stringBuilder.append("0x");
                        if (hv.length() < 2) {
                                stringBuilder.append(0);
                        }
                        stringBuilder.append(hv);
                        stringBuilder.append(", ");
                }
                return stringBuilder.toString();
        }
}
