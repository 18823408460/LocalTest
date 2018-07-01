package com.unisrobot.robothead.uart.core;

import android.support.annotation.NonNull;
import android.util.Log;

import com.unisrobot.robothead.uart.constant.Error;
import com.unisrobot.robothead.uart.constant.TouChuanMsgConstant;
import com.unisrobot.robothead.uart.entitybean.McuResponsePacket;
import com.unisrobot.robothead.uart.entitybean.PacketEntity;
import com.unisrobot.robothead.uart.entitybean.ProtocolPacket;
import com.unisrobot.robothead.uart.entitybean.response.TouChuanResponsePacket;
import com.unisrobot.robothead.uart.entitybean.send.TouChuanAckPacket;
import com.unisrobot.robothead.uart.interfaces.ISendListener;
import com.unisrobot.robothead.uart.util.PacketUtil;

import java.io.IOException;
import java.io.OutputStream;
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
        private static final long SEND_TIME_OUT = 1000; //单次发送的超时时间(测试时时间可以调长点)
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
                                long minSleepTime = SEND_TIME_OUT;
                                long currentTime = System.currentTimeMillis();
                                Iterator<PacketEntity> iterator = mSendPool.iterator();
                                while (iterator.hasNext()) {
                                        PacketEntity next = iterator.next();
                                        if (next.isHasAcked()) { //如果应答位被置位，说明消息发送成功
                                                Log.e(TAG, "processSendPool: has Ack ,remove");
                                                next.onSuccess();
                                                iterator.remove();
                                                continue;
                                        }

                                        if (next.getRetryCount() >= SEND_RETRY_COUNT) {
                                                Log.e(TAG, "processSendPool: no ack");
                                                next.onFailed(Error.NO_ACK);
                                                iterator.remove();
                                                continue;
                                        }

                                        if (currentTime - next.getLastSendTime() >= SEND_TIME_OUT) {
                                                writePacket(next.getPacket());
                                                if (next.isNeedAck()) {// 需要响应
                                                        Log.e(TAG, "processSendPool: need ack,");
                                                        next.increaseRetryCoutn();
                                                        next.setLastSendTime(currentTime);
                                                } else { // 不需要响应
                                                        Log.e(TAG, "processSendPool: not nedd ack");
                                                        iterator.remove();
                                                }
                                        } else { //没有到 发送时间
                                                long sendInterval = SEND_TIME_OUT - (currentTime - next.getLastSendTime());
                                                if (sendInterval < minSleepTime) {
                                                        minSleepTime = sendInterval;
                                                }
                                        }
                                }
                                try {
                                        mSendPool.wait(minSleepTime);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                        Log.e(TAG, "processSendPool: e=" + e);
                                }
                        }
                }
        }

        /**
         * 将数据发送 出去；
         *
         * @param packet
         */
        private void writePacket(ProtocolPacket packet) {
                byte[] bytes = packet.encodeBytes();
                if (outputStream != null) {
                        try {
                                outputStream.write(bytes);
                                Log.d(TAG, "writePacket ok: " + PacketUtil.bytesToHex(bytes));
                                return;
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }
                Log.e(TAG, "writePacket error: " + PacketUtil.bytesToHex(bytes));
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
                                byte[] bytes = protocolPacket.getDataPacket().encodeBytes();
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
                McuResponsePacket packet = (McuResponsePacket) protocolPacket.getDataPacket();
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

                                if (packetType == TouChuanMsgConstant.Request && mSendPool.contains(seqID)) {
                                        Log.e(TAG, "recv same data, send ack drop it !!!");
                                        TouChuanAckPacket touChuanAckPacket = new TouChuanAckPacket(seqID);
                                        ProtocolPacket protocolPacket1 = PacketUtil.buildPacket(touChuanAckPacket);
                                        sendResponse(protocolPacket1);
                                        return;
                                }

                                if (packetType == TouChuanMsgConstant.Request) {
                                        Log.e(TAG, "onReceive: contentPos===" + touChuanResponsePacket.getContent());
                                        addReceivePool(seqID);
                                        handlerTouChuanResquest(touChuanResponsePacket);
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
         * 将请求消息的Id，存放起来，
         *
         * @param seqId
         */
        private void addReceivePool(int seqId) {
                if (mReceivePool.size() > 300) {
                        mReceivePool.remove();
                }
                mReceivePool.add(seqId);
        }

        /**
         * 处理透传请求消息
         *
         * @param content
         */
        private void handlerTouChuanResquest(TouChuanResponsePacket content) {
                sendResponse(PacketUtil.buildTouChuanAckPacket(content.seqID));
                touChuanMgr.handlerTouChuanData(content.getContent());
        }

        /**
         * 处理透传响应消息(从发送池中取出该消息，然后将其设置收到响应)
         *
         * @param seqID
         */
        private void handlerTouChuanAck(final int seqID) {
                executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                                synchronized (mSendPool) {
                                        Iterator<PacketEntity> iterator = mSendPool.iterator();
                                        while (iterator.hasNext()) {
                                                PacketEntity next = iterator.next();
                                                byte[] bytes = next.getPacket().encodeBytes();
                                                int seqID1 = next.getPacket().getDataPacket().seqID;
                                                Log.e(TAG, "handlerTouChuanAck run: seqId =" + seqID1 + "   seqAckID=" + seqID);
                                                if (seqID == seqID1) {
                                                        next.setAcked();
                                                        break;
                                                }
                                        }
                                }
                        }
                });
        }

        /**
         * 处理Mcu过来的消息
         *
         * @param cmdType
         * @param packet
         */
        private void handlerMcuResponse(int cmdType, McuResponsePacket packet) {
                Log.e(TAG, "handlerMcuResponse: ...................");
        }

        /**
         * 释放资源
         */
        public void destroy() {
                touChuanMgr.destroy();
                executorService.shutdownNow();
                mIsRunning = false;
        }

        private OutputStream outputStream;

        public void setOutStream(OutputStream outputStream) {
                this.outputStream = outputStream;
        }
}
