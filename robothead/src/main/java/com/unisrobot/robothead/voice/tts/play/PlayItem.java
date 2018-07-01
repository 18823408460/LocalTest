package com.unisrobot.robothead.voice.tts.play;

/**
 * Created by Administrator on 2018/5/10.
 */

public class PlayItem {
        public ContentType type;
        public String content;
        public Runnable nextRun;

        public PlayItem(ContentType type, String content, Runnable nextRun) {
                this.type = type;
                this.content = content;
                this.nextRun = nextRun;
        }

        public ContentType getType() {
                return type;
        }

        public void setType(ContentType type) {
                this.type = type;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }

        public Runnable getNextRun() {
                return nextRun;
        }

        public void setNextRun(Runnable nextRun) {
                this.nextRun = nextRun;
        }

        @Override
        public String toString() {
                return "PlayItem{" +
                        "type=" + type +
                        ", contentPos='" + content + '\'' +
                        ", nextRun=" + nextRun +
                        '}';
        }
}
