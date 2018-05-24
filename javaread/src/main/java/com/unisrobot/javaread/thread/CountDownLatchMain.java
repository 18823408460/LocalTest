package com.unisrobot.javaread.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018/5/24.
 */

public class CountDownLatchMain {

        public static void main(String[] args) throws InterruptedException {
                CountDownLatch countDownLatch = new CountDownLatch(2);
                MyRunnable myRunnable = new MyRunnable(countDownLatch);
                new Thread(myRunnable).start();
                countDownLatch.countDown();
                countDownLatch.await();

                System.out.println("----------end--------------");
        }


        static class MyRunnable implements Runnable {
                private CountDownLatch countDownLatch;

                public MyRunnable(CountDownLatch countDownLatch) {
                        this.countDownLatch = countDownLatch;
                }

                @Override
                public void run() {
                        System.out.println("--------run----------");
                        try {
                                Thread.sleep(2000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                        countDownLatch.countDown();
                }
        }
}
