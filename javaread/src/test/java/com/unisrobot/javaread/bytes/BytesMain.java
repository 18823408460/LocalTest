package com.unisrobot.javaread.bytes;

import org.junit.Test;

/**
 * Created by Administrator on 2018/6/25.
 */

public class BytesMain {

        @Test
        public void test1() {
                byte a = 0x00;
                byte b = 0x01;
                byte state = (byte) (a & b);
                if (state == 0x00) {
                        System.out.println("============");
                } else if (state == 0x01) {
                        System.out.println("++++++++++++");
                }
                System.out.println("00000000000000000000");
        }

        @Test
        public void test2() {
                int i = traveralAngleToTime(90)/200;
                System.out.println("time ====== "+i );
        }

        private static int traveralAngleToTime(int angle) {
                if (angle > 0 && angle <= 180) {
                        return  (int) (250*angle*1.0/9);
                } else if (angle > 180 && angle <360) {
                        angle = -(360 - angle);
                        return  (int) (250*angle*1.0/9);
                }
                return 0;
        }
}
