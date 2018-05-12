package com.unisrobot.robothead.core;

/**
 * Created by WEI on 2018/5/11.
 */

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.unisrobot.robothead.bean.TouChuanMsgBean;

/**
 *
 */
public class TouChuanMgr {
        private static final String TAG = "TouChuanMgr";
        private volatile static TouChuanMgr touChuanMgr;
        private HandlerThread handlerThread;
        private Handler handler;
        private static final int Msg = 1;

        public static TouChuanMgr getTouChuanMgr() {
                if (touChuanMgr == null) {
                        synchronized (TouChuanMgr.class) {
                                if (touChuanMgr == null) {
                                        touChuanMgr = new TouChuanMgr();
                                }
                        }
                }
                return touChuanMgr;
        }

        private TouChuanMgr() {
                handlerThread = new HandlerThread("TouChuanMgrThread");
                handlerThread.start();
                handler = new Handler(handlerThread.getLooper()) {
                        @Override
                        public void handleMessage(Message msg) {
                                switch (msg.what) {
                                        case Msg: {
                                                TouChuanMsgBean touChuanMsgBean = (TouChuanMsgBean) msg.obj;
                                                int msgType = touChuanMsgBean.msgType;
                                                String msgData = touChuanMsgBean.msgData;
                                                parseMsg(msgType, msgData);
                                                break;
                                        }
                                }
                        }
                };
        }

        /**
         * 执行透传消息
         *
         * @param msgType
         * @param data
         */
        private void parseMsg(int msgType, String data) {
                Log.e(TAG, "parseMsg: msgType" + msgType + "    data=" + data);
                switch (msgType) {

                }
        }


        /**
         * 处理透传信息(所有的消息集中这里处理)--- 通过子线程来处理
         * <p>
         * // 这里 要不要 同步 ?????????
         *
         * @param touChuanMsgBean
         */
        public void handlerTouChuanData(TouChuanMsgBean touChuanMsgBean) {
                Log.e(TAG, "handlerTouChuanData: " + touChuanMsgBean.toString());
                Message message = handler.obtainMessage();
                message.obj = touChuanMsgBean;
                message.what = Msg;
                handler.sendMessage(message);
        }

        /**
         * 释放资源
         */
        public void destroy() {
                if (handlerThread != null) {
                        handlerThread.interrupt();
                }
                handler.removeCallbacksAndMessages(null);
        }
}
