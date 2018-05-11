package com.unisrobot.firstmodule;

import android.app.Activity;
import android.content.ContentProvider;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Administrator on 2018/5/9.
 */

public class FileActivity extends Activity {
        private static final String TAG = "FileActivity";
        private Button button;

        private HandlerThread handlerThread;
        private Handler handler;
        private boolean state = false;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_activity_crash);
                Log.e(TAG, "onCreate: " + android.os.Process.myPid());
                button = findViewById(R.id.crash_btn);
                button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Log.e(TAG, "onClick: --------------" );
                                synchronized (FileActivity.this){
                                        if (!state) {
                                                handlerThread.interrupt();
                                        } else {
                                                handler.sendEmptyMessage(1);
                                        }
                                        state = !state;
                                }
                        }
                });
                handlerThread = new HandlerThread("data");
                handlerThread.start();
                final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                                Log.e(TAG, "run: start11111   " + Thread.currentThread().getName());
                                //                                SystemClock.sleep(10000); // 这种写法是无法打断的。
                                synchronized (FileActivity.this){
                                        try {
                                                Thread.sleep(10000);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                                Log.e(TAG, "run: e===" + e);
                                        }
                                }

                                Log.e(TAG, "run: end111111");
                        }
                };
                handler = new Handler(handlerThread.getLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                                super.handleMessage(msg);
                                runnable.run();
                        }
                };
                handler.sendEmptyMessage(1);

        }
}
