package com.unisrobot.robothead.visualedit.nodebean.common;

/**
 * Created by Administrator on 2018/6/27.
 * <p>
 * <p>
 * AppendC 返回值类型有：它是跟在一个容器型node 后面
 * 》 boolean 值
 * 对于 条件单次容器型， Boolean 值 一开始就是确定的
 * 对于 条件多次容器型， boolean 值是动态变化的。这个时候应该保存什么？
 */

public class AppendCdata {
        public boolean logic = true;
        public String leftCondition; //操作符左边的条件，
        public String rightConfition;//操作符右边的条件
        public String operate;// 操作符

        @Override
        public String toString() {
                return "AppendCdata{" +
                        "logic=" + logic +
                        '}';
        }
}
