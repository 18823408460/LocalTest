package com.unisrobot.robothead;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.unisrobot.robothead.bean.TouChuanMsgBean;
import com.unisrobot.robothead.constant.TouChuanMsgConstant;
import com.unisrobot.robothead.core.UARTManager;
import com.unisrobot.robothead.entitybean.Packet;
import com.unisrobot.robothead.entitybean.ProtocolPacket;
import com.unisrobot.robothead.entitybean.send.ActionSendPacket;
import com.unisrobot.robothead.entitybean.send.TouChuanSendPacket;
import com.unisrobot.robothead.util.PacketUtil;

/**
 * Created by Administrator on 2018/5/3.
 */

public class RobotMainActivity extends Activity {
        private static final String TAG = "RobotMainActivity";

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_robot);


        }

        private boolean state = false;

        public void sendData(View view) {
                if (!state) {
                        TouChuanMsgBean hello = new TouChuanMsgBean(TouChuanMsgConstant.HandShakeRequest, "hello");
                        Packet touChuanSendPacket = new TouChuanSendPacket(hello);
                        ProtocolPacket protocolPacket = ProtocolPacket.buildPacket(touChuanSendPacket);
                        byte[] bytes = protocolPacket.encodeBytes();

                        UARTManager.getManager().onReceive(bytes);

                        Log.e(TAG, "onCreate: " + PacketUtil.bytesToHex(bytes));
                } else {

                        ActionSendPacket actionSendPacket = new ActionSendPacket(1111, false, ActionSendPacket.ActionLib.LibOne);
                        ProtocolPacket protocolPacket = ProtocolPacket.buildPacket(actionSendPacket);
                        byte[] bytes = protocolPacket.encodeBytes();
                        UARTManager.getManager().onReceive(bytes);
                        Log.e(TAG, "onCreate11: " + PacketUtil.bytesToHex(bytes));
                }
                state = !state;
        }
}
