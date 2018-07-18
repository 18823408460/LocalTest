package com.unisrobot.javaread.syn;

/**
 * Created by Administrator on 2018/7/18.
 */

public enum State {
        Spring(1),
        Good(2),
        Bad(3);

        private int value;

        State(int i) {
                this.value = i;
        }
}
