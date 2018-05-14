package com.unisrobot.firstmodule.observer;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.unisrobot.firstmodule.R;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2018/5/14.
 */

public class ObserverActivity extends Activity implements Observer {


        @Override
        public void update(Observable o, Object arg) {  // 发送和接收在同一个线程
                Integer integer = (Integer) arg;
                Log.e(TAG, "update: " + integer + "   " + Thread.currentThread().getName());
        }

        private static final String TAG = "ObserverActivity";

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.first_module_activity_observer);
                ServiceMgr serviceMgr = new ServiceMgr();
                serviceMgr.addObserver(this); // Activity消失后，要调用反注册，，不然内存泄露
        }
}
