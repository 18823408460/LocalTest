package com.unisrobot.firstmodule.cameraview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.unisrobot.firstmodule.R;

/**
 * Created by Administrator on 2018/5/8.
 */

public class ChildActivity extends ParentActivity {

        private static final String TAG = "ChildActivity";

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_activity_child);
                Log.e(TAG, "onCreate: " + this);
                data = "onCreate";
                Log.e(TAG, "onCreate: "+android.os.Process.myPid() );
                datatest = new Data();
                datatest.setStr("oncreate");
                //testHandler();

                if (runnable == null) {
                        runnable = new MyRunnable(this) {
                                @Override
                                public void run() {
                                        Log.e(TAG, " run     " + data + "   activity="+ activity);
                                        Log.e(TAG, " run     " + datatest.getStr() + "   datatest="+datatest);
                                }
                        };
                        new Thread(runnable).start();
                } else {
                        new Thread(runnable).start();
                        Log.e(TAG, "onCreate: runnable not null  ");
                }
        }

        public void setValue(View view){
                datatest.setStr("setValue");
                new Thread(runnable).start();
        }


        @Override
        protected void onDestroy() {
                super.onDestroy();
                Log.e(TAG, "onDestroy: " + this);
                data = "onDestroy";
                datatest.setStr("onDestroy");
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
