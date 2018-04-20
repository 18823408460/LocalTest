package android_serialport_api.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.File;
import java.io.IOException;

import android_serialport_api.SerialPortCallBackManager;
import android_serialport_api.SerialPortUtl;
import android_serialport_api.intefaces.ISerialPortCallBack;

/**
 * Created by Administrator on 2018/4/19.
 */

public class SerialPortService extends Service implements ISerialPortCallBack {
        private SerialPortUtl serialPortUtl;
        private SerialPortCallBackManager serialPortCallBackManager;

        @Override
        public void onCreate() {
                super.onCreate();
                serialPortCallBackManager = new SerialPortCallBackManager(this, "");
                serialPortUtl = new SerialPortUtl(this);
                try {
                        serialPortUtl.openSerialPort("/dev/ttyS3", 115200, 0);
                } catch (SecurityException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        @Override
        public void onDestroy() {
                super.onDestroy();
                serialPortUtl.closeSerialPort();
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
                return null;
        }

        @Override
        public void onDataReceived(byte[] p1, int p2) {
                serialPortCallBackManager.onDataProcess(p1,p2);
        }
}
