package com.unisrobot.robothead.voice.recognition;

/**
 * Created by Administrator on 2018/5/16.
 */

public abstract class IVoiceEngine {

        public abstract void init();

        public abstract void startRecognizer(IVoiceResult iResult);

        public abstract void stopRecognizer();

        public abstract void writeAudio(byte[] data);

        public abstract void setParams(Object paramsKey, Object paramsValue);
}
