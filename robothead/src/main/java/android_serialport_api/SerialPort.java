/*
 * Copyright 2009 Cedric Priscal
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package android_serialport_api;

import android.util.Log;

import com.unisrobot.robothead.constant.ProtocolConstant;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class SerialPort extends Thread {
        private static final String TAG = "SerialPort";
        private FileDescriptor mFd;
        private volatile boolean mStopThread = false;
        private FileInputStream mFileInputStream;
        protected FileOutputStream mFileOutputStream;
        private DataInputStream mDataInputStream;

        /**
         * 收到的数据
         */
        public abstract void onReceiverData(byte[] data);
        public abstract void onInit(boolean ok) ;

        public SerialPort(File device, int baudrate, int flags) {
                /* Check access permission */
                if (!device.canRead() || !device.canWrite()) {
                        try {
                                /* Missing read/write permission, trying to chmod the file */
                                Process su;
                                su = Runtime.getRuntime().exec("/system/bin/su");
                                String cmd = "chmod 666 " + device.getAbsolutePath() + "\n" + "exit\n";
                                su.getOutputStream().write(cmd.getBytes());
                                if ((su.waitFor() != 0) || !device.canRead() || !device.canWrite()) {
                                        Log.e(TAG, "not root,please check ");
                                }
                        } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(TAG, "not root,please check ");
                        }
                }
                mFd = open(device.getAbsolutePath(), baudrate, flags);
                if (mFd == null) {
                        Log.e(TAG, "native open returns null");
                        onInit(false);
                        return;
                }
                mFileInputStream = new FileInputStream(mFd);
                mFileOutputStream = new FileOutputStream(mFd);
                onInit(true);
        }

        @Override
        public void run() {
                if (mFileInputStream != null) {
                        mDataInputStream = new DataInputStream(mFileInputStream);
                        while (!mStopThread) {
                                try {
                                        final byte DH_Head = mDataInputStream.readByte();
                                        if (DH_Head == ProtocolConstant.Head_H) {
                                                final byte DL_Head = mDataInputStream.readByte();
                                                if (DL_Head == ProtocolConstant.Head_L) {
                                                        // byte 可能为负值（java byte 为有符号的）
                                                        final int d = mDataInputStream.readByte() & 0xff;
                                                        final int c = mDataInputStream.readByte() & 0xff;
                                                        int len = d & 256 + c;
                                                        if (len > 0) {
                                                                final byte[] data = new byte[len];
                                                                mDataInputStream.readFully(data);
                                                                onReceiverData(data);
                                                        }
                                                }
                                        }
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                } else {
                        Log.e(TAG, "mFileInputStream is null");
                        return;
                }
        }

        /**
         * 停止线程
         */
        public void stopThread() {
                mStopThread = true;

        }

        /**
         * 获取输出流，用于写入数据
         *
         * @return
         */
        public OutputStream getOutputStream() {
                return mFileOutputStream;
        }

        /*
         * 打开串口
         */
        public native static FileDescriptor open(String path, int baudrate, int flags);

        static {
               // System.loadLibrary("serial_port");
        }
}
