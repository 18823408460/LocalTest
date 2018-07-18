package com.unisrobot.javaread.syn;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/7/16.
 */

public class ListMain {
        public static void main(String[] args) {

                testAddress();
        }

        private static void testAddress() {
                Integer a = 1;
                Integer b = 1;
                Integer c = 11;
                String aa = "hhh";
                String bb = "hhh";
                String cc = new StringBuffer("hhhc").toString();
                a = 2;
                String dd = cc.intern();
                System.out.println(System.identityHashCode(a));
                System.out.println(System.identityHashCode(b));
                System.out.println(System.identityHashCode(c));
                System.out.println(System.identityHashCode(aa));
                System.out.println(System.identityHashCode(bb));
                System.out.println(System.identityHashCode(cc));
                System.out.println(System.identityHashCode(dd));

                String s1 = "hello";
                String s2 = "world";

                // 拼接的最终结果才会放入 运行时常量池中，中间的临时变量不会存储。。
                String s3 = "hello" + "world"; //  String s3 = "helloworld";//经过编译器优化。。 拼接的所有部分都是 纯字面值才是这样优化，只要有变量就会按照下面的方式
                String s4 = s1 + s2;  // String s4 = (new StringBuilder()).append(s1).append(s2).toString();经过编译器编译优化
                System.out.println(s3 == s4);
                System.out.println(s3.hashCode());
                System.out.println(s4.hashCode());
                System.out.println(System.identityHashCode(s3));
                System.out.println(System.identityHashCode(s4));
        }

        private static void testFinal() {
                //                byte  a = 1;
                //                byte  b = 3;
                // byte  c = a+b; 		//直接加会出错，因为运算时Java虚拟机进行了转换，导致把一个int赋值给byte


                final byte a = 1;
                final byte b = 3;
                byte c = a + b;                //加上final就不会出错。
        }

        private static void testStatic() {
                new StaticData();
                new StaticData();
                int count = 0;
                while (true) {
                        if (count++ > 65535) {
                                break;
                        }
                        try {
                                Thread.sleep(1000);
                        } catch (InterruptedException e) {
                                e.printStackTrace();
                        }
                }
                new StaticData2();
        }

        private static void testEnum() {
                State state = State.Bad;
                System.out.println(state.ordinal());
        }

        private static void testintern() {
                String str0 = new String("aaa"); // new出来的字符串是存放在堆里面
                String str1 = "aaa"; // 直接定义字符串变量的时候赋值，如果表达式右边只有字符串常量，那么就是把变量存放在常量池里面
                String str2 = "bbb";
                String str3 = "aaabbb";
                String str4 = str1 + str2;  // str4由于表达式右半边有引用类型，所以str4存在于堆内存
                String str5 = "aaa" + "bbb"; // 而str5表达式右边没有引用类型，是纯字符串常量，就存放在了常量池里面
                System.out.println(str0 == str1);
                System.out.println(str3 == str4); // false
                System.out.println(str3 == str4.intern()); // true
                System.out.println(str3 == str5);// true

                // https://blog.csdn.net/w605283073/article/details/72753494

                String java = new StringBuffer().append("java").toString();
                System.out.println(java == java.intern());
                String str111 = new StringBuilder("计算机").append("软件").toString();
                // String str3= new StringBuilder("计算机软件").toString();
                System.out.println(str111.intern() == str111);
        }

        private static void test() {
                ArrayList<A> as = new ArrayList<>();
                as.add(new A("hello"));
                as.add(new A("hello"));
                as.add(new A("hello"));

                System.out.println(as);
                A a = as.get(0);
                a.name = "world";


                System.out.println(as);

                String aaa = "111";
                System.out.println(Float.parseFloat(aaa));
        }

        public static class A {
                public A(String name) {
                        this.name = name;
                }

                public String name;

                @Override
                public String toString() {
                        return "A{" +
                                "name='" + name + '\'' +
                                '}';
                }
        }
}


