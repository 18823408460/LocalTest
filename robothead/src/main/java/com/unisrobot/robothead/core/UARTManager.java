package com.unisrobot.robothead.core;

import android.support.annotation.NonNull;
import android.util.Log;

import com.unisrobot.robothead.bean.TouChuanMsgBean;
import com.unisrobot.robothead.constant.TouChuanMsgConstant;
import com.unisrobot.robothead.entitybean.McuResponsePacket;
import com.unisrobot.robothead.entitybean.Packet;
import com.unisrobot.robothead.entitybean.PacketEntity;
import com.unisrobot.robothead.entitybean.ProtocolPacket;
import com.unisrobot.robothead.entitybean.response.TouChuanResponsePacket;
import com.unisrobot.robothead.interfaces.ISendListener;
import com.unisrobot.robothead.util.PacketUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Administrator on 2018/5/11.
 */

public class UARTManager {
    private static final String TAG = "UARTManager";
    private static final int SEND_RETRY_COUNT = 3; //超时后重复发送的次数
    private static final long SEND_TIME_OUT = 300; //单次发送的超时时间
    private List<PacketEntity> mSendPool; //发送消息池
    private Queue<Integer> mReceivePool; //接收消息池（存储消息的ID）
    private ExecutorService executorService;
    private Thread mSendThread;
    private volatile static UARTManager sManager;
    private boolean mIsRunning = true;
    private TouChuanMgr touChuanMgr;

    public static UARTManager getManager() {
        if (null == sManager) {
            synchronized (UARTManager.class) {
                if (sManager == null) {
                    sManager = new UARTManager();
                }
            }
        }
        return sManager;
    }

    private UARTManager() {
        touChuanMgr = TouChuanMgr.getTouChuanMgr();
        mSendPool = new ArrayList<>();
        mReceivePool = new LinkedList<>();

        mSendThread = new Thread(new Runnable() {
            @Override
            public void run() {
                processSendPool();
            }
        }, "UART_Manager_Write_Thread");
        mSendThread.setDaemon(true);
        mSendThread.start();

        //这里只能用单个线程，否则消息发送回乱序
        executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(@NonNull Runnable r) {
                Thread ret = new Thread(r, "UART_Manager_Main_Thread");
                ret.setDaemon(true);
                return ret;
            }
        });

    }

    public void init() {
        //连接打开串口
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: start init uart........" + Thread.currentThread().getName());
            }
        });
    }

    /**
     * 处理发送池 中的消息。。。。。
     */
    private void processSendPool() {
        while (mIsRunning) {
            synchronized (mSendPool) {
                long currentTime = System.currentTimeMillis();
                Iterator<PacketEntity> iterator = mSendPool.iterator();
                while (iterator.hasNext()) {
                    PacketEntity next = iterator.next();

                }
            }
        }
    }

    /**
     * 发送请求消息给mcu
     *
     * @param protocolPacket
     * @param iSendListener
     */
    public void sendRequest(final ProtocolPacket protocolPacket, final ISendListener iSendListener) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                PacketEntity packetEntity = new PacketEntity(protocolPacket, iSendListener);
                addToSendPool(packetEntity);
            }
        });
    }

    /**
     * 发送响应消息
     *
     * @param protocolPacket
     */
    public void sendResponse(final ProtocolPacket protocolPacket) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                PacketEntity entity = new PacketEntity(protocolPacket);
                addToSendPool(entity);
            }
        });
    }

    private void addToSendPool(PacketEntity packetWrapper) {
        synchronized (mSendPool) {
            mSendPool.add(packetWrapper);
            mSendPool.notify();
        }
    }

    private void writeBytes(Packet packet) {
        int retry_cont = 0;
        do {
            byte[] bytes = packet.encodeBytes();
            Log.e(TAG, "writeBytes: " + bytes);
            retry_cont++;
        } while (retry_cont < SEND_RETRY_COUNT);
    }

    /**
     * 处理串口发送过来的消息--------这里必须在子线程执行
     *
     * @param data
     */
    public void onReceive(byte[] data) {
        if (data == null) {
            return;
        }
        if (PacketUtil.isValid(data)) {
            Log.e(TAG, "onReceive: data is error");
            return;
        }
        ProtocolPacket protocolPacket = new ProtocolPacket();
        protocolPacket.decodeBytes(data);
        McuResponsePacket packet = (McuResponsePacket) protocolPacket.getPacket();
        if (packet != null) {
            int cmdType = protocolPacket.getCmdType();
            Log.e(TAG, "onReceive: " + cmdType);
            if (PacketUtil.isMcuReponseMsg(cmdType)) { //如果是mcu 发送的消息，这个消息不会发送多次
                handlerMcuResponse(cmdType, packet);

            } else if (PacketUtil.isTouChuanMsg(cmdType)) {
                //如果是收到的是透传消息, 可能是透传响应消息()
                TouChuanResponsePacket touChuanResponsePacket = (TouChuanResponsePacket) packet;
                int packetType = touChuanResponsePacket.packetType;
                int seqID = touChuanResponsePacket.seqID; //请求 或是 响应的 ID
                if (packetType == TouChuanMsgConstant.Request) {
                    Log.e(TAG, "onReceive: content===" + touChuanResponsePacket.getContent());
                    handlerTouChuanResquest(touChuanResponsePacket.getContent());
                } else {
                    Log.e(TAG, "onReceive: Response");
                    handlerTouChuanAck(touChuanResponsePacket.seqID);
                }
            }
        } else {
            Log.e(TAG, "onReceive: packet is null");
        }
    }

    /**
     * 处理透传请求消息
     *
     * @param content
     */
    private void handlerTouChuanResquest(TouChuanMsgBean content) {
        touChuanMgr.handlerTouChuanData(content);
    }

    /**
     * 处理透传响应消息
     *
     * @param seqID
     */
    private void handlerTouChuanAck(int seqID) {
    }

    /**
     * 处理Mcu过来的消息
     *
     * @param cmdType
     * @param packet
     */
    private void handlerMcuResponse(int cmdType, McuResponsePacket packet) {

    }

    /**
     * 释放资源
     */
    public void destroy() {
        touChuanMgr.destroy();
        executorService.shutdownNow();
        mIsRunning = false;
    }
}
