package com.unisrobot.javaread.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by WEI on 2018/6/26.
 */

public class ThreadPoolTest {
    static ExecutorService executorService = Executors.newCachedThreadPool();
    public static void main(String[] args) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(2000);
                        handlerData();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private static int count ;
    private static void handlerData() {
        System.out.println("handler Data......");
        try {
            int a = 1/0 ;
        }catch (Exception e){}
        count++ ;
        if (count ==3){
            count = 0;
            System.out.println("shutdown=====");
            executorService.shutdown();  // 线程不会结束
        }
    }
}
