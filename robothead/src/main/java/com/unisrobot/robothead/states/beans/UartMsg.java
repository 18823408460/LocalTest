package com.unisrobot.robothead.states.beans;

/**
 * Created by Administrator on 2018/5/15.
 */

public class UartMsg<T> {
        private int msgType;
        private T msgData;

        public UartMsg(int msgType, T msgData) {
                this.msgType = msgType;
                this.msgData = msgData;
        }

        public int getMsgType() {
                return msgType;
        }

        public void setMsgType(int msgType) {
                this.msgType = msgType;
        }

        public T getMsgData() {
                return msgData;
        }

        public void setMsgData(T msgData) {
                this.msgData = msgData;
        }

        @Override
        public String toString() {
                return "UartMsg{" +
                        "msgType=" + msgType +
                        ", msgData=" + msgData +
                        '}';
        }
}
