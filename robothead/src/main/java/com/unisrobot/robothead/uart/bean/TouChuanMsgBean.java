package com.unisrobot.robothead.uart.bean;

/**
 * Created by Administrator on 2018/5/11.
 */

public class TouChuanMsgBean {
        public int msgType;
        public String msgData;

        public TouChuanMsgBean(int msgType, String msgData) {
                this.msgType = msgType;
                this.msgData = msgData;
        }

        @Override
        public String toString() {
                return "TouChuanMsgBean{" +
                        "msgType=" + msgType +
                        ", msgData='" + msgData + '\'' +
                        '}';
        }
}
