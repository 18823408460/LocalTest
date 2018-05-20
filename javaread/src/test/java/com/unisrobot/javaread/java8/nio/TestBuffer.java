package com.unisrobot.javaread.java8.nio;

/**
 * Created by WEI on 2018/5/20.
 */

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * buffer: 底层是数组，可存储 不同类型的数据
 * <p>
 * 对于不同 类型 的基本数据(boolean 除外),都有对应的 Bufffer  ( abstract class Buffer)
 * <p>
 * IntBuffer
 * .....
 * <p>
 * <p>
 * abstract class Buffer: 四个重要的属性：(buffer分为 读模式 和  写模式)
 * > position : 下一个可以操作的position
 *
 *
 *
 */
public class TestBuffer {

    @Test
    public void test1() {
        String str = "abcde";
        ByteBuffer allocate = ByteBuffer.allocate(100);
        System.out.println("--------allocate---------------");
        System.out.println("" + allocate.position());
        System.out.println("" + allocate.capacity());
        System.out.println("" + allocate.limit());
        System.out.println("" + allocate.mark());

        allocate.put(str.getBytes());
        System.out.println("--------put---------------");
        System.out.println("" + allocate.position());
        System.out.println("" + allocate.capacity());
        System.out.println("" + allocate.limit());
        System.out.println("" + allocate.mark());

        byte b = allocate.get(); // 由于postion 指向的地方没有数据，所以这里为空
        System.out.println("=======read======"+(char)b);

        allocate.flip();// 读模式,,, postion ==0 ;
        System.out.println("--------flip---------------");
        System.out.println("" + allocate.position());
        System.out.println("" + allocate.capacity());
        System.out.println("" + allocate.limit()); //limit 指向 postion位置，表示有多少数据可以读取

        byte[] reads  = new byte[allocate.limit()];
        allocate.get(reads);
        String s = new String(reads);
        System.out.println("--------get---------------");
        System.out.println("data==="+s);
        System.out.println("" + allocate.position());
        System.out.println("" + allocate.capacity());
        System.out.println("" + allocate.limit());

        // allocate 内置的方法，，都是围绕 他的四个属性在控制的。。。
        System.out.println("--------put--------------");
        allocate.clear(); // 重新写之前，必须要 clear，，，
        allocate.put(str.getBytes());
        System.out.println("" + allocate.position());
        System.out.println("" + allocate.capacity());
        System.out.println("" + allocate.limit());
    }
}
