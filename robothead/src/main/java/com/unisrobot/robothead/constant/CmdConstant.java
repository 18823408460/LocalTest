package com.unisrobot.robothead.constant;

/**
 * Created by Administrator on 2018/5/11.
 */

/**
 * 给mcu发送消息，相关的消息常量类
 */
public class CmdConstant {

        /**
         * 发送动作控制消息
         */
        public static final int ActionCrl =  0xB1;

        /**
         * 发送透传消息
         */
        public static final int TouChuan =  0xB2;

        /**
         * 动作执行完毕
         */
        public static final int ActionFinish = 0xB1;

        /**
         * 超声波响应数据
         */
        public static final int CsbDataResponse = 0xB1;
}
