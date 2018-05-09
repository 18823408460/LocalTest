package com.unisrobot.firstmodule.cameraview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/5/8.
 */

public class ParentActivity extends Activity {
        protected static HandlerThread handlerThread;
        protected static Handler handler;

        protected volatile String data = "init";
        protected  Data datatest ;
        protected static MyRunnable runnable ;





        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

        }

        public class MyRunnable implements Runnable{
                public Activity activity ;
                public MyRunnable(Activity activity) {
                        this.activity =activity;
                }

                @Override
                public void run() {

                }
        }


}
