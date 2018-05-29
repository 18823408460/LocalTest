package com.unisrobot.javaread.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/5/23.
 */

public class ThreadTwoMain {
        public static void main(String[] args) {
                ThreadTwoMain threadTwoMain = new ThreadTwoMain();
                //                ThreadTwoMain.Action action = threadTwoMain.new Action();
                ThreadTwoMain.Action action = new ThreadTwoMain().new Action();

                new Thread(new Runnable() {
                        @Override
                        public void run() {
                                action.loopA();
                        }
                },"A").start();
                new Thread(new Runnable() {
                        @Override
                        public void run() {
                                action.loopB();
                        }
                },"ExtendB").start();
                new Thread(new Runnable() {
                        @Override
                        public void run() {
                                action.loopC();
                        }
                },"C").start();
        }

        public class Action {
                private int number = 1;
                private Lock lock = new ReentrantLock();
                private Condition conditionA = lock.newCondition();
                private Condition conditionB = lock.newCondition();
                private Condition conditionC = lock.newCondition();

                public void loopA() {
                        lock.lock();
                        try {
                                if (number != 1) {
                                        conditionA.await();
                                }

                                for (int i = 0; i < 3; i++) {
                                        System.out.println(" " + Thread.currentThread().getName() + "  \t" + i);
                                }
                                number = 2;
                                conditionB.signal();

                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        } finally {
                                lock.unlock();
                        }
                }

                public void loopB() {
                        lock.lock();
                        try {
                                if (number != 2) {
                                        conditionB.await();
                                }

                                for (int i = 0; i < 3; i++) {
                                        System.out.println(" " + Thread.currentThread().getName() + "  \t" + i);
                                }
                                number = 3;
                                conditionC.signal();

                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        } finally {
                                lock.unlock();
                        }
                }

                public void loopC() {
                        lock.lock();
                        try {
                                if (number != 3) {
                                        conditionC.await();
                                }

                                for (int i = 0; i < 3; i++) {
                                        System.out.println(" " + Thread.currentThread().getName() + "  \t" + i);
                                }
                                number = 1;
                                conditionC.signal();

                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        } finally {
                                lock.unlock();
                        }
                }

        }


}
