package com.unisrobot.javaread.juc;

import org.junit.Test;

/**
 * Created by Administrator on 2018/5/21.
 */

public class VolatileMain {
       // private static boolean flag = false;

        @Test
        public void main() throws InterruptedException {
                MyRunnalbe myRunnalbe = new MyRunnalbe();
                new Thread(myRunnalbe).start();
                while (true){

                        //为什么睡眠可以解决内存可见性问题
                        Thread.sleep(100);
                        if (myRunnalbe.isFlag()){
                                System.out.println("-----------------break----------------");
                                break;
                        }

                }

//                while (true){
//                        synchronized(myRunnalbe){
//                                if (myRunnalbe.isFlag()){
//                                        System.out.println("-------------------break---------");
//                                        break;
//                                }
//                        }
//                }
        }

        static class MyRunnalbe implements Runnable {
                boolean flag = false;
                public boolean isFlag() {
                        return flag;
                }

                public void setFlag(boolean flag) {
                        flag = flag;
                }

                @Override
                public void run() {
                        try {
                                Thread.sleep(5000);
                                flag = true;
                                System.out.println("flag=========" + flag);

                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
        }
}
