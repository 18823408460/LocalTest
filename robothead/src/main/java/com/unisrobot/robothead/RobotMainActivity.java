package com.unisrobot.robothead;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.unisrobot.robothead.states.MsgHandlerImpl;
import com.unisrobot.robothead.states.mode.BaseHandler;
import com.unisrobot.robothead.states.mode.NormalChatState;
import com.unisrobot.robothead.uart.bean.TouChuanMsgBean;
import com.unisrobot.robothead.uart.constant.TouChuanMsgConstant;
import com.unisrobot.robothead.uart.core.UARTManager;
import com.unisrobot.robothead.uart.entitybean.Packet;
import com.unisrobot.robothead.uart.entitybean.ProtocolPacket;
import com.unisrobot.robothead.uart.entitybean.send.ActionSendPacket;
import com.unisrobot.robothead.uart.entitybean.send.TouChuanRequestPacket;
import com.unisrobot.robothead.uart.interfaces.ISendListener;
import com.unisrobot.robothead.uart.util.PacketUtil;

/**
 * Created by Administrator on 2018/5/3.
 */

public class RobotMainActivity extends Activity {
        private static final String TAG = "RobotMainActivity";
        private Handler handler = new Handler();
        MsgHandlerImpl msgHandler;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_robot);

                msgHandler = MsgHandlerImpl.getMsgHandler();
                BaseHandler baseHandler = new BaseHandler(msgHandler);
                NormalChatState normalChatState = new NormalChatState(msgHandler, baseHandler);
                msgHandler.setState(normalChatState);

                msgHandler.handlerVoiceMsg();
        }

        private void testInt() {
                byte a = (byte) 0x7F;
                byte b = (byte) 0x7F;
                final int len = ((a << 8) | b) + 3;
                Log.e(TAG, "onCreate a: " + a);
                Log.e(TAG, "onCreate b: " + b);
                Log.e(TAG, "onCreate len: " + len);

                int aI = 0x7F;
                int bI = 0x7F;
                final int lenI = ((aI * 256) + bI) + 3;
                Log.e(TAG, "onCreate aI: " + aI);
                Log.e(TAG, "onCreate bI: " + bI);
                Log.e(TAG, "onCreate len: " + lenI);

                byte a11 = (byte) 0x99;
                if (a11 == 0x99) { // 这里会转换成 int 比较
                        Log.e(TAG, "onCreate: ---------ok");
                } else {
                        Log.e(TAG, "onCreate: ----------error");
                }
        }

        private int state = 0;

        public void sendData(View view) {
                //                UART();
                msgHandler.handlerVoiceMsg();
        }

        private void UART() {
                if (state % 2 == 0) {
                        TouChuanMsgBean hello = new TouChuanMsgBean(TouChuanMsgConstant.HandShakeRequest, "hello");
                        Packet touChuanSendPacket = new TouChuanRequestPacket(hello);
                        ProtocolPacket protocolPacket = PacketUtil.buildPacket(touChuanSendPacket);
                        UARTManager.getManager().sendRequest(protocolPacket, new ISendListener() {
                                @Override
                                public void onSuccess() {
                                        Log.e(TAG, "onSuccess: ");
                                }

                                @Override
                                public void onFailed(int error) {
                                        Log.e(TAG, "onFailed: " + error);
                                }
                        });
                        int seqID = touChuanSendPacket.seqID;
                        Log.e(TAG, "sendData: seqID===================" + seqID);
                        ProtocolPacket protocolPacket1 = PacketUtil.buildTouChuanAckPacket(seqID);
                        final byte[] bytes = protocolPacket1.encodeBytes();
                        handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                        UARTManager.getManager().onReceive(bytes);
                                }
                        }, 1000);

                } else if (state % 2 == 1) {
                        ActionSendPacket actionSendPacket = new ActionSendPacket(1111, true, ActionSendPacket.ActionLib.LibOne);
                        ProtocolPacket protocolPacket = PacketUtil.buildPacket(actionSendPacket);
                        byte[] bytes = protocolPacket.encodeBytes();
                        Log.e(TAG, "onCreate11: " + PacketUtil.bytesToHex(bytes));
                        UARTManager.getManager().onReceive(bytes);

                }
                state++;
        }
}
