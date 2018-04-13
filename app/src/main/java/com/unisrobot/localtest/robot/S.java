package com.unisrobot.localtest.robot;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class S {
        private int errorCode;

        private String message;

        private DataBean data;

        public int getErrorCode() {
                return errorCode;
        }

        public void setErrorCode(int errorCode) {
                this.errorCode = errorCode;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public DataBean getData() {
                return data;
        }

        public void setData(DataBean data) {
                this.data = data;
        }

        public static class DataBean {

                private String content;

                private double similarity;

                private List<?> relatedQuestions;

                public String getContent() {
                        return content;
                }

                public void setContent(String content) {
                        this.content = content;
                }

                public double getSimilarity() {
                        return similarity;
                }

                public void setSimilarity(double similarity) {
                        this.similarity = similarity;
                }

                public List<?> getRelatedQuestions() {
                        return relatedQuestions;
                }

                public void setRelatedQuestions(List<?> relatedQuestions) {
                        this.relatedQuestions = relatedQuestions;
                }

                @Override
                public String toString() {
                        return "DataBean{" +
                                "content='" + content + '\'' +
                                ", similarity=" + similarity +
                                ", relatedQuestions=" + relatedQuestions +
                                '}';
                }
        }

        @Override
        public String toString() {
                return "S{" +
                        "errorCode=" + errorCode +
                        ", message='" + message + '\'' +
                        ", data=" + data +
                        '}';
        }
}
