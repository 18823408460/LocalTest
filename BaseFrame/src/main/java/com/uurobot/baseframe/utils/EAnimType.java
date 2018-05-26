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
        MoistWet(5),
        LowShao(6),
        LowWen(7),
        HighShao(8),
        OpenDoor(9),
        OpenLamp(10),
        OpenAirCondition(11),
        OpenTv(12);

        public int value;

        // 只能 是 private
        EAnimType(int value) {
                this.value = value;
        }
}
