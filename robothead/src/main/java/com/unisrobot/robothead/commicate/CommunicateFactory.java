package com.unisrobot.robothead.commicate;

/**
 * Created by WEI on 2018/5/11.
 */

public class CommunicateFactory {
    private static volatile CommunicateFactory instance;

    public static CommunicateFactory getInstance() {
        if (instance == null) {
            synchronized (CommunicateFactory.class) {
                if (instance == null) {
                    instance = new CommunicateFactory();
                }
            }
        }
        return instance;
    }

    private CommunicateFactory() {

    }
}
