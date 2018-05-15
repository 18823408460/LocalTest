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

/**
 * 这里只做一个基本的处理，，，不做转态切换
 */
public class BaseHandler {
        private static final String TAG = "BaseHandler";
        private IMsgHandler iMsgHandler;
        //QA请求，


        public BaseHandler(IMsgHandler iMsgHandler) {
                this.iMsgHandler = iMsgHandler;
                Log.e(TAG, "BaseHandler: 构造函数。。。。。");
        }

        public void handlerVoiceMsg(EVoiceType eVoiceType, String msg) {
                Log.e(TAG, "handlerVoiceMsg: " + TAG + "    msg=" + msg);
        }

        public void handlerSensorMsg(ESensor eSensor) {
                Log.e(TAG, "handlerSensorMsg: " + TAG + "     " + eSensor);
                iMsgHandler.playEnd(EPlayerState.ERROR,null, EVoiceOpr.AccessWake);
        }

        public void handlerUartMsg(UartMsg uartMsg) {
                Log.e(TAG, "handlerUartMsg: " + TAG);
        }

        public void handlerHttpMsg(HttpMsg httpMsg) {
                Log.e(TAG, "handlerHttpMsg: " + TAG);
        }

        public void handlerUControlMsg(UControlMsg uControlMsg) {
                Log.e(TAG, "handlerUControlMsg: " + TAG);
        }
}
