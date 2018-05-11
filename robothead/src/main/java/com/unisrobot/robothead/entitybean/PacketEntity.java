package com.unisrobot.robothead.entitybean;

import com.unisrobot.robothead.interfaces.ISendListener;

/**
 * Created by Administrator on 2018/5/11.
 */

public class PacketEntity {
        private ProtocolPacket sendPacket = null;
        private boolean isNeedAck = false;
        private long lastSendTime = 0;
        private int retryCount = 0;
        private ISendListener callback = null;
        private boolean isHasAcked = false;

        /**
         * 需要响应的消息
         *
         * @param packet
         * @param callback
         */
        public PacketEntity(ProtocolPacket packet, ISendListener callback) {
                this.sendPacket = packet;
                this.callback = callback;
                if (callback == null){
                        this.isNeedAck = false ;
                }else {
                        this.isNeedAck = true;
                }
        }

        /**
         * 不需要响应的消息
         *
         * @param packet
         */
        public PacketEntity(ProtocolPacket packet) {
                this.sendPacket = packet;
                this.isNeedAck = false;
        }

        public ProtocolPacket getPacket() {
                return sendPacket;
        }

        public boolean isNeedAck() {
                return isNeedAck;
        }

        public boolean isHasAcked() {
                return isHasAcked;
        }

        public void setAcked() {
                isHasAcked = true;
        }

        public long getLastSendTime() {
                return lastSendTime;
        }

        public void setLastSendTime(long lastSendTime) {
                this.lastSendTime = lastSendTime;
        }

        public void increaseRetryCoutn() {
                retryCount += 1;
        }

        public void resetRetryCount() {
                retryCount = 0;
        }

        public int getRetryCount() {
                return retryCount;
        }


        public void onSuccess() {
                if (callback != null) {
                        callback.onSuccess();
                }
        }

        public void onFailed(int error) {
                if (callback != null) {
                        callback.onFailed(error);
                }
        }
}
