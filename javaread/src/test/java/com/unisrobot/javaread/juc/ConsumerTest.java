package com.unisrobot.javaread.juc;

import org.junit.Test;

/**
 * Created by WEI on 2018/5/21.
 */

public class ConsumerTest {


        @Test
        public void main() {
                Check check = new Check();
                new Thread(new Produce(check), "Produce").start();
                //new Thread(new Comsummer(check), "Comsummer").start();
        }


        static class Check {
                private int count = 0;

                public synchronized void get() {
                        if (count > 0) {
                                System.out.println("货物满了。。。");

                        } else {
                                System.out.println("生产：" + Thread.currentThread().getName() + "  " + (++count));
                                this.notifyAll();
                        }
                }

                public synchronized void sale() {
                        if (count <= 0) {
                                System.out.println("缺货。。。");
                                try {
                                        this.wait();
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                        } else {
                                System.out.println("消费：" + Thread.currentThread().getName() + "   " + (count--));
                                this.notifyAll();
                        }
                }
        }


        private static class Comsummer implements Runnable {
                Check check;

                Comsummer(Check check) {
                        this.check = check;
                }

                @Override
                public void run() {
                        for (int i = 0; i < 10; i++) {
                                check.sale();
                        }
                        System.out.println("comsummer end...........");
                }
        }

        private static class Produce implements Runnable {
                Check check;

                Produce(Check check) {
                        this.check = check;
                }

                @Override
                public void run() {
                        for (int i = 0; i < 10; i++) {
                                check.get();
                        }

                        System.out.println("produce end...........");
                }
        }
}
