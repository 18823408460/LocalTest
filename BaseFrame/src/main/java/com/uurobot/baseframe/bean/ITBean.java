package com.uurobot.baseframe.bean;

/**
 * Created by Administrator on 2018/5/25.
 */

public class ITBean {
        private int resId;
        private String content;

        public ITBean(int resId, String content) {
                this.resId = resId;
                this.content = content;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public int getResId() {
                return resId;
        }

        public void setResId(int resId) {
                this.resId = resId;
        }

        @Override
        public String toString() {
                return "ITBean{" +
                        "resId=" + resId +
                        ", content='" + content + '\'' +
                        '}';
        }
}
