package android_serialport_api;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class SerialPortCallBackManager {
        private List<SerialPortBinder> mBinderList;
        private Intent mBroadcastIntent;
        private Context mContext;
        private static boolean hasCreateFile = false;
        private static String sFileName = null;
        private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

        public Intent getmBroadcastIntent() {
                return mBroadcastIntent;
        }

        public SerialPortCallBackManager(Context context, String broadCastaction) {
                mBinderList = new ArrayList();
                mContext = context;
                mBroadcastIntent = new Intent(broadCastaction);
        }

        public synchronized void clearList() {
                mBinderList.clear();
        }

        public void onDataProcess(byte[] datas, int nLen) {
                Bundle bundle = new Bundle();
                bundle.putByteArray("value", datas);
                mBroadcastIntent.putExtras(bundle);
                mContext.sendBroadcast(mBroadcastIntent);
        }


        private void recordInFileWhenShutdown(byte[] buffer) {
                // :( Parsing error. Please contact me.
        }

        private String number2String(byte num) {
                String data = null;
                data = "0x" + Integer.toHexString((short) num);
                return data;
        }

        public synchronized boolean addToList(SerialPortBinder binder) {
                if (isExistBinder(binder)) {
                        return false;
                }
                mBinderList.add(binder);
                return true;
        }

        public synchronized void removeFromList(IBinder ibinder) {
                if (mBinderList.size() == 0) {
                        return;
                }
                for (int i = 0x0; i < mBinderList.size(); i = i + 0x1) {
                        SerialPortBinder b = (SerialPortBinder) mBinderList.get(i);
                        if (b.getmBinder() == ibinder) {
                                mBinderList.remove(i);
                        }
                }
        }

        public synchronized boolean isSessionIDExist(byte id) {
                if (mBinderList.iterator().hasNext()) {
                        SerialPortBinder b = (SerialPortBinder) mBinderList.iterator().next();
                        if (b.getmSessionID() == id) {
                                return true;
                        }
                }
                return false;
        }


        private synchronized boolean isExistBinder(SerialPortBinder binder) {
                if (mBinderList.iterator().hasNext()) {
                        SerialPortBinder b = (SerialPortBinder) mBinderList.iterator().next();
                        if (b.equals(binder)) {
                                return true;
                        }
                }
                return false;
        }
}
