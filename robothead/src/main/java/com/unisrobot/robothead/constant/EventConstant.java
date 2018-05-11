package com.unisrobot.robothead.constant;

/**
 * Created by Administrator on 2018/5/11.
 */

/**
 * 事件常量类
 */
public class EventConstant {
        /**
         * 初始化成功事件
         */
        public static final int EVENT_INIT_SUCCESS = 0;
        /**
         * 初始化失败事件
         */
        public static final int EVENT_INIT_FAILED = 1;
        /**
         * 串口消息事件--- 具体的消息类型见 McuMsgTypeConstant
         */
        public static final int EVENT_MSG = 2;
}
