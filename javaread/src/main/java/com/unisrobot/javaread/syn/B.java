package com.unisrobot.javaread.syn;

/**
 * Created by Administrator on 2018/7/20.
 */

public class B {
        //public static final String aa = "Hello";
        public static final String aa = "Hello";

        static {
                System.out.println("------B static ------");
        }

        public B() {
                System.out.println("B  ------ contruct-----");
        }
}
