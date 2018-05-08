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



        protected static Runnable runnable ;





        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

        }


}
