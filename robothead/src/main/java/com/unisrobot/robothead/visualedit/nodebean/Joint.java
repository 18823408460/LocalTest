package com.unisrobot.robothead.visualedit.nodebean;

/**
 * Created by Administrator on 2018/3/26.
 */

/**
 * 手臂关节控制
 */
public class Joint {

        /**
         * 0 - 头部上下
         * 1 - 头部左右
         * 2 - 左手肩部前后
         * 3 - 左手肩部上下
         * 4 - 左手肘部旋转
         * 5 - 左手肘部上下
         * 6 - 左手手腕
         * 7 - 右手肩部前后
         * 8 - 右手肩部上下
         * 9 - 右手肘部旋转
         * 10 - 右手肘部上下
         * 11 - 右手手腕
         */
        //关节编号（1--12）
        private int index;

        //角度
        private short rotate;

        // 时间
        private short time;

        public Joint() {
        }

        public Joint(int index, short rotate, short time) {
                this.index = index;
                this.rotate = rotate;

                this.time = time;
        }

        public int getIndex() {
                return index;
        }

        public Joint setIndex(int index) {
                this.index = index;
                return this;
        }

        public short getRotate() {
                return rotate;
        }

        public Joint setRotate(short rotate) {
                this.rotate = rotate;
                return this;
        }

        public short getTime() {
                return time;
        }

        public Joint setTime(short time) {
                this.time = time;
                return this;
        }

        @Override
        public String toString() {
                return "Joint{" +
                        "index=" + index +
                        ", rotate=" + rotate +
                        ", time=" + time +
                        '}';
        }
}
