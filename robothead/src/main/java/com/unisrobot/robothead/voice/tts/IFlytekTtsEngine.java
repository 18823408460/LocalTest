package com.unisrobot.robothead.voice.tts;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.util.ResourceUtil;

/**
 * Created by Administrator on 2018/5/16.
 */

public class IFlytekTtsEngine extends ITtsEngine {
        private static final String TAG = "IFlytekTtsEngine";
        private Context mContext;
        private String mEngineType = SpeechConstant.TYPE_LOCAL;
        private String name ="xiaoyan";
        // 语音合成对象
        private SpeechSynthesizer mTts;

        public IFlytekTtsEngine(Context context) {
                this.mContext = context;
        }

        @Override
        public void init() {
                mTts = SpeechSynthesizer.createSynthesizer(mContext,
                        new InitListener() {
                                @Override
                                public void onInit(int code) {
                                        if (code != ErrorCode.SUCCESS) {
                                                Log.i(TAG, "初始化失败,错误码：" + code);
                                        }
                                        setParam();
                                }
                        });
        }

        @Override
        public boolean isPlay() {
                if (null != mTts) {
                        return mTts.isSpeaking();
                } else {
                        return false;
                }
        }

        @Override
        public void play(final String data, final IPlayResult ittsResult) {
                Log.e(TAG, "play: "+data );
                stop();
                if (mTts != null) {
                        mTts.startSpeaking(data, new SynthesizerListener() {
                                @Override
                                public void onSpeakBegin() {
                                        Log.d(TAG, "onSpeakBegin: ");
                                }

                                @Override
                                public void onBufferProgress(int i, int i1, int i2, String s) {
                                        //Log.d(TAG, "onBufferProgress: ");
                                }

                                @Override
                                public void onSpeakPaused() {
                                        Log.d(TAG, "onSpeakPaused: ");
                                }

                                @Override
                                public void onSpeakResumed() {
                                        Log.d(TAG, "onSpeakResumed: ");
                                }

                                @Override
                                public void onSpeakProgress(int i, int i1, int i2) {
                                       // Log.d(TAG, "onSpeakProgress: ");
                                }

                                @Override
                                public void onCompleted(SpeechError speechError) {
                                        Log.d(TAG, "onCompleted: "+speechError);
                                        if (speechError == null) {
                                                ittsResult.onPlayEnd(true, data);
                                        } else {
                                                ittsResult.onPlayEnd(false, data);
                                        }
                                }

                                @Override
                                public void onEvent(int i, int i1, int i2, Bundle bundle) {
                                        Log.d(TAG, "onEvent: ");
                                }
                        });
                }
        }

        @Override
        public void stop() {
                if (null != mTts) {
                        // 停止说话
                        mTts.stopSpeaking();
                }
        }

        /**
         * 参数设置
         *
         * @return
         */
        private void setParam() {
                // 清空参数
                mTts.setParameter(SpeechConstant.PARAMS, null);

                mTts.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
                if (SpeechConstant.TYPE_LOCAL.equals(mEngineType)) {
                        // 设置发音人资源路径
                        mTts.setParameter(ResourceUtil.TTS_RES_PATH, getResourcePath());
                } else if (SpeechConstant.TYPE_CLOUD.equals(mEngineType)) {

                }
                // 设置发音人]
                mTts.setParameter(SpeechConstant.VOICE_NAME, name);
                // 设置语速
                mTts.setParameter(SpeechConstant.SPEED, "60");
                // 设置音调
                mTts.setParameter(SpeechConstant.PITCH, "60");
                // 设置音量
                mTts.setParameter(SpeechConstant.VOLUME, "65");
                // 设置播放器音频流类型
                mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
                // 设置播放合成音频打断音乐播放，默认为true
                mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

                // mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH,
                // PathConst.projectPath + "tts/ttsaudio.pcm");
                // 背景音乐 1�? 0 �?
                // mTts.setParameter("bgs", "1");
        }

        /**
         * 获取发音人资源路径
         */
        private String getResourcePath() {
                StringBuffer tempBuffer = new StringBuffer();
                // 合成通用资源
                tempBuffer.append(ResourceUtil.generateResourcePath(mContext,
                        ResourceUtil.RESOURCE_TYPE.assets, "tts/common.jet"));
                tempBuffer.append(";");
                // 发音人资源
                tempBuffer.append(ResourceUtil.generateResourcePath(mContext,
                        ResourceUtil.RESOURCE_TYPE.assets, "tts/xiaoyan.jet"));
                return tempBuffer.toString();
        }
}
