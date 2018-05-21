package com.unisrobot.javaread.juc;

/**
 * Created by WEI on 2018/5/21.
 */

public class ConsumerTest {
    public static void main(String[] args) {
        Check check = new Check() ;
        new Thread(new Comsummer(check),"Comsummer").start();
        new Thread(new Produce(check),"Produce").start();
    }


   static class Check {
        private int count = 0;

        public synchronized void get() {
            if (count > 0){
                System.out.println("货物满了。。。");
            }else {
                System.out.println("生产："+ Thread.currentThread().getName()+"  "+ (count++));
            }
        }

        public synchronized void sale(){
            if ( count <= 0 ){
                System.out.println("缺货");
            }else {
                System.out.println("消费："+Thread.currentThread().getName() + "   "+ count--);
            }
        }
    }


    private static class Comsummer implements Runnable{
        Check check ;
        Comsummer(Check check){
            this.check = check ;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                check.sale();
            }
        }
    }

    private static class Produce implements Runnable{
        Check check ;
        Produce(Check check){
            this.check = check ;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                check.get();
            }
        }
    }
}
