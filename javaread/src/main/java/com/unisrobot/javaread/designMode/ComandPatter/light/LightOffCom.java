package com.unisrobot.javaread.designMode.ComandPatter.light;

import com.unisrobot.javaread.designMode.ComandPatter.Command;

/**
 * Created by WEI on 2018/5/12.
 */

public class LightOffCom extends Command {
    Light light;

    public LightOffCom(Light light) {
        this.light = light;
    }

    @Override
    public void excute() {
        light.off();
    }
}
