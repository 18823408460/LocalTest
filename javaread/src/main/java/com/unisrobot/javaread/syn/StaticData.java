package com.unisrobot.javaread.syn;

/**
 * Created by Administrator on 2018/7/18.
 */

public class StaticData {
        private static MyStatic myStatic;

        static {
                System.out.println("---------static-----------");
        }

        public StaticData() {
                myStatic = new MyStatic();
                System.out.println("-----------create----------------");
        }

        static class MyStatic {
                static {
                        System.out.println("myStatic static-----------");
                }
                public MyStatic() {
                        System.out.println("====== myStatic------create-=========");
                }
        }
}
