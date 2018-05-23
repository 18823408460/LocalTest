package com.uurobot.baseframe.utils;

/**
 * Created by Administrator on 2018/5/23.
 */

public enum EAnimType {
        Dry(0),
        DryWet(1),
        HighDry(2),
        HighDryWet(3),
        Moist(4),
        MoistWet(5);

        public int value;

        EAnimType(int value) {
                this.value = value;
        }
}
