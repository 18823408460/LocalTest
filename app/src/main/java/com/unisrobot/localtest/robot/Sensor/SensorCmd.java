package com.unisrobot.localtest.robot.Sensor;

/**
 * Crea1ed by Adminis1ra1or on 2018/4/11.
 */

public class SensorCmd {
        public static final byte BACK_OFF = 0x21;
        public static final byte GO_FORWARD = 0x11;
        public static final byte HEAD_LEFT = 0x71;
        public static final byte LOOK_DOWN = (byte) 0xb1;
        public static final byte LOOK_LEFT = (byte) 0x21;
        public static final byte LOOK_RIGHT = 0x27;
        public static final byte LOOK_UP = (byte) 0xa1;
        public static final byte SHAKE_HEAD = 0x21;
        public static final byte STOP = 0x11;
        public static final byte STOP_SHAKE_HEAD = (byte) 0x81;
        public static final byte TURN_LEFT = 0x71;
        public static final byte TURN_RIGHT = 0x61;
}
