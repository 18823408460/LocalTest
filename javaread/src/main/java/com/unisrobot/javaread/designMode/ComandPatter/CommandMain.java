package com.unisrobot.javaread.designMode.ComandPatter;

/**
 * Created by WEI on 2018/5/12.
 */

import com.unisrobot.javaread.designMode.ComandPatter.light.Light;
import com.unisrobot.javaread.designMode.ComandPatter.light.LightOffCom;
import com.unisrobot.javaread.designMode.ComandPatter.light.LightOnCom;

/**
 * 将 请求 封装成 对象...????
 * <p>
 * <p>
 * // 遥控器上有多个 插槽，然后每个插槽有 开关控制按钮
 */
public class CommandMain {
    public static void main(String[] args) {
        Light light = new Light(); //安装插槽

        LightOnCom lightOnCom = new LightOnCom(light);
        LightOffCom lightOffCom = new LightOffCom(light);

    }
}
