package com.unisrobot.javaread.juc;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/5/21.
 */

public class MemoryVisiableTest {
        private static boolean stop = false;
        private static volatile boolean v2stop = false;
        private static HashMap<String, Boolean> map2stop = new HashMap<String, Boolean>();

        /**
         * @throws InterruptedException
         */
        @Test
        public  void main() throws InterruptedException {
                testMap();
                testVolatile();
                testBasicType();
        }

        //程序正常结束

        /**
         * 第一种写法：HashMap在不是并发安全的—即便这个例子里只有一个线程“写”、另一个线程“读”，不显式同步的话根据Java的语义其实是不保证可见性的。
         * 只不过楼主多半是在x86上测的，而x86的内存模型其实比Java的更强，
         * 所以有些理论上在Java里不保证正确的代码在x86上执行的时候观察不到错误。
         * @throws InterruptedException
         */
        public static void testMap() throws InterruptedException{
                map2stop.put("stop", Boolean.FALSE);
                new Thread(){
                        public void run(){
                                int i=0;
                                while (!map2stop.get("stop") ){
                                        i++;
                                }
                                System.out.println("map loop finish:"+i);
                        }
                }.start();

                Thread.sleep(1000);
                map2stop.put("stop", Boolean.TRUE);
                Thread.sleep(1000);
                System.out.println("map main stop");
        }

        //说明了volatile的开销
        public static void testVolatile() throws InterruptedException{
                new Thread(){
                        public void run(){
                                int i=0;
                                while(!v2stop){
                                        i++;
                                }
                                System.out.println("volatile loop finish:"+i);
                        }
                }.start();

                Thread.sleep(1000);
                v2stop=true;
                Thread.sleep(1000);
                System.out.println("volatile main stop");
        }

        //不可见，发生死循环
        public static void testBasicType() throws InterruptedException{
                new Thread(){
                        public void run(){
                                int i=0;
                                while(!stop){
                                        i++;
                                }
                                System.out.println("basic type loop finish:"+i);
                        }
                }.start();

                Thread.sleep(1000);
                stop=true;
                Thread.sleep(1000);
                System.out.println("basic type main stop");
        }
}
