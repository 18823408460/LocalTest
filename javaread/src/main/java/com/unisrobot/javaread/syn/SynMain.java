package com.unisrobot.javaread.syn;

/**
 * Created by Administrator on 2018/7/9.
 */

/**
 * 静态方法如果没有使用静态变量，则没有线程安全问题。
 * 为什么呢？因为静态方法内声明的变量，每个线程调用时，都会新创建一份，而不会共用一个存储单元。比如这里的tmp,每个线程都会创建自己的一份，因此不会有线程安全问题。
 * 注意:静态变量，由于是在类加载时占用一个存储区，每个线程都是共用这个存储区的，所以如果在静态方法里使用了静态变量，这就会有线程安全问题！
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
                                System.out.println("data=" + EncodeUtils.bytesToHexString(send) + "  name=" + Thread.currentThread().getName());
                                try {
                                        Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }
}
