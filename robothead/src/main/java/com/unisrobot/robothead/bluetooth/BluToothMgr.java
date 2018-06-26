package com.unisrobot.robothead.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.unisrobot.robothead.thread.ThreadPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;


/**
 * Created by Administrator on 2018/6/26.
 */

public class BluToothMgr {
    private static final String TAG = BluToothMgr.class.getSimpleName();
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothServerSocket bluetoothServerSocket;
    private DataOutputStream dos;
    private static final String CONTENT_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private IBluetoothLisenter iBluetoothLisenter;
    private volatile static BluToothMgr mBluToothMgr = null;
    private volatile boolean stopReceiver = false;
    private Handler handler;

    public void setBluetoothLisenter(IBluetoothLisenter iBluetoothLisenter) {
        this.iBluetoothLisenter = iBluetoothLisenter;
    }

    private BluToothMgr() {
        HandlerThread handlerThread = new HandlerThread("ReceiverDataThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    public static BluToothMgr getInstance() {
        if (mBluToothMgr == null) {
            synchronized (BluToothMgr.class) {
                if (mBluToothMgr == null) {
                    mBluToothMgr = new BluToothMgr();
                }
            }
        }
        return mBluToothMgr;
    }

    private void initBluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            if (!bluetoothAdapter.isEnabled()) {
                boolean enable = bluetoothAdapter.enable();
                Log.e(TAG, "enable:" + enable);
            }
        }
        int scanMode = bluetoothAdapter.getScanMode();
        Log.e(TAG, "bluetoothAdapter:" + scanMode);
    }

    private void initBluetoothService() {
        ThreadPool.getInstance().runTask(new Runnable() {
            @Override
            public void run() {
                BluetoothSocket accept = null;
                try {
                    bluetoothServerSocket = bluetoothAdapter.listenUsingInsecureRfcommWithServiceRecord("sss", UUID.fromString(CONTENT_UUID));
                    Log.e(TAG, "BluetoothServerSocket:");
                    while (!stopReceiver) {
                        accept = bluetoothServerSocket.accept();
                        final String name = accept.getRemoteDevice().getName();
                        Log.e(TAG, "accept:" + name);
                        DataInputStream dis = new DataInputStream(accept.getInputStream());
                        dos = new DataOutputStream(accept.getOutputStream());
                        handleSocketMsg(dis);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        if (accept != null) {
                            accept.close();
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

    }

    private void handleSocketMsg(DataInputStream dis) {
        String readUTF = null;
        try {
            readUTF = dis.readUTF();
            Log.e(TAG, "readUTF:" + readUTF);
            if (readUTF.matches("\\w+&&#[1-9]\\.wav")) {
                String[] split = readUTF.split("&&#");
            } else {
                if (readUTF != null) {
                    if (readUTF.startsWith("{") || readUTF.startsWith("[")) {
                        handlerVpJson(readUTF);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlerVpJson(final String readUTF) {
        if (iBluetoothLisenter != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    // 将所有的消息post到 队列中处理，防止阻塞接收
                    iBluetoothLisenter.OnReceiverData(readUTF);
                }
            });
        }
    }

    public void init() {
        stopReceiver = false;
        initBluetooth();
        initBluetoothService();
    }

    public void destroy() {
        stopReceiver = true;
        handler.removeCallbacksAndMessages(null);
        this.iBluetoothLisenter = null;
    }
}
