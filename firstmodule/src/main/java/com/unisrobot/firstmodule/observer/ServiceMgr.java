package com.unisrobot.firstmodule.observer;

import java.util.Observable;

/**
 * Created by Administrator on 2018/5/14.
 */

public class ServiceMgr extends Observable {

        public ServiceMgr() {
                new Thread(new Runnable() {
                        @Override
                        public void run() {
                                while (true) {
                                        update();
                                        try {
                                                Thread.sleep(2000);
                                        } catch (InterruptedException e) {
                                                e.printStackTrace();
                                        }
                                }
                        }
                },"sendData").start();
        }

        int count;

        private void update() {
                setChanged();
                notifyObservers(count++);
        }
}
