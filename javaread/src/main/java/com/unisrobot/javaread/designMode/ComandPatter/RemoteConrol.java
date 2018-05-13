package com.unisrobot.javaread.designMode.ComandPatter;

/**
 * Created by WEI on 2018/5/12.
 */

public class RemoteConrol {
    private Command[] commandsOff;
    private Command[] commandsOn;

    public void setCommand(int slot, Command on, Command off) {
        commandsOff[slot] = off;
        commandsOn[slot] = on;
    }

    public void onButtonPress(int slot) {
        commandsOn[slot].excute();
    }

    public void offButtonPress(int slot) {
        commandsOff[slot].excute();
    }
}
