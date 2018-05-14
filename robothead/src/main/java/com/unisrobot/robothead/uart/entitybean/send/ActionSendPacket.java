package com.unisrobot.robothead.uart.entitybean.send;

import com.unisrobot.robothead.uart.constant.SendCmdConstant;
import com.unisrobot.robothead.uart.entitybean.McuSendPacket;

/**
 * Created by Administrator on 2018/5/11.
 */

public class ActionSendPacket extends McuSendPacket {
        public enum ActionLib {
                LibOne(0),
                LibTwo(1),;
                private byte value;

                ActionLib(int i) {
                        this.value = (byte) i;
                }
        }

        private int actionId;
        private byte head;
        private byte actionLib;

        public ActionSendPacket(int actionId, boolean useHead, ActionLib actionLib) {
                this.actionId = actionId;
                this.head = (byte) (useHead ? 0x01 : 0x00);
                this.actionLib = actionLib.value;
        }

        public ActionSendPacket(int actionId, boolean useHead) {
                this(actionId, useHead, ActionLib.LibOne);
        }

        @Override
        protected byte[] getContent() {
                byte[] bytes = new byte[6];
                int index = 0;
                bytes[index++] = (byte) (actionId & 0xff);
                bytes[index++] = (byte) ((actionId >> 8) & 0xff);
                bytes[index++] = (byte) ((actionId >> 16) & 0xff);
                bytes[index++] = (byte) ((actionId >> 24) & 0xff);
                bytes[index++] = head;
                bytes[index++] = actionLib;
                return bytes;
        }

        @Override
        protected int getMsgType() {
                return SendCmdConstant.ActionCrl;
        }
}
