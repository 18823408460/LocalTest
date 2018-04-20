package android_serialport_api;

import android.os.IBinder;

import android_serialport_api.intefaces.ISerialPortRcvClient;
import android_serialport_api.service.SerialPortService;

/**
 * Created by Administrator on 2018/4/19.
 */

public class SerialPortBinder {
        private IBinder mBinder;
        private SerialPortService mPortService;
        private ISerialPortRcvClient mRcvListener;
        private byte mSessionID;

        public boolean equals(SerialPortBinder ob) {
                SerialPortBinder o = ob;
                if (o.getmBinder() == mBinder) {
                        return true;
                }
                return false;
        }

        public IBinder getmBinder() {
                return mBinder;
        }

        public void setmBinder(IBinder mBinder) {
                this.mBinder = mBinder;
        }

        public ISerialPortRcvClient getmRcvListener() {
                return mRcvListener;
        }

        public void setmRcvListener(ISerialPortRcvClient mRcvListener) {
                mRcvListener = mRcvListener;
        }

        public byte getmSessionID() {
                return mSessionID;
        }

        public void setmSessionID(byte mSessionID) {
                mSessionID = mSessionID;
        }

        public SerialPortService getmPortService() {
                return mPortService;
        }

        public void setmPortService(SerialPortService mPortService) {
                this.mPortService = mPortService;
        }
}
