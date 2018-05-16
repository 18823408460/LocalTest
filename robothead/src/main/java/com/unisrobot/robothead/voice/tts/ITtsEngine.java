package com.unisrobot.robothead.voice.tts;

/**
 * Created by Administrator on 2018/5/16.
 */
// is-a  like-a
public abstract class ITtsEngine {
        public abstract void init();

        public abstract boolean isPlay();

        public abstract void play(String data, IPlayResult ittsResult);

        public  abstract void stop();
}
