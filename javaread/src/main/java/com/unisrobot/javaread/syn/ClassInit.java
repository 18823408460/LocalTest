package com.unisrobot.javaread.syn;

import java.util.Random;

/**
 * Created by Administrator on 2018/7/20.
 */

public class ClassInit {

        private static Random rand = new Random(7); //47作为随机种子，为的就是产生随机数。

        public final int a = rand.nextInt(20);

        public static final int b = rand.nextInt(20);


        public static class Child extends Perent {
                static {
                        System.out.println("------Child static ------");
                }

                public static void test() {
                        System.out.println("--------- test----------");
                }
        }

        public static class Perent {

                public static final String aa = "Hello";
                //                public static String aa = "Hello";

                static {
                        System.out.println("------Perent static ------");
                }

                public Perent() {
                        System.out.println("Perent ------ contruct-----");
                }
        }

        static {
                System.out.println("=============== main ===========");
        }

        public static void main(String[] args) {
                System.out.println(Child.aa);
                Child.test();

                ClassInit classInit = new ClassInit();
                System.out.println(classInit.a);
                System.out.println(classInit.b);

                ClassInit classIni1t = new ClassInit();
                System.out.println(classIni1t.a);
                System.out.println(classIni1t.b);
        }
}
