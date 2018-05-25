package com.uurobot.baseframe.bean;

/**
 * Created by Administrator on 2018/5/25.
 */

public class TTBean {
        private String title;
        private String content;

        public TTBean(String title, String content) {
                this.title = title;
                this.content = content;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        @Override
        public String toString() {
                return "TTBean{" +
                        "title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        '}';
        }
}
