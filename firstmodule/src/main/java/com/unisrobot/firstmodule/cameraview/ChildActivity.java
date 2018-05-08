package com.unisrobot.firstmodule.cameraview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Administrator on 2018/5/8.
 */

public class ChildActivity extends ParentActivity {
        private volatile String data = "init";
        private static final String TAG = "ChildActivity";

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                Log.e(TAG, "onCreate: " + this);
                data = "onCreate";

                //testHandler();

                if (runnable == null) {
                        runnable = new Runnable() {
                                @Override
                                public void run() {
                                        Log.e(TAG, " run     " + data);
                                }
                        };
                        new Thread(runnable).start();
                } else {
                        new Thread(runnable).start();
                        Log.e(TAG, "onCreate: runnable not null  ");
                }
        }




        @Override
        protected void onDestroy() {
                super.onDestroy();
                Log.e(TAG, "onDestroy: " + this);
                data = "onDestroy";
        }

        private void testHandler() {
                if (handlerThread == null) {
                        handlerThread = new HandlerThread("data");
                        handlerThread.start();
                } else {
                        Log.e(TAG, "onCreate: not null1");
                }


                if (handler == null) {
                        handler = new Handler(handlerThread.getLooper()) {
                                @Override
                                public void handleMessage(Message msg) {
                                        Log.e(TAG, "handleMessage: " + data);
                                }
                        };
                } else {
                        Log.e(TAG, "onCreate: not null13222222");
                }
                handler.sendEmptyMessage(1);
        }

}
