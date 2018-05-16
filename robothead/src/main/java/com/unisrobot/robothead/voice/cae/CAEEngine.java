package com.unisrobot.robothead.voice.cae;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.iflytek.alsa.AlsaRecorder;
import com.iflytek.alsa.AlsaRecorder.PcmListener;
import com.iflytek.cae.CAEError;
import com.iflytek.cae.CAEListener;
import com.iflytek.cae.jni.CAEJni;
import com.iflytek.cae.jni.CAEJni.OutValues;
import com.iflytek.cloud.util.ResourceUtil;
import com.iflytek.cloud.util.ResourceUtil.RESOURCE_TYPE;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * CAE引擎单例对象�?
 *
 * @author admin
 */
public class CAEEngine {

        private static final String TAG = "CAEEngine";
        private static CAEEngine instance;
        private static int caeHandle = 0;
        private PcmListener mPcmListener = null;
        private boolean mIsWakeup = false;
        private CAEListener mListener;
        private AlsaRecorder mRecorder = AlsaRecorder.createInstance(0, 64000); // 1=声卡
        private final static int MSG_AUDIO = 2;
        private final static int MSG_ERROR = 3;

        private Handler mMainHandler = new Handler(Looper.getMainLooper()) {
                public void handleMessage(Message msg) {
                        switch (msg.what) {
                                case MSG_AUDIO:
                                        Bundle bundle = (Bundle) msg.obj;
                                        byte[] audioData = bundle.getByteArray("audioData");
                                        int dataLen = bundle.getInt("dataLen");
                                        int param1 = bundle.getInt("param1");
                                        int param2 = bundle.getInt("param2");
                                        if (null != mListener) {
                                                mListener.onAudio(audioData, dataLen, param1, param2);
                                        }
                                        break;

                                case MSG_ERROR:
                                        CAEError e = (CAEError) msg.obj;
                                        if (null != mListener) {
                                                mListener.onError(e);
                                        }
                                        break;
                                default:
                                        break;
                        }
                }

        };

        /**
         * 唤醒回调函数�?
         *
         * @param angle   唤醒角度
         * @param channel 声道
         * @param power   能量�?
         * @param CMScore 唤醒得分
         * @param beam    波束
         */
        protected void ivwCb(int angle, int channel, float power, int CMScore,
                             int beam) {
                mIsWakeup = true;
                JSONObject json = new JSONObject();
                try {
                        json.put("angle", angle);
                        json.put("channel", channel);
                        json.put("power", power);
                        json.put("CMScore", CMScore);
                        json.put("beam", beam);
                        String result = json.toString();
                        Log.e(TAG, "已经唤醒 " + result);
                        mListener.onWakeup(result);
                } catch (JSONException e) {
                        e.printStackTrace();
                }
        }

        /**
         * 音频回调函数�?
         *
         * @param audioData 音频数据
         * @param dataLen   数据长度
         * @param param1    保留作扩展用
         * @param param2    保留作扩展用
         */
        @SuppressWarnings("unused")
        protected void audioCb(byte[] audioData, int dataLen, int param1,
                               int param2) {
                Bundle bundle = new Bundle();
                bundle.putByteArray("audioData", audioData);
                bundle.putInt("dataLen", dataLen);
                bundle.putInt("param1", param1);
                bundle.putInt("param2", param2);
                mMainHandler.obtainMessage(MSG_AUDIO, bundle).sendToTarget();
        }

        private CAEEngine() {

        }

        /**
         * 开启唤醒
         */
        public void startWakeUp() {
                if (mPcmListener == null) {
                        mPcmListener = new PcmListener() {
                                @Override
                                public void onPcmData(byte[] data, int dataLen) {
                                        writeAudio(data, dataLen); // data=49152 byte
                                }
                        };
                }
                if (!mRecorder.isRecording()) {
                        mRecorder.startRecording(mPcmListener);
                        Log.d(TAG, "start recoder");
                }
        }

        /**
         * 停止唤醒
         */
        public void stopWakeUp() {
                stopSuspend();
                mRecorder.stopRecording();// 停止录音
                destroy();
        }

        /**
         * 复位，重新进入唤醒状态
         */
        public void resetWakeUp() {
                stopSuspend();
                mRecorder.stopRecording();// 停止录音
                reset();
        }

        /**
         * 创建引擎单例对象�?
         *
         * @param
         * @return 成功则返回引擎对象，否则返回null
         */

        public static CAEEngine createInstance(Context context) {
                String resPath = ResourceUtil.generateResourcePath(context,
                        RESOURCE_TYPE.assets, "ivw/ivw_resource_youyou.jet");
                if (TextUtils.isEmpty(resPath)) {
                        instance = null;
                        Log.e(TAG, "Ivw resouce path is null!");
                        throw new RuntimeException("Ivw resouce path is null!  ");
                        //            return null;
                }
                synchronized (CAEEngine.class) {
                        if (null == instance) {
                                instance = new CAEEngine();
                                int ret = CAEJni.CAENew(resPath, "ivwCb", "audioCb", null,
                                        instance);
                                if (0 == ret) {
                                        Log.d(TAG,
                                                "CAE engine create success. handle=" + caeHandle);
                                } else {
                                        Log.e(TAG, "CAE engine create fail. error=" + ret);
                                        instance = null;
                                        caeHandle = 0;
                                }
                        } else {
                                Log.d(TAG, "CAE engine has already existed!");
                        }
                }

                CAEJni.DebugLog(false);
                return instance;
        }

        /**
         * 设置状�?�监听器，用于获取唤醒结果�??16K音频数据和引擎错误�??
         *
         * @param listener 监听�?
         */
        public void setCAEListener(CAEListener listener) {
                mListener = listener;
        }

        private OutValues mExtractOutValues = new OutValues();

        /**
         * �?12�?32bit�?16K采样率单麦数据组合�?�成阵列音频（码率：12288bytes/16ms）中抽取1�?16bit�?
         * 16K采样率音频（码率�?512bytes/16ms）�??
         * <p>
         * <i>注：该函数是比较耗时的同步操作，�?好在新线程中调用�?</i>
         *
         * @param inData    阵列音频数据
         * @param inDataLen 数据长度
         * @param channel   �?要抽取哪�?路音频数据（1~12�?
         * @param outData   16K音频存储�?
         * @return 16K音频数据长度，出错返�?0
         */
        public int extract16K(byte[] inData, int inDataLen, int channel,
                              byte[] outData) {
                if (0 == caeHandle) {
                        Log.e(TAG, "CAE engine is destroyed, invalid call!");
                        return 0;
                }
                int ret = CAEJni.CAEExtract16K(inData, inDataLen, channel, outData,
                        mExtractOutValues);

                if (0 != ret) {
                        mMainHandler.obtainMessage(MSG_ERROR, new CAEError(ret, ""))
                                .sendToTarget();
                        return 0;
                }
                return mExtractOutValues.outValInt;
        }

        /**
         * 写入�?12�?32bit�?16K采样率的单麦数据组合而成阵列录音数据，码率为12288bytes/16ms�?<br>
         * 计算方法�?12 * 16000 / 1000 * 4 * 16ms = 12288bytes
         *
         * @param audioData 音频数据
         * @param dataLen   数据长度
         */
        public void writeAudio(byte[] audioData, int dataLen) {
                synchronized (CAEEngine.class) {
                        if (0 != caeHandle) {
                                int ret = CAEJni.CAEAudioWrite(caeHandle, audioData, dataLen);
                                if (0 != ret) {
                                        mMainHandler.obtainMessage(MSG_ERROR, new CAEError(ret, ""))
                                                .sendToTarget();
                                        Log.e(TAG, "Write audio error. error=" + ret);
                                        destroy();
                                }
                        } else {
                                Log.e(TAG, "CAE engine is destroyed, invalid call!");
                        }
                }
        }

        /**
         * 重置CAE引擎，进入待唤醒状�?��??
         */
        public void reset() {
                synchronized (CAEEngine.class) {
                        if (0 != caeHandle) {
                                int ret = CAEJni.CAEReset(caeHandle);
                                if (0 != ret) {
                                        mMainHandler.obtainMessage(MSG_ERROR, new CAEError(ret, ""))
                                                .sendToTarget();
                                        Log.e(TAG, "CAE engine reset fail. error=" + ret);
                                        destroy();
                                } else {
                                        mIsWakeup = false;
                                        Log.d(TAG, "CAE engine reset sucess.");
                                }
                        } else {
                                Log.e(TAG, "CAE engine is destroyed, invalid call!");
                        }
                }
        }

        /**
         * CAE引擎是否处于唤醒状�?��??
         *
         * @return 是否已唤�?
         */
        public boolean isWakeup() {
                synchronized (CAEEngine.class) {
                        if (0 == caeHandle) {
                                mIsWakeup = false;
                                Log.e(TAG, "CAE engine is destroyed, invalid call!");
                        }
                }

                return mIsWakeup;
        }

        /**
         * 改变拾音波束方向，在唤醒成功之后调用。例如：在角�?0的方位进行唤醒（此时beam�?0），唤醒之后如需�?90度方位拾音，
         * 则调用此函数并设置beam参数�?1�? 调用reset()之后，beam的设置会还原�?
         *
         * @param beam 音束，取值：0-2�?4麦阵列）�?0-3�?5麦阵列）
         */
        public void setRealBeam(int beam) {
                int ret = 0;
                synchronized (CAEEngine.class) {
                        if (0 == caeHandle) {
                                Log.e(TAG, "CAE engine is destroyed, invalid call!");
                                return;
                        }

                        if (!mIsWakeup) {
                                Log.e(TAG, "CAE engine is not awake, invalid call!");
                                return;
                        }

                        ret = CAEJni.CAESetRealBeam(caeHandle, beam);
                }

                if (0 != ret) {
                        Log.d(TAG, "CAE engine setRealBeam fail. error=" + ret);

                        mMainHandler.obtainMessage(MSG_ERROR, new CAEError(ret, ""))
                                .sendToTarget();
                }
        }

        /**
         * �?毁CAE引擎，此后getInstance()方法将返回null�?
         */
        public void destroy() {
                stopSuspend();
                if (0 == caeHandle) {
                        return;
                }

                int ret = 0;

                synchronized (CAEEngine.class) {
                        ret = CAEJni.CAEDestroy(caeHandle);

                        if (0 == ret) {
                                caeHandle = 0;
                                instance = null;

                                Log.d(TAG, "CAE engine destroy sucess.");
                        }
                }

                if (0 != ret) {
                        Log.d(TAG, "CAE engine destroy fail. error=" + ret);
                        mMainHandler.obtainMessage(MSG_ERROR, new CAEError(ret, ""))
                                .sendToTarget();
                }
        }

        /**
         * 停止进入唤醒
         */
        private void stopSuspend() {
                Log.d(TAG, "取消待机");
        }
}
