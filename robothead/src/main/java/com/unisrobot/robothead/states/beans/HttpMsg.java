package com.unisrobot.robothead.states.beans;

/**
 * Created by Administrator on 2018/5/15.
 */

public class HttpMsg {
        private int msgType;
        private String data;

        public HttpMsg(int msgType, String data) {
                this.msgType = msgType;
                this.data = data;
        }

        public int getMsgType() {
                return msgType;
        }

        public void setMsgType(int msgType) {
                this.msgType = msgType;
        }

        public String getData() {
                return data;
        }

        public void setData(String data) {
                this.data = data;
        }

        @Override
        public String toString() {
                return "HttpMsg{" +
                        "msgType=" + msgType +
                        ", data='" + data + '\'' +
                        '}';
        }
}
