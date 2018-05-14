package com.unisrobot.robothead.uart.constant;

/**
 * Created by Administrator on 2018/5/11.
 */

/**
 * mcu发送过来的 cmd 。。
 */
public class RevCmdConstant {
        /**
         * 手臂故障消息
         */
        public static final int Msg_Arm_Error =  0xE9;

        /**
         * 透传消息
         */
        public static final int Msg_TouChuan =  0x84;

        /**
         * 透传消息
         */
        public static final int Msg_ActionFinish =  0xB1;

        /**
         * 动作执行完毕
         */
        public static final int ActionFinish = 0xB1;

        /**
         * 超声波响应数据
         */
        public static final int CsbDataResponse = 0xB1;

        /**
         * 电量信息
         */
        public static final int PowerInfo = 0x90;
}
