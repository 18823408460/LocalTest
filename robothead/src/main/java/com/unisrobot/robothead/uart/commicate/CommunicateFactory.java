package com.unisrobot.robothead.uart.commicate;

import android.util.Log;

import com.unisrobot.robothead.uart.core.UARTManager;

import java.io.File;

import android_serialport_api.SerialPort;

/**
 * Created by WEI on 2018/5/11.
 */

public class CommunicateFactory {
        private static final String TAG = CommunicateFactory.class.getSimpleName();
        private static volatile CommunicateFactory instance;
        private static boolean isUseUart = true;
        private SerialPort serialPort;
        private UARTManager uartManager;

        public static CommunicateFactory getInstance() {
                if (instance == null) {
                        synchronized (CommunicateFactory.class) {
                                if (instance == null) {
                                        instance = new CommunicateFactory();
                                }
                        }
                }
                return instance;
        }

        private CommunicateFactory() {
                uartManager = UARTManager.getManager();
                if (isUseUart) {
                        serialPort = new SerialPort(new File("/dev/ttyS3"), 115200, 0) {
                                @Override
                                public void onReceiverData(byte[] data) { //这里是子线程
                                        uartManager.onReceive(data);
                                }

                                @Override
                                public void onInit(boolean ok) {
                                        if (ok) {
                                                uartManager.setOutStream(getOutputStream());
                                        } else {
                                                Log.e(TAG, "onInit: error");
                                        }
                                }
                        };
                        serialPort.start();
                } else {

                }
        }

        public void destroy() {
                if (isUseUart) {
                        serialPort.stopThread();
                } else {

                }
                uartManager.destroy();
        }
}
