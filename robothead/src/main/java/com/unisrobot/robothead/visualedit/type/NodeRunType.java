package com.unisrobot.robothead.visualedit.type;

/**
 * Created by Administrator on 2018/6/27.
 * <p>
 * 节点的运行类型
 */

public enum NodeRunType {
        /**
         * 时间型，即运行 time 后 执行下一个节点
         */
        TIME,

        /**
         * 次数型，即运行 count 次数后 执行下一个节点
         */
        COUNT,

        /**
         * 等待型，即等待某个条件(触摸几次，等几秒，收到云端反馈(需要超时处理)，收到音频播放结束  )后，才执行下一个节点
         */
        WAIT_NEXT,

        /**
         * 一直重复执行，父节点是没有附加条件，由子节点决定什么时候退出当前节点，执行下一个节点
         */
        REPEAT_CYCLE,

        /**
         * 重复执行,直到父节点附加条件的满足，然后退出当前节点，执行下一个节点。（随机监听条件是否满足）
         * 注：只要条件满足，不满当里面的子节点执行了几个，都要退出
         */
        REPEAT_UNIT,

        /**
         * 条件型，当满足了条件，执行XX，不满足，执行YY
         */
        CONDITION,
}
