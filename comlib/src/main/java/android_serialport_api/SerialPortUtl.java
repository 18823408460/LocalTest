package android_serialport_api;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android_serialport_api.intefaces.ISerialPortCallBack;
import android_serialport_api.intefaces.ISerialPortSend;

/**
 * Created by Administrator on 2018/4/19.
 */

public class SerialPortUtl implements ISerialPortSend {
        private static final String TAG = "SerialPortUtl";
        private SerialPortUtl.ReadThread mReadThread;
        private ISerialPortCallBack mSerialCallBack;
        private SerialPort mSerialPort = null;
        List<BufferData> listSend = new ArrayList();

        class BufferData {
                byte[] bytes;
                int nLen;
        }

        public SerialPortUtl(ISerialPortCallBack callback) {
                mSerialCallBack = callback;
        }

        public boolean openSerialPort(String strPort, int baudrate, int nBaudrate) throws SecurityException, IOException {
                if (mSerialPort != null) {
                        closeSerialPort();
                }
                mSerialPort = new SerialPort(new File(strPort), nBaudrate, 0);
                mReadThread = new SerialPortUtl.ReadThread(this);
                mReadThread.start();
                return true;
        }

        public void closeSerialPort() {
                if (mSerialPort != null) {
                        mSerialPort.close();
                        mSerialPort = null;
                }
                if (mReadThread != null) {
                        mReadThread.interrupt();
                }
        }

        public InputStream getInputStream() {
                return mSerialPort.getInputStream();
        }

        public OutputStream getOutputStream() {
                return mSerialPort.getOutputStream();
        }

        class ReadThread extends Thread {
                InputStream mInputStream;

                public ReadThread(SerialPortUtl p1) {
                        mInputStream = mSerialPort.getInputStream();
                }

                public void run() {
                        byte[] buffer = new byte[0x400];

                        while (!isInterrupted()) {
                                if (mInputStream == null) {
                                        return;
                                }
                                try {
                                        int nRead = mInputStream.read(buffer);
                                        if (mSerialCallBack != null) {
                                                mSerialCallBack.onDataReceived(buffer, nRead);
                                        }
                                } catch (IOException e) {
                                        e.printStackTrace();
                                        Log.v("SerialPortUtl", "Serial port has close");
                                }
                        }
                }
        }

        class WriteThread extends Thread {
                OutputStream mOutputStream;

                private WriteThread(SerialPortUtl p1) {
                        mOutputStream = mSerialPort.getOutputStream();
                }

                public void run() {
                        while (!isInterrupted()) {
                                if (mOutputStream == null) {
                                        break;
                                }
                                try {
                                        synchronized (this) {
                                                SerialPortUtl.BufferData datas = null;
                                                for (int i = 0x0; i < listSend.size(); i = i + 0x1) {
                                                        datas = listSend.get(i);
                                                        if (datas != null) {
                                                                mOutputStream.write(datas.bytes, 0, datas.nLen);
                                                                break;
                                                        }
                                                }
                                                if (datas != null) {
                                                        listSend.remove(datas);
                                                        datas = null;
                                                        continue;
                                                }
                                        }
                                } catch (Exception e) {
                                        Log.v("SerialPortUtl", e.toString());
                                        try {
                                                sleep(100);
                                                continue;
                                        } catch (InterruptedException e1) {
                                                Log.v("SerialPortUtl", "interrupt to send data");
                                        }
                                }
                        }
                }
        }

        public void WriteToSerial(byte[] data, int nLen) {
                if (mSerialPort == null) {
                        return;
                }
                OutputStream mOutputStream = mSerialPort.getOutputStream();
                if (mOutputStream != null) {
                        try {
                                mOutputStream.write(data, 0, nLen);
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
        }

        public boolean sendData(byte[] data, int nLen) {
                if (data == null) {
                        return false;
                }
                synchronized (this) {
                        WriteToSerial(data, nLen);
                }
                return true;
        }
}