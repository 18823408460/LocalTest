package com.unisrobot.localtest.robot.Sensor;

import android.util.Log;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/4/11.
 */

public class SensorEvent {

        public static final int ERROR_TYPE = -0x1;

        public static final int ERROR_VALUE = -0x1;

        public static final int INFO_BATTERY = 0x30;

        public static final int INFO_CHARGE_CANCEL = 0x10;

        public static final int INFO_CHARGING = 0x13;

        public static final int INFO_SHUT_DOWN = 0x41;

        public static final int SENSOR_ARSE = 0x81;

        public static final int SENSOR_CHEST = 0x80;

        public static final int SENSOR_HEAD = 0x71;

        public static final int SENSOR_LEFT_EAR = 0x7f;

        public static final int SENSOR_LEFT_HAND = 0x7d;

        public static final int SENSOR_NECK = 0x73;

        public static final int SENSOR_RIGHT_EAR = 0x7e;

        public static final int SENSOR_RIGHT_HAND = 0x7c;

        byte[] mCommand;

        int mLength;

        private int mType;

        private int mValue = 0x0;

        public String toString() {
                return "SensorEvent{mLength=" + mLength + ", mCommand=" + Arrays.toString(mCommand) + '}';
        }

        SensorEvent(byte[] command, int length) {
                mCommand = command;
                mLength = length;
                Log.d("SensorEvent", "getObservable#call: ====:" + length);
                if (length < 0x5) {
                        mType = -0x1;
                        return;
                }
                mType = (command[0x4] & 0xff);
                if (0x30 == mType) {
                        if (length < 0x6) {
                                mValue = -0x1;
                                return;
                        }
                        mValue = (command[0x5] & 0xff);
                }
        }

        public int getType() {
                return mType;
        }

        public int getValue() {
                return mValue;
        }
}
