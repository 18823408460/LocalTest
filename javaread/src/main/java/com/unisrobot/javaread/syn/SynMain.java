package com.unisrobot.javaread.syn;

/**
 * Created by Administrator on 2018/7/9.
 */

public class SynMain {
        public static void main(String[] args) {
                ReceiverRunnable receiverRunnable = new ReceiverRunnable();
                ReceiverRunnable receiverRunnable1 = new ReceiverRunnable();
                ReceiverRunnable receiverRunnable2 = new ReceiverRunnable();

                Thread thread1 = new Thread(receiverRunnable, "thread1");
                Thread thread2 = new Thread(receiverRunnable, "thread2");
                Thread thread3 = new Thread(receiverRunnable, "thread3");
                thread1.start();
                thread2.start();
                thread3.start();
        }

        public static class ReceiverRunnable implements Runnable {
                private int count = 0;

                @Override
                public void run() {
                        while (count < 255) {
                                byte[] send = EncodeUtils.send(count++);
                                System.out.println("data="+ EncodeUtils.bytesToHexString(send) + "  name="+Thread.currentThread().getName());
                                try {
                                        Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }
}
