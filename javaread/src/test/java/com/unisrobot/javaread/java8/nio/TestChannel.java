package com.unisrobot.javaread.java8.nio;

/**
 * Created by WEI on 2018/5/20.
 */

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * Channel: 连接 源文件和 目标文件的 通道，，但和 流不一样，不和数据之间打交道，数据交给 Buffer,
 * <p>
 * Channel 的主要实现类：
 * FileChannel, SocketChannel, SocketServerChannel, DatagrameChannel
 * <p>
 * 1. Chanmel的过去方式：
 * >  FielInputStream.....
 * Socket.....
 * getChannel()
 * >  jdk1.7 中 NIO.2 中，各个通道的静态方法 open()
 * >  jdk1.7 中 NIO2 中  Files工具类的静态方法  newByteChannel() ；
 * <p>
 * <p>
 * 2. 通道之间直接进行数据传输
 * <p>
 * <p>
 * 3. 分散Scather 和 聚集 Gather
 * 分散读取，将 channel中的数据分散读取到多个 buffer中
 * 聚集 ： 将多个buffer中的数据 聚集 到 channel中
 * <p>
 *
 *
 * 4. 字符集
 */
public class TestChannel {


    @Test
    public void test(){

    }



    @Test // 系统支持的 编码集
    public void test6() {
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = stringCharsetSortedMap.entrySet();
        for (Map.Entry<String, Charset> e : entries) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
    }

    @Test
    public void test5() throws IOException {
        String str = "F:\\githubRep\\LocalTest\\LocalTest\\javaread\\1.txt";
        RandomAccessFile accessFile = new RandomAccessFile(str, "rw");
        FileChannel channel = accessFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(40);
        ByteBuffer buffer1 = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = {buffer, buffer1};
        channel.read(buffers);

        for (ByteBuffer b : buffers) {
            b.flip();
            // allocateDirect 不支持 array
            System.out.println(new String(b.array(), 0, b.limit(), "GBK"));
            System.out.println("--------------------------------------------");
        }

        String str1 = "F:\\githubRep\\LocalTest\\LocalTest\\javaread\\2.txt";
        RandomAccessFile accessFile1 = new RandomAccessFile(str1, "rw");
        FileChannel channel1 = accessFile1.getChannel();

        channel1.write(buffers);
    }


    @Test // 直接内存， 通道之间数据的传输
    public void test4() throws IOException {
        String str = "F:\\githubRep\\LocalTest\\LocalTest\\javaread\\javah.png";
        String str2 = "F:\\githubRep\\LocalTest\\LocalTest\\javaread\\javah1133.png";
        FileChannel channel = FileChannel.open(Paths.get(str), StandardOpenOption.READ);
        FileChannel channel1 = FileChannel.open(Paths.get(str2), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

//        channel.transferTo(0,channel.size(),channel1);
        channel1.transferFrom(channel, 0, channel.size());
        channel.close();
        channel1.close();
    }

    // 直接内存 ----文件映射方式
    @Test
    public void test3() throws IOException {
        String str = "F:\\githubRep\\LocalTest\\LocalTest\\javaread\\javah.png";
        String str2 = "F:\\githubRep\\LocalTest\\LocalTest\\javaread\\javah11.png";
        FileChannel channel = FileChannel.open(Paths.get(str), StandardOpenOption.READ);
        FileChannel channel1 = FileChannel.open(Paths.get(str2), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        MappedByteBuffer map1 = channel1.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());

        byte[] dst = new byte[map.limit()];
        map.get(dst);
        map1.put(dst);

        channel.close();
        channel1.close();
    }

    /**
     * 直接内存
     *
     * @throws IOException
     */
    @Test
    public void test2() throws IOException {
        FileInputStream is = new FileInputStream("F:\\githubRep\\LocalTest\\LocalTest\\javaread\\javah.png");
        FileOutputStream os = new FileOutputStream("F:\\githubRep\\LocalTest\\LocalTest\\javaread\\copy1.png");

        FileChannel channel = is.getChannel();
        FileChannel channel1 = os.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (channel.read(buffer) != -1) {
            buffer.flip();
            channel1.write(buffer);

            buffer.clear();
        }
        channel.close();
        channel1.close();
        is.close();
        os.close();

    }

    /**
     * 非直接内存
     *
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        FileInputStream is = new FileInputStream("F:\\githubRep\\LocalTest\\LocalTest\\javaread\\javah.png");
        FileOutputStream os = new FileOutputStream("F:\\githubRep\\LocalTest\\LocalTest\\javaread\\copy.png");

        FileChannel channel = is.getChannel();
        FileChannel channel1 = os.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (channel.read(buffer) != -1) {
            buffer.flip();
            channel1.write(buffer);

            buffer.clear();
        }
        channel.close();
        channel1.close();
        is.close();
        os.close();
    }
}
