package com.unisrobot.localtest.netRequest.bean;

/**
 * Created by Administrator on 2018/4/13.
 */

public class ResponseData {

        private String message;

        private long startime;

        private int status;

        private String access_token;

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public long getStartime() {
                return startime;
        }

        public void setStartime(long startime) {
                this.startime = startime;
        }

        public int getStatus() {
                return status;
        }

        public void setStatus(int status) {
                this.status = status;
        }

        public String getAccess_token() {
                return access_token;
        }

        public void setAccess_token(String access_token) {
                this.access_token = access_token;
        }

        @Override
        public String toString() {
                return "ResponseData{" +
                        "message='" + message + '\'' +
                        ", startime=" + startime +
                        ", status=" + status +
                        ", access_token='" + access_token + '\'' +
                        '}';
        }
}
