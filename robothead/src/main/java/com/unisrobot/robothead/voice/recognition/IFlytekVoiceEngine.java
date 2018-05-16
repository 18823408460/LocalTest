package com.unisrobot.robothead.voice.recognition;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.util.ResourceUtil;
import com.unisrobot.robothead.utils.NetWorkUtil;

/**
 * Created by Administrator on 2018/5/16.
 */

public class IFlytekVoiceEngine extends IVoiceEngine {
        private static final String TAG = "IFlytekVoiceEngine";

        public enum VoiceMode {
                Diction, //听写模式
                UnderStander //语义理解
        }

        private String grammarID;
        private Context mContext;
        private VoiceMode voiceMode = VoiceMode.Diction;
        private SpeechRecognizer mAsr;
        private String mLanguage = "zh_cn";
        private String mAccent = "mandarin";

        public IFlytekVoiceEngine(Context mContext) {
                this.mContext = mContext;
        }

        @Override
        public void init() {
                mAsr = SpeechRecognizer.createRecognizer(mContext, new InitListener() {
                        @Override
                        public void onInit(int code) {
                                if (code == ErrorCode.SUCCESS) {
                                        Log.e(TAG, "onInit:  success");
                                } else {
                                        Log.e(TAG, "onInit: error");
                                }
                        }
                });
        }

        /**
         * 参数设置
         */
        private void setParam() {
                // 清空参数
                mAsr.setParameter(SpeechConstant.PARAMS, null);
                // 设置识别引擎 混合识别
                if (NetWorkUtil.checkNetworkConnection(mContext)) { // 有网和没网的情况下，阈值设置不一样
                        Log.e(TAG, "setParam: have net"  );
                        mAsr.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_MIX);
                        mAsr.setParameter(SpeechConstant.MIXED_THRESHOLD, "40");// 离线结果混合门限.
                        // 0-100, def:60
                        mAsr.setParameter(SpeechConstant.ASR_THRESHOLD, "40");// 离线结果识别门限0-100,
                        // default:0
                        Log.d(TAG, "mix");
                } else {
                        mAsr.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
                        mAsr.setParameter(SpeechConstant.MIXED_THRESHOLD, "25");// 离线结果混合门限.
                        // 0-100, def:60
                        mAsr.setParameter(SpeechConstant.ASR_THRESHOLD, "20");// 离线结果识别门限0-100,
                        // default:0
                        Log.d(TAG, "local");
                }

                mAsr.setParameter(SpeechConstant.LANGUAGE, mLanguage);
                // 设置语言区域
                mAsr.setParameter(SpeechConstant.ACCENT, mAccent);// lag);

                // // 设置本地识别资源
                mAsr.setParameter(ResourceUtil.ASR_RES_PATH, getResourcePath());
                // 设置返回结果格式
                mAsr.setParameter(SpeechConstant.RESULT_TYPE, "json");
                mAsr.setParameter(SpeechConstant.NLP_VERSION, "2.0");
                // 下面两个参数设置的过小，可能导致录音一开始录制就会结束-------需注意
                // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
                mAsr.setParameter(SpeechConstant.VAD_BOS, "5000");
                // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
                mAsr.setParameter(SpeechConstant.VAD_EOS, "900");
                // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
                mAsr.setParameter(SpeechConstant.ASR_PTT, "0");
                if (Config.OpenFiveMic) {
                        // 远场
                        mAsr.setParameter("ent", "smsfar16k");
                        mAsr.setParameter("aue", "speex-wb;10");
                }
                mAsr.setParameter("mixed_type", "realtime");// 混合模式的类型
                mAsr.setParameter(SpeechConstant.MIXED_TIMEOUT, "10000");// 在线结果超时控制.
                // 0-30000,
                // def:2000
                mAsr.setParameter("local_prior", "1");

                mAsr.setParameter(SpeechConstant.NET_TIMEOUT, "4000");// 语义识别时间设置，实际的时间会长一点点
                mAsr.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");

                // 设置语法构建路径
                mAsr.setParameter(ResourceUtil.GRM_BUILD_PATH, Config.GRAMMAR_PATH);
                // 设置本地识别使用语法id
                mAsr.setParameter(SpeechConstant.LOCAL_GRAMMAR, grammarID);
                mAsr.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
                if (voiceMode == VoiceMode.UnderStander) {
                        // 是否进行语义识别
                        mAsr.setParameter("asr_sch", "1");
                        mAsr.setParameter("nlp_version", "3.0");
                        mAsr.setParameter("scene", "main");
                } else {
                        mAsr.setParameter("asr_sch", "0");// 听写时不需要设置语法ID
                }
        }


        //这里所有的回调都是在 main thread
        private RecognizerListener mRecognizerListener = new RecognizerListener() {
                StringBuffer stringBuffer = new StringBuffer();

                @Override
                public void onResult(final RecognizerResult result, boolean isLast) {
                        stringBuffer.append(result.getResultString()).append("\n");
                        // isLast为true的时候，表示一句话说完，将拼接后的完整的一句话返回
                        if (isLast) {
                                Log.i(TAG, "onResult = " + stringBuffer.toString() + "  "+Thread.currentThread().getName());
                                // 数据回调
                                String temp = stringBuffer.toString();
                                if ((TextUtils.isEmpty(temp)) || (temp != null && temp.length() == 1)) {
                                        resultData(false, Code.NoMatchSceneMsg);  // 识别为空或者为单字当做错误处理
                                } else {
                                        resultData(true, temp);
                                }
                                stringBuffer.delete(0, stringBuffer.length());
                        }
                }

                @Override
                public void onEndOfSpeech() {
                        stop();
                        Log.d(TAG, "onEndOfSpeech: " + Thread.currentThread().getName());
                }

                @Override
                public void onBeginOfSpeech() {
                        stringBuffer.delete(0, stringBuffer.length());
                        Log.d(TAG, "onBeginOfSpeech: " + Thread.currentThread().getName());
                }

                @Override
                public void onError(SpeechError error) {
                        stringBuffer.delete(0, stringBuffer.length());
                        Log.e(TAG, "error = " + error.toString() + "  "+Thread.currentThread().getName());//
                        // error=20002应该是网络出现问题
                        int errorCode = error.getErrorCode();
                        resultData(false, String.valueOf(errorCode));
                }

                @Override
                public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
                        Log.e(TAG, "onEvent: "+Thread.currentThread().getName() );
                        if (eventType == 22002) {
                                stringBuffer.delete(0, stringBuffer.length());
                        } else if (eventType == 22003) {
                        } else if (eventType == 20001) {
                        }
                        // 开始说话 eventType = 22002
                        // 结束说话 eventType = 22003
                        // 开始识别 eventType = 20001
                }

                @Override
                public void onVolumeChanged(int arg0, byte[] arg1) {
                }

        };

        private void resultData(boolean succes, String data) {
                if (iVoiceResult != null) {
                        if (succes) {
                                iVoiceResult.onResult(data);
                        } else {
                                iVoiceResult.onError(data);
                        }
                }
        }

        // 获取识别资源路径
        private String getResourcePath() {
                StringBuffer tempBuffer = new StringBuffer();
                tempBuffer.append(ResourceUtil.generateResourcePath(mContext, ResourceUtil.RESOURCE_TYPE.assets, "asr/common.jet"));
                return tempBuffer.toString();
        }

        private IVoiceResult iVoiceResult;

        @Override
        public void startRecognizer(IVoiceResult iResult) {
                Log.d(TAG, "startRecognizer: ");
                this.iVoiceResult = iResult;
                if (mAsr != null) {
                        stopRecognizer();
                        setParam();
                        mAsr.startListening(mRecognizerListener);
                }
        }

        @Override
        public void stopRecognizer() {
                Log.d(TAG, "stopRecognizer: ");
                if (mAsr != null) {
                        mAsr.stopListening();
                        mAsr.cancel();
                }
        }

        private void stop(){
                if (mAsr != null){
                        mAsr.stopListening();
                }
        }

        @Override
        public void writeAudio(byte[] data) {
                Log.d(TAG, "writeAudio: ");
                if (mAsr != null) {
                        mAsr.writeAudio(data, 0, data.length);
                }
        }

        @Override
        public void setParams(Object paramsKey, Object paramsValue) {

        }
}
