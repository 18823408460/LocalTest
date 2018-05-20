package com.unisrobot.javaread.java8.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by WEI on 2018/5/20.
 */

/**
 * Channel:
 * SelectChanel
 * SocketChannel
 * ServerSocketChannel
 * DatagramChannel
 * <p>
 * Pipe -- SinkChannel
 * Pipe -- SourceChannel
 */
public class TestBlockingNio2 {

    @Test
    public void client() throws IOException {
        String str = "F:\\githubRep\\LocalTest\\LocalTest\\javaread\\javah.png";
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel fileChannel = FileChannel.open(Paths.get(str), StandardOpenOption.READ);

        ByteBuffer allocate = ByteBuffer.allocate(1024);
        while (fileChannel.read(allocate) != -1) {
            allocate.flip();
            socketChannel.write(allocate);
            allocate.clear();
        }

        // 必须要关闭  输出，，才能接受 输入，，不然会 一直阻塞
        socketChannel.shutdownOutput();

        int len;
        while ((len = socketChannel.read(allocate)) != -1) {
            String s = new String(allocate.array(), 0,len);
            System.out.println("======== " + s);
        }

        socketChannel.close();
        fileChannel.close();
    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9898));
        SocketChannel socketChannel = channel.accept();

        String str1 = "F:\\githubRep\\LocalTest\\LocalTest\\javaread\\socket.png";
        FileChannel fileChannel = FileChannel.open(Paths.get(str1), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }

        buffer.put("服务端结束succes".getBytes());
        buffer.flip(); // 每次写完数据后要进行 读操作，，
        socketChannel.write(buffer);


        socketChannel.close();
        fileChannel.close();
    }
}
