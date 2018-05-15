package com.unisrobot.robothead.states.mode;

import android.util.Log;

import com.unisrobot.robothead.states.IMsgHandler;
import com.unisrobot.robothead.states.beans.HttpMsg;
import com.unisrobot.robothead.states.beans.UControlMsg;
import com.unisrobot.robothead.states.beans.UartMsg;
import com.unisrobot.robothead.states.enums.ESensor;
import com.unisrobot.robothead.states.enums.EVoiceType;

/**
 * Created by Administrator on 2018/5/15.
 */

public class NormalChatState extends State {
        private static final String TAG = "NormalChatState";

        public NormalChatState(IMsgHandler iMsgHandler,BaseHandler baseHandler) {
                super(iMsgHandler,baseHandler);
                Log.e(TAG, "NormalChatState: 构造函数");
        }

        @Override
        public void handlerVoiceMsg(EVoiceType eVoiceType, String msg) {
                Log.e(TAG, "handlerVoiceMsg: " + TAG + "  msg==" + msg);
                if (eVoiceType == EVoiceType.ReCognizer) {
                        if (msg.contains("导购")) {
                                DaoGouState daoGouState = new DaoGouState(iMsgHandler,baseHandler);
                                iMsgHandler.setState(daoGouState);
                                daoGouState.handlerVoiceMsg(eVoiceType, msg);
                        }
                } else if (eVoiceType == EVoiceType.WakeSuccess) {

                }
        }

        @Override
        public void handlerSensorMsg(ESensor eSensor) {
                Log.e(TAG, "handlerSensorMsg:  handler sensor" + TAG);
                baseHandler.handlerSensorMsg(eSensor);
        }

        @Override
        public void handlerUartMsg(UartMsg uartMsg) {
                Log.e(TAG, "handlerUartMsg: " + TAG + "  uartMsg="+uartMsg);
        }

        @Override
        public void handlerHttpMsg(HttpMsg httpMsg) {
                Log.e(TAG, "handlerHttpMsg: " + TAG);
        }

        @Override
        public void handlerUControlMsg(UControlMsg uControlMsg) {
                Log.e(TAG, "handlerUControlMsg: " + TAG);
        }
}
