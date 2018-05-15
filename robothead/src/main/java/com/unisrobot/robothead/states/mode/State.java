package com.unisrobot.robothead.states.mode;

import com.unisrobot.robothead.states.IMsgHandler;
import com.unisrobot.robothead.states.beans.HttpMsg;
import com.unisrobot.robothead.states.beans.UControlMsg;
import com.unisrobot.robothead.states.beans.UartMsg;
import com.unisrobot.robothead.states.enums.ESensor;
import com.unisrobot.robothead.states.enums.EVoiceType;

/**
 * Created by Administrator on 2018/5/15.
 */

public abstract class State {
        protected IMsgHandler iMsgHandler;
        protected BaseHandler baseHandler;

        public State(IMsgHandler iMsgHandler, BaseHandler baseState) {
                this.iMsgHandler = iMsgHandler;
                this.baseHandler = baseState;
        }

        // 语音转换后的asr，以及pad 触摸要处理的（触摸转asr）
        public abstract void handlerVoiceMsg(EVoiceType eVoiceType, String msg);

        public abstract void handlerSensorMsg(ESensor eSensor);

        public abstract void handlerUartMsg(UartMsg uartMsg);

        public abstract void handlerHttpMsg(HttpMsg httpMsg);

        public abstract void handlerUControlMsg(UControlMsg uControlMsg);
}
