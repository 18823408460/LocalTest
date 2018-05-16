package com.unisrobot.robothead.voice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.iflytek.cae.CAEError;
import com.iflytek.cae.CAEListener;
import com.unisrobot.robothead.IVoiceBinder;
import com.unisrobot.robothead.IVoiceCallback;
import com.unisrobot.robothead.voice.cae.CAEEngine;
import com.unisrobot.robothead.voice.recognition.IFlytekVoiceEngine;
import com.unisrobot.robothead.voice.recognition.IVoiceEngine;
import com.unisrobot.robothead.voice.recognition.IVoiceResult;
import com.unisrobot.robothead.voice.tts.IPlayResult;
import com.unisrobot.robothead.voice.tts.MixPlayMgr;
import com.unisrobot.robothead.voice.tts.play.ContentType;
import com.unisrobot.robothead.voice.tts.play.PlayItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/15.
 */

public class VoiceService extends Service {
        private static final String TAG = "VoiceService";
        private CAEEngine mCaeEngine;
        private IVoiceEngine iVoiceEngine;
        private MixPlayMgr mixPlayMgr;
        private boolean startRecognize = false;

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
                return new VoiceBinder();
        }

        @Override
        public void onCreate() {
                super.onCreate();
                initData();
                initWakeup();
                initVoiceEngine();
        }

        private void initData() {
                mixPlayMgr = MixPlayMgr.getMixPlayMgr();
        }

        private void initWakeup() {
                mCaeEngine = CAEEngine.createInstance(getApplicationContext());
                mCaeEngine.setCAEListener(new CAEListener() {
                        @Override
                        public void onWakeup(String s) {
                                Log.e(TAG, "onWakeup: " + s);
                                PlayItem playItem = new PlayItem(ContentType.TEXT, "唤醒成功", null);
                                List<PlayItem> playItems = new ArrayList<>();
                                playItems.add(playItem);
                                mixPlayMgr.playItems(playItems, new IPlayResult() {
                                        @Override
                                        public void onPlayEnd(boolean state, String pathOrData) {
                                                Log.e(TAG, "onPlayEnd: " + pathOrData + "  " + Thread.currentThread().getName());
                                                startRecognizer(null);
                                        }
                                });
                        }

                        @Override
                        public void onAudio(byte[] bytes, int i, int i1, int i2) {
                                if (startRecognize) {
                                        if (iVoiceEngine != null) {
                                                iVoiceEngine.writeAudio(bytes);
                                        }
                                }
                        }

                        @Override
                        public void onError(CAEError caeError) {
                                Log.e(TAG, "onError: ");
                        }

                        @Override
                        public void onSuspend() {
                                Log.e(TAG, "onSuspend: ");
                        }
                });
                mCaeEngine.startWakeUp();
        }

        private void initVoiceEngine() {
                iVoiceEngine = new IFlytekVoiceEngine(getApplicationContext());
                iVoiceEngine.init();
        }

        private void startRecognizer(final IVoiceCallback iVoiceCallback) {
                if (iVoiceEngine != null) {
                        startRecognize = true;
                        iVoiceEngine.startRecognizer(new IVoiceResult() {
                                @Override
                                public void onResult(String data) {
                                        startRecognize = false;
                                        Log.d(TAG, "onResult: ");
                                        if (iVoiceCallback != null) {
                                                try {
                                                        iVoiceCallback.onResult(true, data);
                                                } catch (RemoteException e) {
                                                        e.printStackTrace();
                                                }
                                        }
                                }

                                @Override
                                public void onError(String msg) {
                                        Log.d(TAG, "onError: ");
                                        startRecognize = false;
                                        if (iVoiceCallback != null) {
                                                try {
                                                        iVoiceCallback.onResult(false, msg);
                                                } catch (RemoteException e) {
                                                        e.printStackTrace();
                                                }
                                        }
                                }
                        });
                }
        }

        private class VoiceBinder extends IVoiceBinder.Stub {
                private IVoiceCallback iVoiceCallback;

                @Override
                public void startRecogniger(IVoiceCallback callback) throws RemoteException {
                        Log.d(TAG, "startRecogniger: ");
                        this.iVoiceCallback = callback;
                        startRecognizer(iVoiceCallback);
                }


                @Override
                public void stopRecognizer() throws RemoteException {
                        Log.d(TAG, "stopRecognizer: ");
                        startRecognize = false;
                        if (iVoiceEngine != null) {
                                iVoiceEngine.stopRecognizer();
                                iVoiceCallback = null;
                        }
                }
        }

}
