package com.unisrobot.javaread.java8.nio;

/**
 * Created by WEI on 2018/5/20.
 */

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 非阻塞 IO ；； socket
 * <p>
 * >  通道
 * >  缓冲区
 * >  选择器
 */
public class TestNonBlockingNio {

    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.01", 9999));
        socketChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        buffer.put(("Server hello:"+new Date()).getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        socketChannel.close();
    }

    @Test
    public void sever() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                if (next.isAcceptable()) {
                    SocketChannel accept = serverSocketChannel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);

                } else if (next.isReadable()) {
                    SocketChannel channel = (SocketChannel) next.channel();
                    channel.configureBlocking(false);
                    ByteBuffer dst = ByteBuffer.allocate(1024);

                    int len =0 ;
                    while((len = channel.read(dst)) != -1){
                        dst.flip();
                        System.out.println("===" + new String(dst.array(), 0,len));
                        dst.clear();
                    }
                }
                iterator.remove();
            }
        }
        serverSocketChannel.close();
    }
}
