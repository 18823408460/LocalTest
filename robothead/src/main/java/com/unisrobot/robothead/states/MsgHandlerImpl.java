package com.unisrobot.robothead.states;

import android.util.Log;

import com.unisrobot.robothead.states.beans.UartMsg;
import com.unisrobot.robothead.states.enums.EPlayerState;
import com.unisrobot.robothead.states.enums.ESensor;
import com.unisrobot.robothead.states.enums.EVoiceOpr;
import com.unisrobot.robothead.states.enums.EVoiceType;
import com.unisrobot.robothead.states.mode.State;
import com.unisrobot.robothead.states.msg.PowerMsg;
import com.unisrobot.robothead.uart.bean.PowerBean;

/**
 * Created by Administrator on 2018/5/15.
 */

public class MsgHandlerImpl implements IMsgHandler {
        private static final String TAG = "MsgHandlerImpl";
        private static volatile MsgHandlerImpl msgHandler;
        private State currentState;
        // 语音识别相关在这里处理
        // 播放tts，MP3， 动作，眼睛动画,发送串口消息，全部用单列, 播放的回调全部回到这里
        public static MsgHandlerImpl getMsgHandler() {
                if (msgHandler == null) {
                        synchronized (MsgHandlerImpl.class) {
                                if (msgHandler == null) {
                                        msgHandler = new MsgHandlerImpl();
                                }
                        }
                }
                return msgHandler;
        }

        private MsgHandlerImpl() {
        }


        @Override
        public void setState(State state) {
                Log.e(TAG, "setState: " + state.getClass().getSimpleName());
                this.currentState = state;
        }

        @Override
        public void playEnd(EPlayerState ePlayerState, String path, EVoiceOpr eVoiceOpr) {
                Log.e(TAG, "playEnd:   threadName=" + Thread.currentThread().getName());
        }

        public void handlerVoiceMsg() {
                currentState.handlerVoiceMsg(EVoiceType.ReCognizer, "你试谁");
                currentState.handlerVoiceMsg(EVoiceType.ReCognizer, "你试顶顶顶顶谁");
                currentState.handlerSensorMsg(ESensor.HeadBack);
                currentState.handlerUartMsg(new PowerMsg(1, new PowerBean(1, false)));
                currentState.handlerVoiceMsg(EVoiceType.ReCognizer, "导购");
                currentState.handlerVoiceMsg(EVoiceType.ReCognizer, "今天你好吗");
                currentState.handlerSensorMsg(ESensor.HeadTop);
                currentState.handlerUartMsg(new PowerMsg(2, new PowerBean(1, false)));
                currentState.handlerVoiceMsg(EVoiceType.ReCognizer, "退出去");
                currentState.handlerVoiceMsg(EVoiceType.ReCognizer, "今天天气怎么样");
                currentState.handlerUartMsg(new UartMsg(1, 1));
                currentState.handlerVoiceMsg(EVoiceType.ReCognizer, "今天天气怎么样");
        }
}
