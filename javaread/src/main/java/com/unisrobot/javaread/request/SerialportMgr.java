package com.unisrobot.javaread.request;

import android.os.SystemClock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/5/3.
 */

public class SerialportMgr {
        private static volatile SerialportMgr serialportMgr;
        private ExecutorService executorService;

        public static SerialportMgr getSerialportMgr() {
                if (serialportMgr == null) {
                        synchronized (SerialportMgr.class) {
                                if (serialportMgr == null) {
                                        serialportMgr = new SerialportMgr();
                                }
                        }
                }
                return serialportMgr;
        }

        private SerialportMgr() {
                executorService = Executors.newFixedThreadPool(2);
        }

        private IResultLisenter iResultLisenter;
        private int count;

        public void queryData(IResultLisenter resultLisenter) {
                this.iResultLisenter = resultLisenter;
                executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                                try {
                                        Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }

                                if (iResultLisenter != null) {
                                        iResultLisenter.onResponse("hello ====  " + (count++));
                                }

//                                iResultLisenter = null;
                        }
                });

        }
}
