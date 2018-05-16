package com.unisrobot.robothead.voice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.unisrobot.robothead.IVoiceBinder;
import com.unisrobot.robothead.IVoiceCallback;
import com.unisrobot.robothead.R;

/**
 * Created by Administrator on 2018/5/16.
 */

public class VoiceActivity extends AppCompatActivity {
        private ServiceConnection conn;
        private IVoiceBinder iVoiceBinder;
        private static final String TAG = "VoiceActivity";

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_voice);
                Log.e(TAG, "onCreate: ");
                conn = new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {
                                Log.e(TAG, "onServiceConnected: ");
                                iVoiceBinder = IVoiceBinder.Stub.asInterface(service);
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName name) {
                                Log.e(TAG, "onServiceDisconnected: ");
                        }
                };
                Intent intent = new Intent("com.unisrobot.robothead.voiceservice");
                intent.setClassName("com.unisrobot.robothead",
                        "com.unisrobot.robothead.voice.VoiceService");
                intent.setPackage("com.unisrobot.robothead");
                bindService(intent, conn, BIND_AUTO_CREATE);

        }

        @Override
        protected void onDestroy() {
                super.onDestroy();
                if (conn != null) {
                        unbindService(conn);
                }
        }

        public void start(View view) throws RemoteException {
                iVoiceBinder.startRecogniger(new IVoiceCallback() {
                        @Override
                        public void onResult(boolean state, String data) throws RemoteException {
                                Log.e(TAG, "onResult: " + state);
                                Log.e(TAG, "onResult: " + data);
                        }

                        @Override
                        public IBinder asBinder() {
                                return null;
                        }
                });
        }

        public void stop(View view) {
                try {
                        iVoiceBinder.stopRecognizer();
                } catch (RemoteException e) {
                        e.printStackTrace();
                }
        }
}
