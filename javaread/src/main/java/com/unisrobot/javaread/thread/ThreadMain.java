package com.unisrobot.javaread.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/5/22.
 */

public class ThreadMain {
        public static void main(String[] args) {
                System.out.println("---------------------start-------------------");
                //                Check check = new Check();
                //                new Thread(new Product(check)).start();
                //                new Thread(new Consumer(check)).start();

                CheckLock check = new CheckLock();
                new Thread(new Product(check)).start();
                new Thread(new Consumer(check)).start();
                new Thread(new Product(check)).start();
                new Thread(new Consumer(check)).start();
        }


        static class Check {
                private int count = 0;

                public synchronized void getCount() throws InterruptedException {
                        if (count >= 1) {
                                System.out.println("货物满了");
                                this.wait();
                        }
                        System.out.println("生产=" + ++count);
                        this.notifyAll();
                }

                public synchronized void sale() throws InterruptedException {
                        if (count <= 0) {
                                System.out.println("缺货");
                                this.wait();
                        }
                        System.out.println("消费：" + --count);
                        this.notifyAll();
                }
        }

        static class CheckLock {
                private int count = 0;
                private Lock lock = new ReentrantLock();
                private Condition condition = lock.newCondition();

                public void getCount() throws InterruptedException {
                        lock.lock();
                        try {
                                if (count >= 1) {
                                        System.out.println("货物满了");
                                        condition.await();
                                }
                                System.out.println("生产=" + ++count);
                                condition.signalAll();

                        } finally {
                                lock.unlock();
                        }

                }

                public synchronized void sale() throws InterruptedException {
                        lock.lock();
                        try {
                                if (count <= 0) {
                                        System.out.println("缺货");
                                        condition.await();
                                }
                                System.out.println("消费：" + --count);
                                condition.signalAll();
                        } finally {
                                lock.unlock();
                        }
                }
        }

        static class Product implements Runnable {
                CheckLock check;

                public Product(CheckLock check) {
                        this.check = check;
                }

                @Override
                public void run() {
                        for (int i = 0; i < 10; i++) {
                                try {
                                        check.getCount();
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                        }
                        System.out.println("Produce-------------end");
                }
        }

        static class Consumer implements Runnable {
                CheckLock check;

                public Consumer(CheckLock check) {
                        this.check = check;
                }

                @Override
                public void run() {
                        for (int i = 0; i < 10; i++) {
                                try {
                                        check.sale();
                                } catch (InterruptedException e) {
                                        e.printStackTrace();
                                }
                        }
                        System.out.println("Consumer-------------end");
                }
        }
}
