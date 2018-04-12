package com.unisrobot.localtest.netRequest.bean;

/**
 * Created by Administrator on 2018/4/11.
 */

/**
 * {protocol=http/1.1, code=200, message=OK, url=http://95558js.faqrobot.cn/token/getToken?appId=zwxMCclbnc5cJHxZJd&secret=QNbUWR1q7P3500595D0B}
 */
public class Reponse {

        private String protocol;

        private String message;

        private String url;

        private int code;

        public Reponse(String protocol, String message, String url, int code) {
                this.protocol = protocol;
                this.message = message;
                this.url = url;
                this.code = code;
        }

        public Reponse() {
        }

        public String getProtocol() {
                return protocol;
        }

        public void setProtocol(String protocol) {
                this.protocol = protocol;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public int getCode() {
                return code;
        }

        public void setCode(int code) {
                this.code = code;
        }

        @Override
        public String toString() {
                return "Reponse{" +
                        "protocol='" + protocol + '\'' +
                        ", message='" + message + '\'' +
                        ", url='" + url + '\'' +
                        ", code=" + code +
                        '}';
        }
}
