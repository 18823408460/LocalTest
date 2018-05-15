package com.unisrobot.robothead.states.mode;

import android.util.Log;

import com.unisrobot.robothead.states.IMsgHandler;
import com.unisrobot.robothead.states.beans.HttpMsg;
import com.unisrobot.robothead.states.beans.UControlMsg;
import com.unisrobot.robothead.states.beans.UartMsg;
import com.unisrobot.robothead.states.enums.EPlayerState;
import com.unisrobot.robothead.states.enums.ESensor;
import com.unisrobot.robothead.states.enums.EVoiceOpr;
import com.unisrobot.robothead.states.enums.EVoiceType;

/**
 * Created by Administrator on 2018/5/15.
 */

public class DaoGouState extends State {
        private static final String TAG = "DaoGouState";

        public DaoGouState(IMsgHandler iMsgHandler, BaseHandler baseHandler) {
                super(iMsgHandler, baseHandler);
                Log.e(TAG, "DaoGouState: 构造函数。。。。。");
        }

        @Override
        public void handlerVoiceMsg(EVoiceType eVoiceType, String msg) {
                Log.e(TAG, "handlerVoiceMsg: " + TAG + "    msg=" + msg);
                if (eVoiceType == EVoiceType.ReCognizer) {
                        if (msg.contains("退出")) {
                                NormalChatState normalChatState = new NormalChatState(iMsgHandler, baseHandler);
                                iMsgHandler.setState(normalChatState);
                        }
                }
        }

        @Override
        public void handlerSensorMsg(ESensor eSensor) {
                Log.e(TAG, "handlerSensorMsg:  not handler sensor " + TAG );
                iMsgHandler.playEnd(EPlayerState.ERROR,null, EVoiceOpr.AccessWake);
        }

        @Override
        public void handlerUartMsg(UartMsg uartMsg) {
                Log.e(TAG, "handlerUartMsg: " + TAG + "   uartMsg="+uartMsg);
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
