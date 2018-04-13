package com.unisrobot.localtest.util;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.unisrobot.localtest.MainApplication;

public class ToastUtil {

        private static Toast toast;

        private static ToastUtil toastUtil;

        private Handler handler;

        private Runnable runnable;

        private String text;

        private ToastUtil() {

                // 手动调用looper，不用时必须销毁
//                Looper.prepare();
//                Looper.loop();
//                Looper.myLooper().quitSafely();

                // 这样写，如果第一次在子线程调用，就会因为没有Looper 而报错
                //toast = Toast.makeText(MainApplication.getContext(),"",Toast.LENGTH_SHORT);

                if (!Thread.currentThread().getName().equals("main")) {
                        handler = new Handler(Looper.getMainLooper());
                        runnable = new Runnable() {
                                @Override
                                public void run() {
                                        if (toast == null) {
                                                toast = Toast.makeText(MainApplication.getContext(), "", Toast.LENGTH_SHORT);
                                        }
                                        toast.setText(text);
                                        toast.show();
                                }
                        };
                }
                else {
                        toast = Toast.makeText(MainApplication.getContext(), "", Toast.LENGTH_SHORT);
                }

        }

        public static ToastUtil getToastUtil() {
                if (toastUtil == null) {
                        synchronized (ToastUtil.class) {
                                if (toastUtil == null) {
                                        toastUtil = new ToastUtil();
                                }
                        }
                }
                return toastUtil;
        }

        public void show(String text) {
                if (!Thread.currentThread().getName().equals("main")) {
                        this.text = text;
                        handler.post(runnable);
                        return;
                }
                toast.setText(text);
                toast.show();
        }


}