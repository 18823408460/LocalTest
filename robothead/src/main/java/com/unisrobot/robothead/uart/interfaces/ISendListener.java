package com.unisrobot.robothead.uart.interfaces;

/**
 * Created by Administrator on 2018/5/11.
 */

/**
 * 发送给mcu 消息的 接口
 */
public interface ISendListener {
        void onSuccess();

        void onFailed(int error);
}
